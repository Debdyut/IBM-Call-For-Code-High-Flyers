require('dotenv').config({silent: true})

const express = require('express');
const bodyParser = require('body-parser');

const assistant = require('./lib/assistant.js');
const port = process.env.PORT || 3000

const cloudant = require('./lib/cloudant.js');

const app = express();
app.use(bodyParser.json());

const testConnections = () => {
  const status = {}
  return assistant.session()
    .then(sessionid => {
      status['assistant'] = 'ok';
      return status
    })
    .catch(err => {
      console.error(err);
      status['assistant'] = 'failed';
      return status
    })
    .then(status => {
      return cloudant.info();
    })
    .then(info => {
      status['cloudant'] = 'ok';
      return status
    })
    .catch(err => {
      console.error(err);
      status['cloudant'] = 'failed';
      return status
    });
};

const handleError = (res, err) => {
  const status = err.code !== undefined && err.code > 0 ? err.code : 500;
  return res.status(status).json(err);
};

app.get('/', (req, res) => {
  testConnections().then(status => res.json({ status: status }));
});

/**
 * Get a session ID
 *
 * Returns a session ID that can be used in subsequent message API calls.
 */
app.get('/api/session', (req, res) => {
  assistant
    .session()
    .then(sessionid => res.send(sessionid))
    .catch(err => handleError(res, err));
});

/**
 * Post process the response from Watson Assistant
 *
 * We want to see if this was a request for resources/supplies, and if so
 * look up in the Cloudant DB whether any of the requested resources are
 * available. If so, we insert a list of the resouces found into the response
 * that will sent back to the client.
 * 
 * We also modify the text response to match the above.
 */
function post_process_assistant(result) {
  let resource
  // First we look to see if a) Watson did identify an intent (as opposed to not
  // understanding it at all), and if it did, then b) see if it matched a supplies entity
  // with reasonable confidence. "supplies" is the term our trained Watson skill uses
  // to identify the target of a question about resources, i.e.:
  //
  // "Where can i find face-masks?"
  //
  // ....should return with an enitity == "supplies" and entitty.value = "face-masks"
  //
  // That's our trigger to do a lookup - using the entitty.value as the name of resource
  // to to a datbase lookup.
  if (result.intents.length > 0 ) {
    result.entities.forEach(item => {
      if ((item.entity == "supplies") &&  (item.confidence > 0.3)) {
        resource = item.value
      }
    })
  }
  if (!resource) {
    return Promise.resolve(result)
  } else {
    // OK, we have a resource...let's look this up in the DB and see if anyone has any.
    return cloudant
      .find('', resource, '')
      .then(data => {
        let processed_result = result
        if ((data.statusCode == 200) && (data.data != "[]")) {
          processed_result["resources"] = JSON.parse(data.data)
          processed_result["generic"][0]["text"] = 'There is' + '\xa0' + resource + " available"
        } else {
          processed_result["generic"][0]["text"] = "Sorry, no" + '\xa0' + resource + " available"           
        }
        return processed_result
      })
  }
}

/**
 * Post a messge to Watson Assistant
 *
 * The body must contain:
 * 
 * - Message text
 * - sessionID (previsoulsy obtained by called /api/session)
 */
app.post('/api/message', (req, res) => {
  const text = req.body.text || '';
  const sessionid = req.body.sessionid;
  console.log(req.body)
  assistant
    .message(text, sessionid)
    .then(result => {
      return post_process_assistant(result)
    })
    .then(new_result => {
      res.json(new_result)
    })
    .catch(err => handleError(res, err));
});

/**
 * Get a list of resources
 *
 * The query string may contain the following qualifiers:
 * 
 * - type
 * - name
 * - userID
 *
 * A list of resource objects will be returned (which can be an empty list)
 */
app.get('/api/resource', (req, res) => {  
  const bloodGroup = req.query.bloodGroup;
  const location = req.query.location;  
  cloudant
    .find(location, bloodGroup)
    .then(data => {
      if (data.statusCode != 200) {
        res.sendStatus(data.statusCode)
      } else {
        res.send(data.data)
      }
    })
    .catch(err => handleError(res, err));
});

/**
 * Create a new resource
 *
 * The body must contain:
 * 
 * - type
 * - name
 * - contact
 * - userID
 *
 * The body may also contain:
 * 
 * - description
 * - quantity (which will default to 1 if not included)
 * 
 * The ID and rev of the resource will be returned if successful
 */
let types = ["Any", "A+", "B+", "O+", "AB+", "A-", "B-", "AB-", "O-"]
app.post('/api/resource', (req, res) => {
  if (!req.body.type) {
    return res.status(422).json({ errors: "Type of item must be provided"});
  }
  if (!types.includes(req.body.type)) {
    return res.status(422).json({ errors: "Type of item must be one of " + types.toString()});
  }
  if (!req.body.name) {
    return res.status(422).json({ errors: "Name of item must be provided"});
  }
  if (!req.body.contact) {
    return res.status(422).json({ errors: "A method of conact must be provided"});
  }
  const type = req.body.type;
  const name = req.body.name;
  const description = req.body.description || '';
  const userID = req.body.userID || '';
  const quantity = req.body.quantity || 1;
  const location = req.body.location || '';
  const contact = req.body.contact;

  cloudant
    .create(type, name, description, quantity, location, contact, userID)
    .then(data => {
      if (data.statusCode != 201) {
        res.sendStatus(data.statusCode)
      } else {
        res.send(data.data)
      }
    })
    .catch(err => handleError(res, err));
});

/**
 * Create a new resource
 *
 * The body must contain:
 * 
 * - type
 * - name
 * - contact
 * - userID
 *
 * The body may also contain:
 * 
 * - description
 * - quantity (which will default to 1 if not included)
 * 
 * The ID and rev of the resource will be returned if successful
 */
app.post('/api/saveDonorData', (req, res) => {
  if (!req.body.personName) {
    return res.status(422).json({ errors: "Person name must be provided"});
  }
  if (!req.body.dob) {
    return res.status(422).json({ errors: "Date of birth must be provided"});
  }
  if (!req.body.emailId) {
    return res.status(422).json({ errors: "Email Id must be provided"});
  }
  if (!req.body.phone) {
    return res.status(422).json({ errors: "Phone number must be provided"});
  }
  if (!req.body.location) {
    return res.status(422).json({ errors: "Location must be provided"});
  }
  if (!req.body.gender) {
    return res.status(422).json({ errors: "Gender must be provided"});
  }
  if (!req.body.isLabTestDone) {
    return res.status(422).json({ errors: "Lab test done indicator must be provided"});
  }
  if (!req.body.haveRecentSymptoms) {
    return res.status(422).json({ errors: "Recent symptoms indicator must be provided"});
  }
  if (!req.body.isFollowUpTestDone) {
    return res.status(422).json({ errors: "Follow up test done indicator must be provided"});
  }
  if (!req.body.lastDateOfSymptom) {
    return res.status(422).json({ errors: "Last date of symptom must be provided"});
  }
  if (!req.body.bloodGroup) {
    return res.status(422).json({ errors: "Blood group must be provided"});
  }
      
  const userID = req.body.userID || '';
  const personName = req.body.personName;
  const dob = req.body.dob;
  const emailId = req.body.emailId;
  const phone = req.body.phone;  
  const location = req.body.location;
  const gender = req.body.gender;
  const isLabTestDone = req.body.isLabTestDone;
  const haveRecentSymptoms = req.body.haveRecentSymptoms;
  const isFollowUpTestDone = req.body.isFollowUpTestDone;
  const lastDateOfSymptom = req.body.lastDateOfSymptom;  
  const bloodGroup = req.body.bloodGroup;  

  cloudant
    .create(personName, dob, emailId, phone, location, gender, isLabTestDone, haveRecentSymptoms, isFollowUpTestDone, lastDateOfSymptom, bloodGroup, userID)
    .then(data => {
      if (data.statusCode != 201) {
        res.sendStatus(data.statusCode)
      } else {
        res.send(data.data)
      }
    })
    .catch(err => handleError(res, err));
});

/**
 * Update new resource
 *
 * The body may contain any of the valid attributes, with their new values. Attributes
 * not included will be left unmodified.
 * 
 * The new rev of the resource will be returned if successful
 */

app.patch('/api/resource/:id', (req, res) => {
  const type = req.body.type || '';
  const name = req.body.name || '';
  const description = req.body.description || '';
  const userID = req.body.userID || '';
  const quantity = req.body.quantity || '';
  const location = req.body.location || '';
  const contact = req.body.contact || '';

  cloudant
    .update(req.params.id, type, name, description, quantity, location, contact, userID)
    .then(data => {
      if (data.statusCode != 200) {
        res.sendStatus(data.statusCode)
      } else {
        res.send(data.data)
      }
    })
    .catch(err => handleError(res, err));
});

/**
 * Delete a resource
 */
app.delete('/api/resource/:id', (req, res) => {
  cloudant
    .deleteById(req.params.id)
    .then(statusCode => res.sendStatus(statusCode))
    .catch(err => handleError(res, err));
});

const server = app.listen(port, () => {
   const host = server.address().address;
   const port = server.address().port;
   console.log(`SolutionStarterKitCooperationServer listening at http://${host}:${port}`);
});

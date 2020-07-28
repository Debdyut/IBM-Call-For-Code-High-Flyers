/**
 *
 * main() will be run when you invoke this action
 *
 * @param Cloud Functions actions accept a single parameter, which must be a JSON object.
 *
 * @return The output of this action, which must be a JSON object.
 *
 */
const fetch = require('node-fetch');

async function main(params) {
  if (params.type === "saveDonorData") {
    return fetch(`${params.bff_url}/api/saveDonorData`, {
        method: 'POST',
        mode: 'no-cors',
        cache: 'no-cache',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(params)
      }).then((response) => {
        if (!response.ok) {
          return {
            result: `Oops, something went wrong. Please try again later.`
          }
        } else {
          return {
            result: `Donor has been registered successfully.`
          }
        }
      });
  }
  else if (params.type === "immunity") {
    return fetch(`${params.immunity_url}/immunity/api/checkWebhook`, {
        method: 'POST',
        mode: 'no-cors',
        cache: 'no-cache',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(params)
      }).then((response) => {
        if (!response.ok) {
          return {
            result: `Oops, something went wrong. Please try again later.`
          }
        } else {
          return response.json()
        }
      });
  }
  else if (params.type === "depression") {
    const response = await fetch(`${params.depression_url}/mntlHlthEvln/api/getEvlnRpt`, {
        method: 'POST',
        mode: 'no-cors',
        cache: 'no-cache',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(params)
      });
      
      const result = await response
      const json = await response.json();
      
      if(!result.ok) {
          return { "message": "Oops please retry after some time.","color":""}
      } else {
			var displayHelpCenter=false;
		    var intimateFriend = false;
			//var msg=`Report says you have ${json.dprnScor.dprnStt} with score of ${json.dprnScor.dprnScor}`;
			var msg='';
			var stt = `${json.dprnScor.dprnStt}`;
			stt = stt.toLowerCase();
			
			var score = `${json.dprnScor.dprnScor}`;
			score = parseInt(score);
			
			var username = titleCase(`${params.A12}`);
			var nameArray = username.split(' ');
			
			//=== populate message head to display in the bot.
			msg = ppldMsgHead(nameArray, stt, score);
			
            
			if(`${json.dprnScor.dprnStt}` === 'Moderate depression'){
			
				displayHelpCenter=true;
				
			} else if(`${json.dprnScor.dprnStt}` === 'Moderately severe depression' || 
			`${json.dprnScor.dprnStt}` === 'Severe depression'){
			    
				intimateFriend = true;
				displayHelpCenter=true;
				
			}
			
			if(`${displayHelpCenter}` === 'true'){
			    
				//=== populate entire to display in the bot.
				msg=ppldFinalMsg(msg);
				
				if(`${intimateFriend}` === 'true'){
				    sendEmail(params, json);
				}
			} else{
			 
			    msg = `${msg}` +' which is really great news, we wish you always have a happy smile.</p>';
			    
			}
          return { "message": `${msg}`,"color": `${json.dprnScor.colorCd}` }
      }

  }
}

function getHelpDtls(){
	var hlpDtls='<div>Jeevan Aastha Helpline: '
                +'	<ul><li>1800 233 3330</li></ul>'
                +'</div>'
    			+'<div>AASRA Helpline: '
                +'	<ul><li>+91-9820466726</li></ul>'
                +'</div>'
    			+'<div>One Life Foundation: '
                +'	<ul><li>+91-7893078930</li></ul>'
                +'</div>'
    			+'<div>Fortis Stress Helpline: '
                +'	<ul><li>+91-8376804102</li></ul>'
                +'</div>'
    			+'<div>'
                +'	Please follow the <a href="https://indianhelpline.com/SUICIDE-HELPLINE/">link</a> to find more.'
                +' <br /> <br />   '
                +'    link: https://indianhelpline.com/SUICIDE-HELPLINE/' 
                +'</div>';
	return hlpDtls;			
}
function sendEmail(params, json){
	
	var username = titleCase(`${params.A12}`);
	var nameArray = username.split(' ');
	var stt = `${json.dprnScor.dprnStt}`;
	stt = stt.toLowerCase();
	
	var hlpDtls = getHelpDtls();
	
	const sgMail = require('@sendgrid/mail');
	sgMail.setApiKey(params.sendgrid_apikey);
	const msg = {
	  to: `${params.A11}`,
	  from: params.sender_email,
	  subject: 'Mental Helth Evaluation Review',
	  //text: 'and easy to do anywhere, even with Node.js',
	  html: '<div >Hi '+`${params.A10}`+','
    			+'<p><div>I am SHIELD bot powered by IBM Watson and recently had a discussion with '+`${username}`+'.' 
    			+'I noticed he is suffering from '+`${stt}`+'. Please talk to '+`${nameArray[0]}`+' and arrange immediate help. </div>'
    			+'<div>Below is a list of helpline numbers,</div>'
    			+'<p>'
    			+`${hlpDtls}`
    			+'</p></p>'
    		+'</div>',
	};
	sgMail.send(msg);
	console.log("send email");
}

function titleCase(str) {
   var splitStr = str.toLowerCase().split(' ');
   for (var i = 0; i < splitStr.length; i++) {
       // You do not need to check if i is larger than splitStr length, as your for does that for you
       // Assign it back to the array
       splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);     
   }
   // Directly return the joined string
   return splitStr.join(' '); 
}

function ppldMsgHead(nameArray, stt, score) {
   var msg = 'Hello '+`${nameArray[0]}`+','
		+'<p>'
		+'It was a pleasure to talking with you. Thank you so much for answering so many questions with patience. ';
		
	
	if(score > 0){
		msg = msg +'After discussing with you, we have found that you are going through a '+`${stt}`+', my friend. ';
		
	} else {
		
		msg = msg +'After discussing with you, we have found that you don\'t have any depression. ';
	}
	
	return msg;
}

function ppldFinalMsg(msg){
	var hlpDtls = getHelpDtls();
	var msg1='<div > '+`${msg}`
				+'Don\'t worry, please spend some more time with your dear once. '
				+'For further assistance, please reach out to the below contacts or the links. '
				+'We will be happier to help you.</p>'
				  +'<p style="background-color:#e3eeff;">'
					+'<div style="background-color:#e3eeff; padding:10px;  word-wrap: break-word;">'
					+`${hlpDtls}`
					
					
					+'</div>'
				  +'</p>'
				+'</div>';
	
	return msg1;
}
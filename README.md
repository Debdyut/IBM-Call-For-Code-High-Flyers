# IBM-Call-For-Code-High-Flyers:SHIELD

# What's the problem?
* Over 6 months have passed after the first few Covid-19 cases had been identified  in China. Today coronavirus has become the singular most life-threatening disease for a significant portion of world population who are aged and suffering from other critical ailments. 
* Businesses across the globe are struggling due to the absence of customers and staff were as the same (customer and staff) are scared of catching the infection and staying at home. This is a classic deadlock situation which is plummeting economies, causing drastic layoffs, increased depression problems, and COVID-19 is going to continue for at least the next 2 quarters or maybe new few years till COVID-19 vaccine arrives in the market.  
* According to the WHOs website, ‘there are no specific vaccines or treatments for COVID-19’. Meanwhile, various existing medicines and clinical treatments are being tried as potential treatments for Covid-19. US Food and  
* Drug Administration in partnership with popular health organizations is collecting convalescent plasma from  
Covid-19 recovered patients to cure serious or immediately life-threatening COVID-19 infections, or those  
judged by a health care provider to be at high risk of progression to severe or life-threatening disease.  
* According to them, ‘People who have fully recovered from COVID-19 have antibodies in their plasma that can attack the virus’. Today there is a lack of centralized system which will help to connect health organizations with potential plasma donors who have fully recovered from Covid-19 and help in the curing process. 

# The idea
A comprehensive ChatBot solution SHIELD to help survive  the pandemic and also potentially help in the cure.  — 
* Detects depression based on conversation analysis and suggest potential solutions, helpline numbers and notifications to concerned party depending on the severity. This helps people with depression due to staying at home. 
* Calculates immunity score based on conversation analysis of eating habit, daily routine, age group etc. and suggest ways of improving it through lifestyle and food changes. In absence of vaccine physical distancing and improved immunity are the only SHIELD against COVID-19 allowing  people to come out of there home and continue with there life in turn  normalizing businesses, economies etc. 
* Allows voluntary registration of plasma donors to the centralized registry accessible to medical and government departments. 
* notifies plasma donors depending on the donation history, donor location etc. when requested by medical department. User accepting the request and confirming for donation will save 5 lives (each donation is enough to save 5 critical patient through plasma therapy). 

# Getting started

## Prerequisites
* Register for an IBM Cloud account.
* Install and configure IBM Cloud CLI.
* Register for a HERE account.
* Install React Native CLI dependencies. See the React Native documentation for the exact steps and requirements based on your Operating System and Target OS. For example:
    * iOS on macOS
        * Node.js
        * Watchman
        * Xcode
        * CocoaPods
    * Android on Windows
        * Node.js
        * Python 2
        * Java Development Kit
        * Android Studio - add Android 9 (Pie) SDK & configure ANDROID_HOME
        * Create an Android Virtual Device (AVD) - with Pie image (API Level 28)
* Clone the [repository](https://github.com/Debdyut/IBM-Call-For-Code-High-Flyers/)

## Steps
1. **Set up an instance of Watson Assistant**
2. **Provision a CouchDB instance using Cloudant**
3. **Generate an API Key from the HERE Developer Portal**
4. **Deploy Mental Health Service on IBM cloud**
5. **Deploy Immunity Service on IBM cloud**
6. **Creating Cloud Functions**
7. **Integrate data sources via a Watson Assistant webhook**
8. **Run the server**
9. **Run the mobile application**

### 1. Set up an instance of Watson Assistant
   Log in to IBM Cloud and provision a Watson Assistant instance.
   1. Provision an instance of Watson Assistant from the IBM Cloud catalog. 
   2. Launch the Watson Assistant service. 
   3. Create an Assistant. 
   4. Add a dialog skill to the Assistant by importing the skill.json file. 
   5. Go back to All Assistants page, open Settings from the action menu ( ⋮ ) and click on API Details. 
   6. Note the Assistant ID, API Key, and Assistant URL. For Assistant URL, make note of the base URL/domain (e.g., https://api.us-    south.assistant.watson.cloud.ibm.com or https://api.eu-gb.assistant.watson.cloud.ibm.com) and not the full directory/path. You will need all three of these values in Step 4 below. 
   7. Go to Preview Link to get a link to test and verify the dialog skill. 

### 2. Provision a CouchDB instance using Cloudant
   Log into the IBM Cloud and provision a CouchDB instance using Cloudant.
   1. From the catalog, select Databases and then the Cloudant panel.
   2. Once selected, you can choose your Cloudant plan -- there is a free tier for simple testing that is sufficient to run this CIR example. You should choose an appropriate region, give the service a name, and it is recommended you choose Use only IAM under Available authentication methods. You can leave the other settings with their defaults. Click the blue Create button when ready.
   3. Once your Cloudant instance has been created, you need to create a service credential that the CIR API Server can use to communicate with it. By selecting your running Cloudant instance, you can choose Service credentials from the left-hand menu. Create a new service credential and give it a name (it doesn't matter what you call it).
   4. Once created, you can display the credentials by selecting view service credentials, and then copy the credential, so you are ready to paste it into the code of the API server in Step 4.

### 3. Generate an API Key from the HERE Developer Portal
   The application uses HERE Location Services for maps, searching, and routing.
   To access these services, you'll need an API key. Follow the instructions outlined in the HERE Developer Portal to generate a JavaScript API key.

### 4. Deploy Mental Health Service on IBM cloud

   Follow the steps mentioned [here](https://github.com/Debdyut/IBM-Call-For-Code-High-Flyers/blob/master/mental-health-service/README.md)

### 5. Deploy Immunity Service on IBM cloud 

   Follow the steps mentioned [here](https://github.com/Debdyut/IBM-Call-For-Code-High-Flyers/blob/master/immunity/README.md)

### 6. Creating Cloud Functions

   1. Create a cloud function using node.js runtime. Follow the steps [here](https://github.com/Call-for-Code/Solution-Starter-Kit-Communication-2020/blob/master/starter-kit/webhook/README.md#creating-cloud-functions)
   2. Replace the node.js function using [action.js](https://github.com/Debdyut/IBM-Call-For-Code-High-Flyers/blob/master/action/action.js)

### 7.Integrate data sources via a Watson Assistant webhook

   1. Bring up the assistent created earlier.
   2. Click on Options on the left.
   3. Under Options > Webhooks, in the URL text box, paste the URL from the Cloud Funciton step. Make sure to add a .json at the end of the URL.
   4. Select Dialog on the left navigation.
   5. After selecting the node, click Customize.
   6. Enable Webhooks by moving the toggle button to On in the Webhooks section. Click Save.

### 8. Run the server
   To set up and launch the server application:
   1. Go to the IBM-Call-For-Code-High-Flyers/mobile-bff directory of the cloned repo.
   2. Copy the .env.example file in the IBM-Call-For-Code-High-Flyers/mobile-bff directory, and create a new file named .env.
   3. Edit the newly created .env file and update the ASSISTANT_URL, ASSISTANT_ID, and ASSISTANT_IAM_APIKEY with the values from the dialog skill's API Detail page in Watson Assistant, from Step 1. Also, update the CLOUDANT_ID and CLOUDANT_IAM_APIKEY with the values from the service credential you created in Step 2. (Note that the username from the credential is what should be used for the CLOUDANT_ID.)
   4. Edit the name value in the manifest.yml file to your application name (for example, my-app-name).
   5. From a terminal:
      * Go to the IBM-Call-For-Code-High-Flyers/mobile-bff directory of the cloned repo.
      * Install the dependencies: npm install.
      * Launch the server application locally or deploy to IBM Cloud:
        * To run locally:
            * Start the application: npm start.
            * The server can be accessed at http://localhost:3000.
        * To deploy to IBM Cloud:
            * Log in to your IBM Cloud account using the IBM Cloud CLI: ibmcloud login.
            * Target a Cloud Foundry org and space: ibmcloud target --cf.
            * Push the app to IBM Cloud: ibmcloud app push.
            * The server can be accessed at a URL using the name given in the manifest.yml file (for example, https://my-app-name.bluemix.net).

### 9. Run the mobile application
   To run the mobile application (using the Xcode iOS Simulator or Android Studio Emulator):
   1. Go to the IBM-Call-For-Code-High-Flyers/mobile-app directory of the cloned repo.
   2. Copy the .env.example file in the IBM-Call-For-Code-High-Flyers/mobile-app directory, and create a file named .env.
   3. Edit the newly created .env file:
      * Update the STARTER_KIT_SERVER_URL with the URL to the server app launched in the previous step.Note: If you are running the server locally and testing with the Android Emulator set the STARTER_KIT_SERVER_URL using the local machine's URL (e.g., http://10.0.2.2:3000) instead of localhost  
      * Update the HERE_APIKEY with the API key generated in the HERE Developer Portal.
   4. From a terminal:
      * Go to the starter-kit/mobile-app directory.
      * Install the dependencies: npm install.
      * iOS only: Go to the ios directory: cd ios.
      * iOS only: Install pod dependencies: pod install.
      * iOS only: Return to the mobile-app directory: cd ../.
      * Launch the app in the simulator/emulator:
         * iOS only: npm run iosNote: You should be running at least iOS 13.0. The first time you launch the simulator, you should ensure that you set a Location in the Features menu.  
         * Android only: npm run androidNote: Your Android Studio needs to have the Android 9 (Pie) SDK and a Pie API Level 28 virtual device

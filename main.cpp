#include <Arduino.h>

#include <ESP8266WiFi.h>
#include <Firebase_ESP_Client.h>
#include <addons/TokenHelper.h>

//HERE YOU PLACE YOUR HOTSPOT OR WIFI_NAME AND ITS PASSWORD!
#define WIFI_SSID "XXXXXX"
#define WIFI_PASSWORD "XXXXXXXXX"

//edit firebase credentials

//get the API_KEY from your firebase by going to the settings->project settings (initially you will have no web APIs) so you do the following:
//go to service accounts-> create service account-> generate new private key and choose the file to be Node.js (This will download a Node.js file that contains some configurations)
//after downloading return to the general tab in the project settings in firebase you will find a generated web-api
#define API_KEY "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

//in your firebase go to Build->Realtime Database then create a database choose american then choose test mode for rules and then copy the link generated for your database
#define DATABASE_URL "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

//get the project id from the downloaded Node.js file
#define FIREBASE_PROJECT_ID "XXXXXXXXXXXX"

//go to authentication in your firebase and then create authentication choose email/password and enable email/password
//go to users and add any testMail and testPassword
#define USER_EMAIL "XXXXXXXXXX"
#define USER_PASSWORD "XXXXXXXXXXXXXXXX"

//hardware pins according to your connection
#define TRIG_PIN 14 //D5
#define ECHO_PIN 12 //D6

//add private key (get this from the Node.js file. You will find a field that has a value identical to this) 
const char PRIVATE_KEY[] PROGMEM = "-----BEGIN PRIVATE KEY-----XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX-----END PRIVATE KEY-----\n";
FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;
FirebaseJson content;

void setup()
{
    Serial.begin(115200);
    pinMode(LED_BUILTIN, OUTPUT);
    pinMode(TRIG_PIN, OUTPUT);
    pinMode(ECHO_PIN, INPUT_PULLUP);

    //connect to wifi network
    WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
    Serial.print("Connecting to Wi-Fi");
    while (WiFi.status() != WL_CONNECTED)
    {
        digitalWrite(LED_BUILTIN, HIGH);
        Serial.print(".");
        delay(500);
        digitalWrite(LED_BUILTIN, LOW);
        Serial.print(".");
        delay(500);
    }
    Serial.printf("\nConnected with IP: %s\n", (WiFi.localIP()).toString().c_str());
    Serial.printf("Firebase Client v%s\n\n", FIREBASE_CLIENT_VERSION);

    /* Assign the api key*/
    config.api_key = API_KEY;

    /* Assign the user sign in credentials */
    auth.user.email = USER_EMAIL;
    auth.user.password = USER_PASSWORD;

    /* Assign the RTDB URL*/
    config.database_url = DATABASE_URL;

    /* Assign the callback function for the long running token generation task */
    config.token_status_callback = tokenStatusCallback;
    fbdo.setBSSLBufferSize(1024, 4096); /* Rx/Tx buffer size in bytes from 512 - 16384 */
    fbdo.setResponseSize(2048); // Limit the size of response payload to be collected in FirebaseData
    Firebase.begin(&config, &auth);
    Firebase.reconnectWiFi(true);

    millis();
}

unsigned long int printTime = millis() + 1000;
float distance = 0;
FirebaseJson json;
String path = "/";

void loop()
{

    if (Firebase.ready() && millis() > printTime)
    {
        printTime = millis() + 1000;

        digitalWrite(TRIG_PIN, HIGH);
        delayMicroseconds(10);
        digitalWrite(TRIG_PIN, LOW);
        delayMicroseconds(10);

        distance = pulseIn(ECHO_PIN, HIGH) * 0.0343/2;

        json.setFloatDigits(5);
        json.add("distance", distance);

        //write opration
        if( Firebase.RTDB.setJSON(&fbdo, path.c_str(), &json) )
        {
            Serial.printf("Set json... ok\n");
        }
        else
        {
            Serial.printf("Set json... %s\n", fbdo.errorReason().c_str());
        }

        //read opration
        if( Firebase.RTDB.getJSON(&fbdo, path.c_str()) )
        {
            Serial.printf("Get json... %s\n", fbdo.to<FirebaseJson>().raw());
        }
        else
        {
            Serial.printf("Get json... %s\n", fbdo.errorReason().c_str());
        }
    }
}
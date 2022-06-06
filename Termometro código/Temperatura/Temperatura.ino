
#include <WiFi.h>
#include <geniotmqtt_ESP32.h>
#include <PubSubClient.h>
#include <OneWire.h>
#include <DallasTemperature.h>
#include <esp_task_wdt.h>

OneWire barramento(02);


DallasTemperature sensor(&barramento);

/*
  SSID e Password da rede WiFi
*/
const char* ssid      = "SSD";
const char* password  = "SENHA";
  
const char * Temperatura  = "temperatura";  

const char * ClientID     = "Client1"; //defina um nome único para cada conexão/device!!
 
String valor;
String valor2;
String valor3;
 
WiFiClient espClient;
Mqttlib client(espClient);

void setup() {
   Serial.begin(115200);
   sensor.begin();
  
   esp_task_wdt_init(20, true);
  esp_task_wdt_add(NULL);
  WiFi.begin(ssid, password);
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");
 
  while (!client.connected()) {
    Serial.println("Connecting to MQTT...");
 
    /*
       No Mqtt a conexão cada dispositivo conectado ao broker precisa ter um nome único.
       Por esse motivo, cada device precisa ter um nome diferente defino em "ClientID_GUID" 
    */
    if (client.connect(ClientID, "user1", "321")) {  // usuário e senha MQTT exemplo: "user1"  ,  "321", caso não tenha apague
      Serial.println("connected");  
 
    } else {
 
      Serial.print("failed with state ");
      Serial.print(client.state());
      delay(2000);
 
    }
  }
 
}

boolean reconnect() {
  return client.connected();
    
}

void loop() {

  static const long interval = 60000;  
  static unsigned long previousMillis = 0, currentMillis;
  currentMillis = millis();
   float temp = sensor.getTempCByIndex(0);
   
   
   
   sensor.requestTemperatures();

    valor = temp;
     
         
      char message[58];
      
     
      valor.toCharArray(message,58);
    
     
     
  if(currentMillis - previousMillis >= interval) 
   {
         previousMillis = currentMillis;
    client.publish(Temperatura, message);
         hold(100);
         Serial.print("t1: ");
    Serial.println(sensor.getTempCByIndex(0));

    
   
     

  
    
   }

  if(!client.connected()) {
WiFi.begin(ssid, password);
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");
 
  while (!client.connected()) {
    Serial.println("Connecting to MQTT...");
 
    /*
       No Mqtt a conexão cada dispositivo conectado ao broker precisa ter um nome único.
       Por esse motivo, cada device precisa ter um nome diferente defino em "ClientID_GUID" 
    */
    if (client.connect(ClientID, "user1", "321")) {  //exemplo: "user1"  ,  "321"
      Serial.println("connected");  
 
    } else {
 
      Serial.print("failed with state ");
      Serial.print(client.state());
      delay(2000);
 
    }
  }
   
    }  

    client.loop();
     esp_task_wdt_reset();
}
 void hold(const unsigned int &ms) { //
// Non blocking delay
unsigned long m = millis();
while (millis() - m < ms) {
yield();
}
}

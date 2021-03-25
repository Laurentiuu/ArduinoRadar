#include <Servo.h> 

Servo myServo;
int startUngle=0;
const int servoPin = 8;

const int trigPin = 6;
const int echoPin = 7;

long duration;  //timpul in care senzorul primeste sunetul
int distance;   //distanta obiectului

void setup() 
{ 
  Serial.begin(9600);
  
  myServo.attach(servoPin);  //folosesc pinul 9 pentru controlul motorasului
  myServo.write(startUngle);   //initializez pozitia de start a motorasului

  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  
} 

void loop() {
  //fac o rotatie de 180 de grade
  delay(10);
  for(int ungle=0;ungle<=180;ungle++){  
    myServo.write(ungle);
    delay(50);    
    distance=getDistance();
    Serial.print(String(ungle));
    Serial.print(",");
    Serial.print(String(distance));
    Serial.print(",");
    Serial.print(String(1));     
    Serial.println(".");
  }
  
  //timpul de asteptare dintre rotatii
  delay(500);
  
  //fac o rotatie inversa de 180 de grade
  for(int ungle=180;ungle>0;ungle--){
    myServo.write(ungle);
    delay(50);
    distance=getDistance();
    Serial.print(String(ungle));
    Serial.print(",");
    Serial.print(String(distance));   
    Serial.print(",");
    Serial.print(String(0)); 
    Serial.println(".");
  }
  delay(500);
} 

int getDistance(){
  int dist;
  //setez trigPin-ul pe LOW
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);

  //generez unda de ultrasunet care dureaza 10 microsecunde
  //dupa care setez semnalul din nou pe LOW
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  //functia care citeste un puls de pe un pin atunci cand este pe HIGH
  //dupa care generez distanta dupa formula: timp=dist/vit => dist=timp*vit
  //distanta=timpulUltrasunetului * vitezaSunetuluiInMicrosecunde/2
  //impart la 2 deoarece sunetul face un drum dus-intors
  duration = pulseIn(echoPin, HIGH);
  dist = duration * 0.034/2;

  return dist;
}

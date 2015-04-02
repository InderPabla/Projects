//Arduino pins 2,4,6,8
int pin1 = 2;
int pin2 = 4;
int pin3 = 6;
int pin4 = 8;

void setup() {
  //sets pins 1 and 2 to high. High = short circuit, thus no power to RC car
  pinMode(pin1,OUTPUT); 
  pinMode(pin2,OUTPUT);
  digitalWrite(pin1,HIGH); //power wheel 1
  digitalWrite(pin2,HIGH); //power wheel 2
  
  //sets pins 3 and 4 to high. High = short circuit, thus no power to RC car
  pinMode(pin3,OUTPUT);
  pinMode(pin4,OUTPUT);
  digitalWrite(pin3,HIGH); //steer wheel 1
  digitalWrite(pin4,HIGH); //steer wheel 2
  
  Serial.begin(9600);
  Serial.println("--Serial Started--");
}//setup method

void loop() {
    while (Serial.available()) { 
        char readChar = (char)Serial.read(); 
        if (readChar == '0') {
            digitalWrite(pin1,HIGH);
            digitalWrite(pin2,HIGH); 
        }//stop
        if (readChar == '1') {
            digitalWrite(pin1,LOW);
            digitalWrite(pin2,HIGH);  
        }//fwd
       
        if (readChar == '2') {
            digitalWrite(pin1,HIGH);
            digitalWrite(pin2,LOW);  
        }//back   
       
        if (readChar == '3') {
            digitalWrite(pin3,HIGH);
            digitalWrite(pin4,HIGH);  
        }//wheel reset
        if (readChar == '4') {
            digitalWrite(pin3,LOW);
            digitalWrite(pin4,HIGH);  
        }//right
       
        if (readChar == '5') {
            digitalWrite(pin3,HIGH);
            digitalWrite(pin4,LOW);  
        }//left      
    }//get serial information
}//loop method
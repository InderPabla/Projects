#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/stat.h>
#include <pthread.h>

struct my_msgbuf {
    long mtype;
    char mtext[1000] ;
}; //message buffer

struct my_msgbuf buf;

key_t key, key2; //recieve and send key

int state = 0; //states inside thread, (used like mutex)
int recieveMsqid,sendMsqid; //recieve and send msqid

char userPassword[12]; //password
char userPassword2[12]; //temporary password
char uppercasePassword[12]; //uppercased password
char rndString[100]; //random 32 bit integer in string form
char b0[5];char b1[5];char b2[5]; // Stores the original password (in three blocks).
char h0[5];char h1[5];char h2[5]; // Stores the hashed password (in three blocks).
char h[13]; //Combination of h0,h1,h2


void E(char *in, char *out)
{
    out[0]=(in[0]&0x80)^(((in[0]>>1)&0x7F)^((in[0])&0x7F));
    out[1]=((in[1]&0x80)^((in[0]<<7)&0x80))^(((in[1]>>1)&0x7F)^((in[1])&0x7F));
    out[2]=((in[2]&0x80)^((in[1]<<7)&0x80))^(((in[2]>>1)&0x7F)^((in[2])&0x7F));
    out[3]=((in[3]&0x80)^((in[2]<<7)&0x80))^(((in[3]>>1)&0x7F)^((in[3])&0x7F));
}//E, 4 letter hash creator

void uppercaseString(char *in, char*out){
    int i =0;
    int done = 0; //0 = no null appeard yet, 1 = null pad the rest of the password
    for(i=0;i<12;i++){
        if(done==0){
            if (isalpha(in[i])) {
                out[i] = toupper(in[i]);
            } //if alphabet then uppcase it
            else if (isdigit(in[i])) {
                out[i] = in[i];
            } //if digit then leave it as a digit
            else {
                done = 1;
                out[i] = '\0'; //null this character
            }//password ended, done = 1
        } //if done = 0 then null
        else{
            out[i] = '\0';
        } //if done = 1 then null pad rest
    } //uppercase characters of the password
}//uppercaseString, turns a given string into all uppercase.

void string_To_blocks(char *in, char*out1, char*out2, char*out3){
    int i =0;

    for(i=0;i<4;i++){
        out1[i] = in[i];
    } //first block from index 0 - 3
    out1[4] = '\0'; //null 5th location

    for(i=4;i<8;i++){
        out2[i-4] = in[i];
    } //second block from index 4 - 7
    out2[4] = '\0'; //null 5th location

    for(i=8;i<12;i++){
        out3[i-8] = in[i];
    } //third block from index 8 - 11
    out3[4] = '\0'; //null 5th location
}//string_To_Blocks, split the password into three blocks.


void blocks_To_String(char *out, char*in1, char*in2, char*in3){
    int i =0;

    for(i=0;i<4;i++){
        out[i] = in1[i];
    } //first block from index 0 - 3

    for(i=4;i<8;i++){
        out[i] = in2[i-4];
    } //second block from index 4 - 7

    for(i=8;i<12;i++){
        out[i] = in3[i-8];
    } //third block from index 8 - 11

    out[12] = '\0'; //null last 13th location

}//blocks_To_String, combine the three blocks containing the password into one array.


void encryptBlocks(char *in1,char *in2,char *in3,char *out1,char *out2,char *out3){
    E(in1,out1); //Encrypt block 1
    out1[4] = '\0';
    E(in2,out2); //Encrypt block 2
    out2[4] = '\0';
    E(in3,out3); //Encrypt block 3
    out3[4] = '\0';
}//encryptBlocks, Hash all three password blocks.

void createMsgReceiveQ(){
    char *k = "-13";
    if (atoi(k) == 0){
        printf("invalid argument\n");
        exit(-1);
    }
    key2 = atoi(k);
    if ((recieveMsqid = msgget(key2,0644 | IPC_CREAT | S_IROTH | S_IWOTH)) == -1) {//create recieve queue
        perror("msgget");
        exit(1);
    }
}//createMessageRecieveQ, recieve messages from client on key -13


void connectToServerMsgQ()
{
    char *k = "-12";
    if (atoi(k) == 0){
        printf("invalid argument\n");
        exit(-1);
    }
    key = atoi(k);

    if ((sendMsqid = msgget(key, 0644)) == -1){ // connect to the queue
        perror("msgget");
        exit(1);
    }
}//connectToServerMsgQ, connect to server's message queue at key -12

void sendMessageToServer(){
    int len = strlen(buf.mtext);
    if (buf.mtext[len-1] == '\n') buf.mtext[len-1] = '\0'; //append buf.mtext with null

    if (msgsnd(sendMsqid, &buf, len+1, 0) == -1) //send message to server
        perror("msgsnd");
} //sendMessageToServer, send buf.mtext to server


void getUsername(char* username){
    int loop = 0;
    while(loop==0){
        printf("Enter Username: ");
        fgets (username, 32, stdin);  // Read username from stdin and limit it to 32 characters.
        strtok(username,"\n"); // Remove the new line character which was added by fgets.
        if(strlen(username)<4 || strlen(username)>32){
            if(strlen(username)<4) //username less than 4 characters
                printf("Username too small. Must be larger than 4 characters\n");
            if(strlen(username)>32) //username greater than 32 characters
                printf("Username too big. Must be smaller than 32 characters\n");
        }// if username too small or too big, prompt client again.
        else{
            loop = 1;
        }// if username matches restriction stop loop
    }//loop == 0
}//getUsername, prompts the user to enter a username.


void getPassword(char* password){
    char passwordTemp[100]; //temporary password
    int i =0;

    printf("Enter Password: ");

    fgets(passwordTemp,100,stdin); // Read the password from stdin and limit it to 12 characters + '\0'.

    strtok(passwordTemp,"\n");    // Remove the new line character which was added by fgets.

    strncpy(password,passwordTemp,12); //copy temorary password into password

    for(i = strlen(password);i<12;i++){
            password[i] = '\0';
    }//null pad rest of the unused password

}//getPassword, prompts the user to enter a password.


void passwordHandeler(char* userPass, char* pass2, char* b0, char* b1, char* b2, char *h0, char* h1, char* h2, char *h){
        getPassword(userPass); //get and autenticate password
        uppercaseString(userPass, pass2); //Convert the user's entered password into all uppercase
        string_To_blocks(pass2,b0,b1,b2); //Split the password into three blocks.
        encryptBlocks(b0,b1,b2,h0,h1,h2); //Hash all three blocks of the password.
        blocks_To_String(h,h0,h1,h2); //Put slipt hash blocks into one hash string
}//passwwordHandler, convert password into hashed password

void XOR(){
    int i = 0;
    char hashOrg[30]; //original hash
    char hashRnd[100]; //random hash
    strncpy(hashOrg,h,strlen(h));
    for(i = 0;i<strlen(hashOrg);i++){
        hashRnd[i] = hashOrg[i] ^ rndString[i]; //randomHash = originalHash XOR key
    }
    hashRnd[i] = '\0';
    for(i = i;i<100;i++){
        hashRnd[i] = '\0'; //null pad rest of random hash
    }
    memset(buf.mtext,'\0',100);//null buf.mtext
    strncpy(buf.mtext,hashRnd,strlen(h)); //copy random hash into buf.mtext to be sent to server
    sendMessageToServer(); //send message to server
}//XOR, XOR's hash with random number string and send to server

void passwordAuthenticate(){
    int loop2 = 0;
    strncpy(rndString,buf.mtext,strlen(buf.mtext)); //copy buf.mtext into rndString to be used for XOR
    memset(buf.mtext,'\0',100); //null buf.mtext
    while(loop2 == 0){
        char userPassword2[12]; //temporary userpassword
        passwordHandeler(userPassword,uppercasePassword,b0,b1,b2,h0,h1,h2,h); //get password hash into h
        printf("Re-"); //Again
        passwordHandeler(userPassword2,uppercasePassword,b0,b1,b2,h0,h1,h2,h);// again get password hash into h
        if(strcmp(userPassword,userPassword2)==0){
            loop2=1;
        }//if both passwords don't match then end loop
        else{
           printf("Entered password's don't match.\n");
        }//if password's don't match, run loop again
    }//loop == 0
    XOR(); //XOR and send random hash to server
}//passwordAuthenticate, get password, match password, hash password, XOR password with random number, send it to server

void *receiveMessage(void *num){
    int i = 0;
    int loop = 1;
    while(loop==1){
        if (msgrcv(recieveMsqid, &buf, sizeof(buf.mtext), 0, 0) == -1) {
            perror("msgrcv");
             exit(1);
        }//exit if error
        else{
            if(strncmp(buf.mtext,"--NewPasswordCreated--", strlen(buf.mtext))==0){
                printf("Access Granted.\n"); //access is granted
                exit(0);
            }// severs tells user access is granted and new password is set
            else if(state==1){
                printf("\n--Create new password--\n");
                passwordAuthenticate();
            }//create new password and send it to server
            else if(strncmp(buf.mtext,"--PasswordMatched--", strlen(buf.mtext))==0){
                state = 1;
            }// server tells user they are found in the table and to prepare for new password
            else if(strncmp(buf.mtext,"--LockedOut--", strlen(buf.mtext))==0){
                printf("Access Denied.\n"); //access is denied
                exit(0);
            }// server tells user they are locked out and access is denied
            else if(state ==0){
                passwordAuthenticate();
            }//send password to server for autentication
        }// if no error
    }// loop == 1
}//recieveMessage, run by a thread to handel client server interation

int main(void){
    int i,rc;
    pthread_t checker; //Thread for message recieve

    buf.mtype = 1; //message type 1, doesn't matter, 1 = normal

    connectToServerMsgQ(); //connect to server to server data
    createMsgReceiveQ(); //Create message recieve queue

    rc = pthread_create(&checker, NULL, receiveMessage, (void *)0); //created thread to run recieveMessage method

    getUsername(buf.mtext); //get username
    sendMessageToServer(); //send username to server

    while(1){} //keep client running
}

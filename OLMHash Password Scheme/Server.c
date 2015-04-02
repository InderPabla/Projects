#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/stat.h>
#include <pthread.h>
#define SIZE 50 //Number of allowed accounts

struct my_msgbuf {
    long mtype;
    char mtext[1000] ;
}; //message buffer

struct my_msgbuf buf;

key_t key, key2; //recieve and send key

int recieveMsqid,sendMsqid; //recieve and send msqid
int usermatch = 0; //0 = no username exists in table, 1 = username does exist in table
int matchIndex = 0; //index where usename exits
int line = 0; //number of lines inside the file
int loop = 1; //to keep thread looping
int state = 0; //states inside thread, (used like mutex)
int lockedOut = 0; //state of user, 0 = allowed, 1 = locked
int wrongTry = 0; //number of tries
int locked[SIZE]; //Locked out table

char rndString[100]; //random 32 bit integer in string form
char userID[32]; //username
char caculatedHash[100]; //hash calulated from reverse XOR
char *tables[SIZE][2]; // Tables which holds the list of usernames and hashed passwords.


void createMsgReceiveQ(){
    char *k = "-12";
    if (atoi(k) == 0){
        printf("invalid argument\n");
        exit(-1);
    }
    key = atoi(k);
    if ((recieveMsqid = msgget(key,0644 | IPC_CREAT | S_IROTH | S_IWOTH)) == -1) {//create recieve queue
        perror("msgget");
        exit(1);
    }
}//createMessageRecieveQ, recieve messages from client on key -12

void sendMessageToClient(){
    int len = strlen(buf.mtext);
    if (buf.mtext[len-1] == '\n') buf.mtext[len-1] = '\0'; //append buf.mtext with null

    if (msgsnd(sendMsqid, &buf, len+1, 0) == -1) //send message to client
        perror("msgsnd");
    printf("--> Message Sent To Client: %s\n",buf.mtext);
}//sendMessageToClient, sends buf.mtext to client

void connectToClientMsgQ()
{

    char *k = "-13";
    if (atoi(k) == 0){
        printf("invalid argument\n");
        exit(-1);
    }
    key2 = atoi(k);
    if ((sendMsqid = msgget(key2, 0644)) == -1){ // connect to the queue
        perror("msgget");
        exit(1);
    }
}//connectToClientMsgQ, connect to client's message queue at key -13


void reverseXOR(){
	int i = 0;
	printf("Random Hash:  %s\n",buf.mtext);
    char hashGetBack[100];
    for(i = 0;i<strlen(buf.mtext);i++){
        hashGetBack[i] = buf.mtext[i] ^ rndString[i]; //get orginal hash, original = randomHash XOR key
        caculatedHash[i] = hashGetBack[i];
    }
    caculatedHash[strlen(buf.mtext)] = '\0';
    printf("Original Hash: %s\n",caculatedHash);

} //reverseXOR, reverse the XOR done by client

void sendClientRandomNumber(){
    int rnd = 0;
    rnd = rand();
    snprintf(buf.mtext, 100,"%d",rnd); //put random number in buf.mtext to send to client
    snprintf(rndString, 100,"%d",rnd); //put random number in rndString for later use inorder to reverse XOR
    sendMessageToClient(); //send message to client
}//sendClientRandomNumber, send client 32 bit random number in string from

void messageSender(char* reason){
	char matchStr[100];
	if(strcmp(reason,"NewPassword")==0){
		strcpy(matchStr,"--NewPasswordCreated--");
		state = 0;
	}// request new password
	else if(strcmp(reason,"LockedOut")==0){
		strcpy(matchStr,"--LockedOut--");
		state = 0;
	}// let client know they are locked out of the system
	else if(strcmp(reason,"MatchedPassword")==0){
		strcpy(matchStr,"--PasswordMatched--");
		state =  2;
	}//let client know the old password matches that of the table
	memset(buf.mtext,'\0',100);
    strncpy(buf.mtext,matchStr,strlen(matchStr));
    sendMessageToClient(); //send message to client
}// messaSender, send specific messages to client to request specific infrormations

void writeToFile(int state){
	int i = 0;
	if(state==1){
		tables[line/2][0] = malloc(sizeof(char) * 32); //initilize location for username
		tables[line/2][1] = malloc(sizeof(char) * 12); //initilize location for passworded hash
		strncpy(tables[line/2][0], userID, 32); //copy username into the table
		strncpy(tables[line/2][1], caculatedHash, 12); //copy passworded hash into the table
		line+=2; //update line
	}//malloc a new locations in the table

 	FILE *fw = fopen("account.txt", "w"); //open file for write
    for(i = 0; i<line/2;i++){
        fprintf(fw,"%s\n",tables[i][0]);
        fprintf(fw,"%s\n",tables[i][1]);
    } //write username and passworded hash into the table
    fclose(fw); //close file
}//writeToFile, write tables to file

void searchTable(){
    int i = 0;
    for(i = 0;i<SIZE;i++){
        if(tables[i][0]!=NULL){
            int len = 0;
            //find largest length of the two
            if(strlen(tables[i][0])>strlen(userID))
                len = strlen(tables[i][0]);
            else
                len = strlen(userID);

            if(strncmp(tables[i][0],userID, len)==0){
                matchIndex= i;
                usermatch = 1; //user found
                if(locked[matchIndex] == 1){
                    messageSender("LockedOut");
                    lockedOut = 1; //locked
                }//let client know this user is locked out
                break; //stop
            }// if userID entered by client match that of the table
        }//if username exists
        else{
            break; //stop
        }// if table's username is null then no point serching on
    }//serch table
}//searchTable, search table for username

void *receiveMessage(void *num){
    int i = 0;
    while(loop==1){
        if (msgrcv(recieveMsqid, &buf, sizeof(buf.mtext), 0, 0) == -1) {
            perror("msgrcv");
             exit(1);
        }//exit if error
        else{
            printf("<-- Message Recieved From Client: %s\n",buf.mtext);
            if(state==2){
                reverseXOR(); //reverse XOR the random hash
                strncpy(tables[matchIndex][1], caculatedHash, 12);  //copy calulated hash into table
                writeToFile(0); //write to file
                messageSender("NewPassword"); //let client know password is saved
            }//when state = 0, new password revieved and saved
            else if(state==0){
                usermatch = 0; //reset usermatch
                wrongTry = 0; //wrong number of tries set to 0 for new user
                lockedOut = 0; //state of user, 0 = allowed, 1 = locked
                strncpy(userID,buf.mtext,strlen(buf.mtext)); //

                searchTable();
                if(lockedOut==0){
                    connectToClientMsgQ(); //connect to client to send data
                    sendClientRandomNumber(); //send client 32 bit random number in string
                    state = 1;
                } //if user is not locked out, then continue to state 1 of password authentication
            }//when state = 0, initial state when user enters in there username
            else if(state==1){
                reverseXOR(); //reverse XOR the random hash
                searchTable();

                if(usermatch==0){
                    writeToFile(1);
                    messageSender("NewPassword");
                }//if user not found in the table then request user for new password
                if(usermatch==1 && locked[matchIndex]==0){
                    if(strncmp(tables[matchIndex][1],caculatedHash, strlen(tables[matchIndex][1]))==0){
                        messageSender("MatchedPassword");
                        sendClientRandomNumber(); //
                    }//get new password from client if old password matches
                    else{
                        sendClientRandomNumber();
                        wrongTry++; //update wrong counter
                        if(wrongTry==2){
                            locked[matchIndex] = 1;
                            wrongTry = 0; //reset wrong try
                        } //if 3 wrong attemps lockout user
                    }//wrong password
                }//if user is found on table and not locked out of the system

            }//when state = 1, when user enters in password.
        }//no error
    }// loop == 1
} //recieveMessage, run by a thread to handel client server interation




int main(void){
    int rc,i;
    char localGet[32]; //temp variable
    pthread_t checker; //Thread for message recieve

    memset(locked,0,SIZE); //All values in locked set to 0
    srand(time(NULL)); //Seed time to random number generation
    createMsgReceiveQ();  //Create message recieve queue
    rc = pthread_create(&checker, NULL, receiveMessage, (void *)0); //created thread to run recieveMessage method

    if(fopen("account.txt", "r")!=NULL){
        FILE *fr = fopen("account.txt", "r"); //Open file for read

        while(fscanf(fr,"%s",localGet)>=0){
            line++;
        }//Get total results in the file

        fclose(fr);//Close file

        fr = fopen("account.txt", "r"); //Open file for read again
        for(i=0;i<line/2;i++){
            tables[i][0] = malloc(sizeof(char) * 32); //Username size 32
            fgets (tables[i][0], 33, fr );
            strtok(tables[i][0],"\n");

            tables[i][1] = malloc(sizeof(char) * 12); //Password hash size 12
            fgets (tables[i][1], 13, fr );
            strtok(tables[i][1],"\n");
        }//Put results into tables 2D array
        fclose(fr);//Close file
    }// if account exists
    while(1){} //keep server running
}//main method

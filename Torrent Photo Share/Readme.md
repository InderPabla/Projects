## Torrent photo share
Torrent photo share is a peer-to-peer photo sharing service. It allows transfer of photos between peers using central DHT server system to keep track of all files. Peers can upload file names to DHT, and other peers can access these files by prompting DHT the name of the file and the IP of the peer which has the file. Then peer which wants the file can connect to the server side of the peer which has the file and download it. 
######(For more information, please check out the pseudo code)

## Setup
####Platform - Eclipse 
Create A Project in Eclipse
Step 1: Start Eclipse on a computer and create a workspace

Step 2: Go to File -> New -> Project.


Step 3: New window will pop up called New Project. Select Java Project, and then click Next.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/1.PNG "Optional Title") 

Step 4: A new window will pop up named New Java Project. Name your project anything you wish. Pick version JavaSE-1.7, and then click Finish. 
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/2.PNG "Optional Title") 

Step 5: You are now done. You should have a new project under Project Explorer, on the top-left side of the window. 
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/3.PNG "Optional Title")  

How to implement DirectoryServer.java

Step 1: Copy DirectoryServer.java.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/4.PNG "Optional Title") 

Step 2: Paste it into src folder under your project folder in Eclipse, and then double click it to open the file.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/5.PNG "Optional Title")   

---Setting up Arguments On DirectoryServer.java---
Step 3: Right click DirectoryServer.java, and go down to Properties.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/6.PNG "Optional Title") 

Step 4: A new window pop up called Properties for DirectoryServer.java. Click Run/Debug Settings, and then click New.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/7.PNG "Optional Title") 

Step 5: A new window will pop up called Select Configuration Type. Select Java Application and click Ok.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/8.PNG "Optional Title") 

Step 6: A new window will pop up called Edit Configurations. Click Arguments. 
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/9.PNG "Optional Title") 

The format for arguments on DirectoryServer.java is:
ServerPort ServerID SuccessorServerPort SuccessorServerIP
Example: 40340 1 40340 ENG201-25
-	In this example, the main port on this server is 40340, it is the first server on the DHT, it’s successor server’s (second server on the DHT) main port is 40340 and it’s successor server’s IP name is ENG201-25.

Step 7: Under Program arguments, type the argument. Then click Apply and then click OK. Now you will be back on Properties for DirectoryServer.java window, again click Apply and then click OK.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/10.PNG "Optional Title")  

Step 8: Repeat Step 1-7 on three other computers, to get a total of 4 Directory Server’s.
Remember, the successor server of Server 4 will be Server 1. This will ensure the DHT is connected in a ring system. 

-After you are done, simply click the Run button on each computer, to start the servers. 
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/11.PNG "Optional Title") 

-In the Console, you will see the program start. 
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/12.PNG "Optional Title") 

How to implement PeerClient.java and PeerServer.java

Step 1: Copy both PeerClient.java and PeerServer.java.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/13.PNG "Optional Title") 

Step 2: Paste them into src folder under your project folder in Eclipse, and then double click them to open the files.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/14.PNG "Optional Title")  

	
---Setting up Arguments on PeerClient.java---
	
Step 3: Right click PeerClient.java, and go down to Properties.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/15.PNG "Optional Title") 

Step 4: A new window pop up called Properties for PeerClient.java. Click Run/Debug Settings, and then click New.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/16.PNG "Optional Title") 

Step 5: A new window will pop up called Select Configuration Type. Select Java Application and click Ok.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/17.PNG "Optional Title") 

Step 6: A new window will pop up called Edit Configurations. Click Arguments. 
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/18.PNG "Optional Title") 

The format for arguments on PeerClient.java is:
PeerServerPort ServerOneIP ServerOneMainPort
Example: 40340 ENG201-19 40340
-	In this example, Peer Server will use port 40340 for other client to connect for file transfer. The last two arguments are first server on the DHT’s IP and its main port number.

Step 7: Under Program arguments, type the argument. Then click Apply and then click OK. Now you will be back on Properties for PeerClient.java window, again click Apply and then click OK.
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/19.PNG "Optional Title")   


Step 8: Repeat Step 1-7, for all other client that you wish to connect to the DHT.

-First start your servers. 
-After you are done, simply click the Run button on each computer to start the client. 
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/20.PNG "Optional Title") 

-In the Console, you will see the program start. 
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/21.PNG "Optional Title") 

3 Options For Client
Upload 
A client chooses U, and then entered fig1. Corresponding hashed Directory Server has successfully added the content to its list.   
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/22.PNG "Optional Title")

Query
Client 1 chooses U, and then entered tree. Corresponding hashed Directory Server has successfully added the content to its list.   
![Alt text](https://github.com/InderPabla/Projects/blob/master/Torrent%20Photo%20Share/Images/23.PNG "Optional Title")

Client 2 chooses Q, then entered tree.  We can see Directory Server’s message of content found. New connection created by Peer Server to service file exchange on a different socket. As well as GET message sent to Peer Server from retrieved IP using new socket and HTTP response message from Peer Server’s new socket. This client then chose to exit. 
 
Exit
A client has uploaded multiple files. Then press E to exit. All contents uploaded are successfully removed.

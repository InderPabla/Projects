Platform - Eclipse 

Create A Project in Eclipse
Step 1: Start Eclipse on a computer and create a workspace

Step 2: Go to File -> New -> Project.


Step 3: New window will pop up called New Project. Select Java Project, and then click Next.
![ScreenShot](https://github.com/InderPabla/Projects/tree/master/Torrent%20Photo%20Share/Images/1.PNG) 

Step 4: A new window will pop up named New Java Project. Name your project anything you wish. Pick version JavaSE-1.7, and then click Finish. 
 

Step 5: You are now done. You should have a new project under Project Explorer, on the top-left side of the window. 
  

	
 
How to implement DirectoryServer.java

Step 1: Copy DirectoryServer.java.
 

Step 2: Paste it into src folder under your project folder in Eclipse, and then double click it to open the file.
   

---Setting up Arguments On DirectoryServer.java---
Step 3: Right click DirectoryServer.java, and go down to Properties.
 

Step 4: A new window pop up called Properties for DirectoryServer.java. Click Run/Debug Settings, and then click New.
 

Step 5: A new window will pop up called Select Configuration Type. Select Java Application and click Ok.
 

Step 6: A new window will pop up called Edit Configurations. Click Arguments. 
 

The format for arguments on DirectoryServer.java is:
ServerPort ServerID SuccessorServerPort SuccessorServerIP
Example: 40340 1 40340 ENG201-25
-	In this example, the main port on this server is 40340, it is the first server on the DHT, it’s successor server’s (second server on the DHT) main port is 40340 and it’s successor server’s IP name is ENG201-25.

Step 7: Under Program arguments, type the argument. Then click Apply and then click OK. Now you will be back on Properties for DirectoryServer.java window, again click Apply and then click OK.
  

Step 8: Repeat Step 1-7 on three other computers, to get a total of 4 Directory Server’s.
Remember, the successor server of Server 4 will be Server 1. This will ensure the DHT is connected in a ring system. 

-After you are done, simply click the Run button on each computer, to start the servers. 
 

-In the Console, you will see the program start. 
 

How to implement PeerClient.java and PeerServer.java

Step 1: Copy both PeerClient.java and PeerServer.java.
 

Step 2: Paste them into src folder under your project folder in Eclipse, and then double click them to open the files.
  

	
---Setting up Arguments on PeerClient.java---
	
Step 3: Right click PeerClient.java, and go down to Properties.
 


Step 4: A new window pop up called Properties for PeerClient.java. Click Run/Debug Settings, and then click New.
 

Step 5: A new window will pop up called Select Configuration Type. Select Java Application and click Ok.
 

Step 6: A new window will pop up called Edit Configurations. Click Arguments. 
 

The format for arguments on PeerClient.java is:
PeerServerPort ServerOneIP ServerOneMainPort
Example: 40340 ENG201-19 40340
-	In this example, Peer Server will use port 40340 for other client to connect for file transfer. The last two arguments are first server on the DHT’s IP and its main port number.

Step 7: Under Program arguments, type the argument. Then click Apply and then click OK. Now you will be back on Properties for PeerClient.java window, again click Apply and then click OK.
   


Step 8: Repeat Step 1-7, for all other client that you wish to connect to the DHT.

-First start your servers. 
-After you are done, simply click the Run button on each computer to start the client. 
 

-In the Console, you will see the program start. 
 

3 Options For Client
Upload 
A client chooses U, and then entered fig1. Corresponding hashed Directory Server has successfully added the content to its list.   
 
Query
Client 1 chooses U, and then entered tree. Corresponding hashed Directory Server has successfully added the content to its list.   
 
Client 2 chooses Q, then entered tree.  We can see Directory Server’s message of content found. New connection created by Peer Server to service file exchange on a different socket. As well as GET message sent to Peer Server from retrieved IP using new socket and HTTP response message from Peer Server’s new socket. This client then chose to exit. 
 
Exit
A client has uploaded multiple files. Then press E to exit. All contents uploaded are successfully removed.
 

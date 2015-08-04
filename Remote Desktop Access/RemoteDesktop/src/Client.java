import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;


public class Client
{
	int port;
	float ratio;
	
	boolean isRunning = true;
	boolean errorHasOccurred = false;
	
	String IP;
	String errorInformation = "";
	
	Dimension screenSize = null;
	Dimension viewSize = null;
	Point viewPoint = null;
	
	Thread imageThread = null;	
	
	Socket socket;	
	DatagramSocket clientUDPSocket;
	
	DataInputStream inputStream;	
	
	BufferedImage bufferedImage = null; 

	public Client(int port, String IP)
	{
		this.port = port;
		this.IP = IP;
		screenSize = new Dimension(0,0);
		viewSize = new Dimension(0,0);
		viewPoint = new Point(0,0);
		
		init();
		imageThread = new Thread(imageRunnable);
        imageThread.start();
	}
	
	public void init()
	{
		try
        {
            socket = new Socket(IP, port);
            clientUDPSocket = new DatagramSocket();
            inputStream = new DataInputStream(socket.getInputStream());
            
            screenSize.width = inputStream.readInt();
            screenSize.height = inputStream.readInt();
            viewSize.width = screenSize.width;
            viewSize.height = screenSize.height;
            ratio = (float)screenSize.width/(float)screenSize.height;
            socket.close();               
        }
        catch(Exception error)
        {
        	errorHasOccurred = true;
			isRunning = false;
			errorInformation = "Error During Initialize Thread";
        }
	}
	
	Runnable imageRunnable = new Runnable()
	{
		public void run ()
        {
			try
            {
				socket = new Socket(IP, port);
				inputStream = new DataInputStream(socket.getInputStream());
				while(isRunning)
				{
					int size = inputStream.readInt();
                    byte[] imgBytes = new byte[size];
                    inputStream.readFully(imgBytes);
                    InputStream in = new ByteArrayInputStream(imgBytes);
                    bufferedImage = ImageIO.read(in);
				}
            }
			catch(Exception error)
			{
				errorHasOccurred = true;
				isRunning = false;
				errorInformation = "Error During Image Thread";
			}
        }
	};
	
	public void sendUDPCaptureMessage(int x1, int y1, int x2, int y2)
	{
		String message = "Cap "+x1+" "+y1+" "+x2+" "+y2+" PADING";
		System.out.println("("+x1+","+y1+") "+" ("+x2+","+y2+") "+message.getBytes().length);
		
		try
		{
			sendDataToServer(message, IP, port);
		}
		catch(Exception error)
		{
			System.out.println("Unable to send capture message.");
		}
	}
	
	public void sendDataToServer(String message, String serverIP, int serverPort) throws IOException 
	{
		byte[] sendData = new byte[1024];
		sendData = message.getBytes();
		InetAddress internetAddress = InetAddress.getByName(serverIP);
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, internetAddress, serverPort);
		clientUDPSocket.send(sendPacket);
	}
	
}

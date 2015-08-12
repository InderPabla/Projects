import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;



public class RemoteDesktopServer 
{
	public static void main(String args[])
	{
		Server server = new Server();
	}
	
	public static class Server
	{
		int port = 6000;
		
		//String IP = "192.168.0.14";
		
		Dimension screenSize = null;
		
		ServerSocket serverSocket;
		DatagramSocket udpServerSocket;
		Socket socket;	
		
		Thread imageThread = null;	
		Thread udpThread = null;	
		
		DataInputStream inputStream;	
		DataOutputStream outputStream;	
		
		boolean isRunning = true;
		
		Rectangle captureArea = null;
		
		Robot robotMove;
		
		public Server()
		{
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			captureArea = new Rectangle(0,0,screenSize.width,screenSize.height);
			init();
			imageThread = new Thread(imageRunnable);
	        imageThread.start();
	        
	        udpThread = new Thread(udpRunnable);
	        udpThread.start();
		}
		
		public void init()
		{
			try
			{
				serverSocket = new ServerSocket(port);
				socket = serverSocket.accept();
				outputStream = new DataOutputStream(socket.getOutputStream());
				outputStream.writeInt(screenSize.width);
				outputStream.writeInt(screenSize.height);
				socket.close();
			}
			catch(Exception error)
			{
				System.out.println();
			}
		}
		
		Runnable imageRunnable = new Runnable()
		{
			public void run ()
	        {
				try 
	            {
					Robot robot = new Robot();
					socket = serverSocket.accept();
					
					while(isRunning)
					{
						BufferedImage image = robot.createScreenCapture(captureArea);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						
						ImageIO.write(image, "jpg", baos );
						baos.flush();
						byte[] imageInByte = baos.toByteArray();
						baos.close();
						
						outputStream = new DataOutputStream(socket.getOutputStream());
						outputStream.writeInt(imageInByte.length);
						outputStream.write(imageInByte);
						outputStream.flush();
					}
					socket.close();
	            }
				catch(Exception error)
				{
					
				}
	        }
		};
		
		Runnable udpRunnable = new Runnable()
		{
			public void run ()
	        {
				try
	            {
					robotMove = new Robot();
					udpServerSocket= new DatagramSocket(port);
					while(isRunning)
					{
					
						String message =  receiveDataFromClient();
						Scanner scan = new Scanner(message);
						System.out.println(message);
						String type = scan.next();
						
						if(type.contains("Cap"))
						{
							int x1 = scan.nextInt();
							int y1 = scan.nextInt();
							int x2 = scan.nextInt();
							int y2 = scan.nextInt();
							
							captureArea = new Rectangle(x1,y1,x2-x1,y2-y1);
						}
						else if(type.contains("Mou"))
						{
							int x1 = scan.nextInt();
							int y1 = scan.nextInt();
							robotMove.mouseMove(x1, y1);
							Timer time = new Timer(true);
							time.schedule(new Click(), 20); 
						}
						else if(type.contains("Mov"))
						{
							int x1 = scan.nextInt();
							int y1 = scan.nextInt();
							robotMove.mouseMove(x1, y1);
						}
						else if(type.contains("Cli"))
						{
							int x = scan.nextInt();
							if(x == 0)
							{
								robotMove.mousePress(InputEvent.BUTTON1_MASK);
							}
							else if(x == 1)
							{
								robotMove.mouseRelease(InputEvent.BUTTON1_MASK);
							}
							else if(x == 2)
							{
								robotMove.mousePress(InputEvent.BUTTON2_MASK);
							}
							else if(x == 3)
							{
								robotMove.mouseRelease(InputEvent.BUTTON2_MASK);
							}
							else if(x == 4)
							{
								robotMove.mousePress(InputEvent.BUTTON3_MASK);
							}
							else if(x == 5)
							{
								robotMove.mouseRelease(InputEvent.BUTTON3_MASK);
							}
							
						}
						else if(type.contains("Key"))
						{
							int key = scan.nextInt();
							int action = scan.nextInt();
							if(action == 0)
							{
								robotMove.keyPress(key);
							}
							else if (action == 1)
							{
								robotMove.keyRelease(key);
							}
							
						}
					}
	            }
				catch(Exception error)
				{
					
				}
	        }
		};
		
		public class Click extends TimerTask
		{
			public void run() 
			{	
				robotMove.mousePress(InputEvent.BUTTON1_MASK);
				robotMove.mouseRelease(InputEvent.BUTTON1_MASK);
			}
		}
		
		public String receiveDataFromClient() throws IOException {
			byte[] receiveData = new byte[1024];
			DatagramPacket recievePacket = new DatagramPacket(receiveData,
					receiveData.length);
			udpServerSocket.receive(recievePacket);
			return new String(recievePacket.getData());
		}
	}
}

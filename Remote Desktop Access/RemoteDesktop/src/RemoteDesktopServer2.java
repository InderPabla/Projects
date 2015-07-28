import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.imageio.ImageIO;



public class RemoteDesktopServer2 
{
	public static class Server
	{
		int listenPort = 6000;
		ServerSocket serverTCPSocket;	
		ServerSocket serverTCPSocket2;
		ServerSocket serverTCPSocket3;
		DatagramSocket serverUDPSocket;	
		
		Socket serverListenSocket = null;
		Socket serverListenSocket2 = null;
		Socket serverListenSocket3 = null;
		DatagramPacket serverUDPListenSocket = null;
		
		DataInputStream data = null;
		Thread imageTCPThread;
		Thread imageTCPThread2;
		Thread imageTCPThread3;
		Thread touchUDPThread;
		int deviceWidth,deviceHeight;
		Rectangle captureArea;
		boolean init = false;
		Scanner scanner;
		float diffX=0,diffY=0;
		public Server()
		{
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			System.out.println(width+" "+height);
			try
			{
				serverTCPSocket = new ServerSocket(listenPort);
				serverTCPSocket2 = new ServerSocket(listenPort+1);
				serverTCPSocket3 = new ServerSocket(listenPort+2);
				serverUDPSocket = new DatagramSocket(listenPort);

			}
			catch(Exception error)
			{
				System.out.println("Port not avaliable.");
			}
			
			imageTCPThread = new Thread(imageTCPRunnable);
			imageTCPThread.start();
			/*imageTCPThread2 = new Thread(imageTCPRunnable2);
			imageTCPThread2.start();*/
			/*imageTCPThread3 = new Thread(imageTCPRunnable3);
			imageTCPThread3.start();*/
			touchUDPThread = new Thread(touchUDPRunnable);
			touchUDPThread.start();
		}
		
		Runnable touchUDPRunnable = new Runnable() 
		{
			public void run() 
			{
				while(true)
				{
					try
					{
						byte[] receiveData = new byte[1024];
						DatagramPacket recievePacket = new DatagramPacket(receiveData,
								receiveData.length);
						
						serverUDPSocket.receive(recievePacket);
						String str = new String(recievePacket.getData());
						scanner = new Scanner(str);
						diffX = scanner.nextFloat();
						diffY = scanner.nextFloat();
						int x = captureArea.x;
						int y = captureArea.y;
						x+=diffX/10;
						y+=diffY/10;
						if(x>1920-deviceWidth)
							x = 1920-deviceWidth;
						if(x<0)
							x = 0;
						if(y>1080-deviceHeight)
							y = 1080-deviceHeight;
						if(y<0)
							y = 0;
						if(init)
						{
							captureArea.x = x;
							captureArea.y = y;
						}
					}catch(Exception e){System.out.println("errrrr");}
				}				
			}			
		};
		
		
		Runnable imageTCPRunnable = new Runnable() 
		{
			public void run() 
			{
				Robot robot = null;
				
				try
				{
					robot = new Robot();
					serverListenSocket = serverTCPSocket.accept();
					data = new DataInputStream(serverListenSocket.getInputStream());
					deviceWidth = getIntInformation();
					deviceHeight = getIntInformation();
					//deviceWidth /=2;
					//deviceHeight /=2;
					captureArea = new Rectangle(0,0,deviceWidth,deviceHeight);
					System.out.println(deviceWidth+" "+deviceHeight);
					serverListenSocket.close();
					
					serverListenSocket = serverTCPSocket.accept();
					init = true;
					
					while(true)
					{
						BufferedImage img = robot.createScreenCapture(captureArea);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(img, "jpg", baos );
						baos.flush();
						byte[] imageInByte = baos.toByteArray();
						baos.close();
						
						DataOutputStream data = new DataOutputStream(serverListenSocket.getOutputStream());
	
						data.writeInt(imageInByte.length);
						data.write(imageInByte);
					
						data.flush();
					}
					
				}
				catch(Exception e){e.printStackTrace();}
			}
			
		};
		
		Runnable imageTCPRunnable2 = new Runnable() 
		{
			public void run() 
			{
				Robot robot = null;
				
				try
				{
					robot = new Robot();

					
					serverListenSocket2 = serverTCPSocket2.accept();
					init = true;

					while(true)
					{
						BufferedImage img = robot.createScreenCapture(captureArea);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(img, "jpg", baos );
						baos.flush();
						byte[] imageInByte = baos.toByteArray();
						baos.close();
						
						DataOutputStream data = new DataOutputStream(serverListenSocket2.getOutputStream());
	
						data.writeInt(imageInByte.length);
						data.write(imageInByte);
					
						data.flush();
					}
					
				}
				catch(Exception e){e.printStackTrace();}
			}
			
		};
		
		Runnable imageTCPRunnable3 = new Runnable() 
		{
			public void run() 
			{
				Robot robot = null;
				
				try
				{
					robot = new Robot();

					
					serverListenSocket3 = serverTCPSocket3.accept();
					init = true;

					while(true)
					{
						BufferedImage img = robot.createScreenCapture(captureArea);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(img, "jpg", baos );
						baos.flush();
						byte[] imageInByte = baos.toByteArray();
						baos.close();
						
						DataOutputStream data = new DataOutputStream(serverListenSocket3.getOutputStream());
	
						data.writeInt(imageInByte.length);
						data.write(imageInByte);
					
						data.flush();
					}
					
				}
				catch(Exception e){e.printStackTrace();}
			}
			
		};
		
		
		
		public int getIntInformation()
		{	
			try
			{
				int intInfo = data.readInt();
				return intInfo;
			}catch(Exception error)
			{
				return -1;
			}
           
		}
		
	}
	
	public static void main (String[] arguments)
	{
		new Server();
	}
}

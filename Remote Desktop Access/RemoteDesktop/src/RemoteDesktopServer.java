import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
		
		String IP = "192.168.0.14";
		
		Dimension screenSize = null;
		
		ServerSocket serverSocket;	
		Socket socket;	
		
		Thread imageThread = null;	
		
		DataInputStream inputStream;	
		DataOutputStream outputStream;	
		
		boolean isRunning = true;
		
		Rectangle captureArea = null;
		
		public Server()
		{
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			captureArea = new Rectangle(0,0,screenSize.width,screenSize.height);
			init();
			imageThread = new Thread(imageRunnable);
	        imageThread.start();	
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
	}
}

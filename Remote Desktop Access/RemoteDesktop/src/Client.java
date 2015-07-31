import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
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
	
}

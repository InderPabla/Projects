import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class RemoteDesktopClient extends Applet implements Runnable
{
	Dimension appletSize;
	Thread thread;
	Thread thread2;
	Thread thread3;
	Thread thread4;
    Socket socket = null;
    Socket socket2 = null;
    Socket socket3 = null;
    DataInputStream data;
    DataInputStream data2;
    DataInputStream data3;
    String serverIpAddress = "192.168.0.14";
    int portToConnect = 6000;
    int width,height;
	boolean done = false;
	BufferedImage image;
	BufferedImage image2;
	BufferedImage image3;
	boolean wait = true;
	boolean painting = false;
	DataOutputStream data1;
	int counter = 0;
	public void init()
	{
		appletSize = new Dimension(900,700);
		setSize(appletSize);
		width = 1920;
		height = 1080;
		setBackground(Color.black);
		thread = new Thread(inforunnable);
        thread.start();
		
	} 
	public void writeIntInformation(int info)
    {
        try
        {
            data1.writeInt(info);
        }
        catch(Exception error) {}
    }
	
	 Runnable inforunnable = new Runnable()
	    {
	        public void run ()
	        {
	            try
	            {
	                socket = new Socket(serverIpAddress, portToConnect);
	                data1 = new DataOutputStream(socket.getOutputStream());
	                writeIntInformation(width);
	                writeIntInformation(height);
	                socket.close();
	                thread2 = new Thread(tcprunnable);
	                thread2.start();
	                /*thread3 = new Thread(tcprunnable2);
	                thread3.start();
	                thread4 = new Thread(tcprunnable3);
	                thread4.start();*/
	               
	                //thread.stop();
	            }
	            catch(Exception error){}
	        }
	    };
	
	
	 Runnable tcprunnable = new Runnable()
	    {
	        public void run ()
	        {
	            try
	            {
	                socket = new Socket(serverIpAddress, portToConnect);
	                data = new DataInputStream(socket.getInputStream());
	                while(true)
	                {
	                    int size = data.readInt();
	                    byte[] imgBytes = new byte[size];
	                    data.readFully(imgBytes);
	                    InputStream in = new ByteArrayInputStream(imgBytes);
	                   

	                    //if(wait == false )
	                    	image = ImageIO.read(in);
	                    //else 
	                    	//image2 = ImageIO.read(in);
                      
	                    //wait = !wait;
	                    
	                    //if(counter<2)
	                    	//counter++;

	                }
	                //socket.close();
	            }
	            catch(Exception error){}
	        }
	    };
	    
	    Runnable tcprunnable2 = new Runnable()
	    {
	        public void run ()
	        {
	            try
	            {
	                socket2 = new Socket(serverIpAddress, portToConnect+1);
	                data2 = new DataInputStream(socket2.getInputStream());
	                while(true)
	                {
	                    int size = data2.readInt();
	                    byte[] imgBytes = new byte[size];
	                    data2.readFully(imgBytes);
	                    InputStream in = new ByteArrayInputStream(imgBytes);
	                   

	                    //if(wait == false )
	                    	image = ImageIO.read(in);
	                    //else 
	                    	//image2 = ImageIO.read(in);
                      
	                    //wait = !wait;
	                    
	                    //if(counter<2)
	                    	//counter++;

	                }
	                //socket.close();
	            }
	            catch(Exception error){}
	        }
	    };
	    
	    Runnable tcprunnable3 = new Runnable()
	    {
	        public void run ()
	        {
	            try
	            {
	                socket3 = new Socket(serverIpAddress, portToConnect+2);
	                data3 = new DataInputStream(socket2.getInputStream());
	                while(true)
	                {
	                    int size = data3.readInt();
	                    byte[] imgBytes = new byte[size];
	                    data3.readFully(imgBytes);
	                    InputStream in = new ByteArrayInputStream(imgBytes);
	                   

	                    //if(wait == false )
	                    	image = ImageIO.read(in);
	                    //else 
	                    	//image2 = ImageIO.read(in);
                      
	                    //wait = !wait;
	                    
	                    //if(counter<2)
	                    	//counter++;

	                }
	                //socket.close();
	            }
	            catch(Exception error){}
	        }
	    };
	
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void paint(Graphics g)
	{
		//g.fillRect(0, 0, 700,700);
		//if(wait==false)
			g.drawImage(image, 0,0, 768,432, this);
		//else
			//g.drawImage(image2, 0,0, 400,240, this);
		
	}

	public void run() 
	{
		while (true) 
		{
		     repaint();
		}
	}
	public void start() 
	{
		thread = new Thread(this);
		thread.start();
	}
	public void stop() 
	{
		thread = null;
	}
}

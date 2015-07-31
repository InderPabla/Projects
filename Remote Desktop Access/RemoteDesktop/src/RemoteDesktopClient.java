import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JApplet;


public class RemoteDesktopClient extends Applet implements Runnable
{
	private Thread appletThread;
	private BufferedImage bufferedImage = null;
	private Dimension appletSize = null;
	private Dimension clientScreenSize = null;
	private Client client = null;
	
	public void init()
	{
		clientScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		appletSize = new Dimension((int)(clientScreenSize.width*0.25f),(int)(clientScreenSize.height*0.25f));
		client = new Client(6000,"192.168.0.14");
		
		this.setSize(appletSize);		
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(client.bufferedImage,0,0,appletSize.width,(int)((float)appletSize.width/client.ratio),this);	
		
		appletSize.width = getSize().width;
		appletSize.height = getSize().height ;
		//System.out.println(this.getSize().width+" "+this.getSize().height);
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void run() 
	{
		while(true)
		{
			repaint();
		}
	}
	
	public void start() 
	{
		appletThread = new Thread(this);
		appletThread.start();
	}
	
	public void stop() 
	{
		appletThread = null;
	}

}

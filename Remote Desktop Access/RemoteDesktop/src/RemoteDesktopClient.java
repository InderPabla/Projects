import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JApplet;


public class RemoteDesktopClient extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
	private Thread appletThread;
	private BufferedImage bufferedImage = null;
	private Dimension appletSize = null;
	private Dimension clientScreenSize = null;
	private Client client = null;
	private boolean mouseDown = false;
	private Point initialMousePosition = new Point();
	private Point dynamicMousePosition = new Point();
	
	private boolean mouseMode = false;
	private boolean useMouse = false;
	public void init()
	{
		clientScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		appletSize = new Dimension((int)(clientScreenSize.width*0.25f),(int)(clientScreenSize.height*0.25f));
		client = new Client(6000,"192.168.0.14");
		
		this.setSize(appletSize);	
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(client.bufferedImage,0,0,appletSize.width,(int)((float)appletSize.width/client.ratio),this);	
		
		appletSize.width = getSize().width;
		appletSize.height = getSize().height ;
		
		if(mouseDown==true)
		{
			g.setColor(Color.red);
			//g.drawRect(initialMousePosition.x, initialMousePosition.y, dynamicMousePosition.x-initialMousePosition.x, dynamicMousePosition.y-initialMousePosition.y);
		}
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

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
			
	}

	public void mousePressed(MouseEvent e) {
		
			mouseDown = true;
			initialMousePosition.x = e.getX();
			initialMousePosition.y = e.getY();
			dynamicMousePosition.x = e.getX();
			dynamicMousePosition.y = e.getY();
			
			
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				client.sendUDPMouseActionMessage(0);
			}
			else if(e.getButton()==MouseEvent.BUTTON2)
			{
				client.sendUDPMouseActionMessage(2);
			}
			else if(e.getButton()==MouseEvent.BUTTON3)
			{
				client.sendUDPMouseActionMessage(4);
			}
	}

	public void mouseReleased(MouseEvent e) {
		/*if(mouseMode == false)
		{
			mouseDown = false;
			
			if(useMouse)
			{
				int x1 = (int)(((float)client.screenSize.width/(float)appletSize.width)*(float)initialMousePosition.x);
				int y1 = (int)(((float)client.screenSize.height/   ((float)appletSize.width/client.ratio)   )*(float)initialMousePosition.y);
				
				int x2 = (int)(((float)client.screenSize.width/(float)appletSize.width)*(float)dynamicMousePosition.x);
				int y2 = (int)(((float)client.screenSize.height/   ((float)appletSize.width/client.ratio)   )*(float)dynamicMousePosition.y);
				
				client.sendUDPCaptureMessage(x1, y1, x2, y2);
			}
		}
		else
		{
			int x1 = (int)(((float)client.screenSize.width/(float)appletSize.width)*(float)initialMousePosition.x);
			int y1 = (int)(((float)client.screenSize.height/   ((float)appletSize.width/client.ratio)   )*(float)initialMousePosition.y);
			client.sendUDPMouseMessage(x1,y1);
		}*/
		
		if(e.getButton()==MouseEvent.BUTTON1)
		{
			client.sendUDPMouseActionMessage(1);
		}
		else if(e.getButton()==MouseEvent.BUTTON2)
		{
			client.sendUDPMouseActionMessage(3);
		}
		else if(e.getButton()==MouseEvent.BUTTON3)
		{
			client.sendUDPMouseActionMessage(5);
		}
		
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		dynamicMousePosition.x = e.getX();
		dynamicMousePosition.y = e.getY();
		
		int x1 = (int)(((float)client.screenSize.width/(float)appletSize.width)*(float)e.getX());
		int y1 = (int)(((float)client.screenSize.height/   ((float)appletSize.width/client.ratio)   )*(float)e.getY());
		client.sendUDPMouseMoveMessage(x1,y1);
	}

	public void mouseMoved(MouseEvent e) 
	{
		int x1 = (int)(((float)client.screenSize.width/(float)appletSize.width)*(float)e.getX());
		int y1 = (int)(((float)client.screenSize.height/   ((float)appletSize.width/client.ratio)   )*(float)e.getY());
		
		client.sendUDPMouseMoveMessage(x1,y1);
	}

	public void keyPressed(KeyEvent e) {
		client.sendUDPKeyActionMessage(e.getKeyCode(),0);
		
	}

	public void keyReleased(KeyEvent e) 
	{
		client.sendUDPKeyActionMessage(e.getKeyCode(),1);
		
		/*if(e.getKeyCode() == KeyEvent.VK_R)
		{
			client.sendUDPCaptureReset();
		}*/
		
		/*if(e.getKeyCode() == KeyEvent.VK_1)
		{
			mouseMode =! mouseMode;
			System.out.println(mouseMode);
		}
		if(e.getKeyCode() == KeyEvent.VK_M)
		{
			useMouse =! useMouse;
		}*/
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

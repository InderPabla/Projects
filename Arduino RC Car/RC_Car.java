import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
* RC_Car class applet, connects and talks to RC_Car_Arduino.ino
*/
public class RC_Car extends Applet implements Runnable, MouseListener {
	int delay = 25; //25 ms delay in print
	Thread runThread;  //active thread
	Dimension screen = new Dimension(600,600); //screen dimensions
	float mx,my; //mouse x and y positions
	
	long lastTime; //last time of system
	long fps; //frames per second
	
	Image image; //image to be printed out
	Graphics g2; 
	
	//buttons and colors
	Rectangle up,down; 
	Color upC,downC;
	Rectangle left,right;
	Color leftC,rightC;
	Rectangle stopCar;
	Color stopCarC;
	Rectangle autoReverse;
	Color autoReverseC;
	
	//Arduino serial connectors 
	OutputStream out;
    SerialReader input;
    Scanner scanner= new Scanner(System.in);
	
	boolean state=false;
	boolean isReverse = false;
	int reverseCounter;
	int counter=0;
	
	/**
     * init method sets up initial settings 
     */
	public void init(){
		setSize(screen); //set screen size
		setBackground(Color.black); //screen background to black
		
		image = createImage(screen.width, screen.height); //image creation
		g2 = image.getGraphics(); //graphic set to show image

		addMouseListener(this); //adding mouse listener
		
		up = new Rectangle(300,300-50-10,50,50); //up button of size 50x50 at 300,240
		down = new Rectangle(300,300+50+10,50,50); //up button of size 50x50 at 300,360
		upC = Color.gray;
		downC = Color.gray;
		
		left = new Rectangle(300-50-10,300,50,50); //left button of size 50x50 at 240,300
		right = new Rectangle(300+50+10,300,50,50); //right button of size 50x50 at 360,300
		leftC = Color.gray;
		rightC = Color.gray;
		
		stopCar = new Rectangle(300,300,50,50); //stopCar button of size 50x50 at 300,300
		stopCarC = Color.gray;
		
		autoReverse = new Rectangle(50,50,50,50); //autoReverse button of size 50x50 at 50,50
		autoReverseC = Color.gray;
		
		try {connect("COM7");} catch (Exception e) {e.printStackTrace();} //connect to Arduino using port COM7
		
		resetCar(0); //reset car
	}
	
	/**
     * update method prints out buttons and shows framerate (25 ms each)
	 * @param  g  gets drawn
     */
	public void update(Graphics g){
		
		//draw screen
		g2.setColor(Color.black);
		g2.fillRect(0,0, screen.width, screen.height);
		
		//draw buttons
		g2.setColor(upC);
		g2.fillRect(up.x,up.y,up.width,up.height);
		g2.setColor(downC);
		g2.fillRect(down.x,down.y,down.width,down.height);
    	
		g2.setColor(leftC);
		g2.fillRect(left.x,left.y,left.width,left.height);
		g2.setColor(rightC);
		g2.fillRect(right.x,right.y,right.width,right.height);
		
		g2.setColor(stopCarC);
		g2.fillRect(stopCar.x,stopCar.y,stopCar.width,stopCar.height);
		
		g2.setColor(autoReverseC);
		g2.fillRect(autoReverse.x,autoReverse.y,autoReverse.width,autoReverse.height);
		
		//draw frame rate
		g2.setColor(Color.yellow);
		g2.drawString("Framerate:" + (1000/(System.currentTimeMillis() - lastTime)), 15, 15);
		lastTime = System.currentTimeMillis();	
		
		//draw image
		g.drawImage(image,0,0,this);
		
		if(isReverse==true){
			reverse();
		} //reverse car
	}//update
	
	/**
     * paint (main update functions which is called)
	 * @param  g  gets drawn
     */
	public void paint(Graphics g) {
	    this.update(g);//call update method
	}//paint method	
	
	/**
     * run starts a thread
     */
	@SuppressWarnings("static-access")
	public void run() {
		while (Thread.currentThread() == runThread) {
		      try {
		        Thread.currentThread().sleep(delay);
		      } catch (InterruptedException  e) {}
		      repaint();
		 } //repaint scene per 25 ms delay
	}//run method
	public void start() {
		runThread = new Thread(this);
		runThread.start();
	}//start method
	public void stop() {
		runThread = null;
	}//stop method
	
	/**
     * mouseClicked check which button is pressed and handel left, right and reverse movement
	 * @param  ev  mouse event
     */
	@Override
	public void mouseClicked(MouseEvent ev) {
		mx=ev.getX(); //get mouse x
		my=ev.getY(); //get mouse y
		
		//if mouse click left button
		if(left.intersects(new Rectangle((int)mx, (int)my,1,1))){
			leftWheel(); //turn wheel left
			leftC = Color.red;
			rightC = Color.gray;
		} 
		
		//if mouse click right button
		if(right.intersects(new Rectangle((int)mx, (int)my,1,1))){
			rightWheel(); //turn wheel right
			rightC = Color.red;
			leftC = Color.gray;
		}
		
		//if mouse click stopCar button
		if(stopCar.intersects(new Rectangle((int)mx, (int)my,1,1))){
			resetCar(0); //rest car wheel, and stop car
			rightC = Color.gray;
			leftC = Color.gray;
		}
		
		if(isReverse==false)
			//if mouse click stopCar button
			if(autoReverse.intersects(new Rectangle((int)mx, (int)my,1,1))){
				isReverse = true;
				autoReverseC = Color.red;
				autoReverseC = Color.gray;
			}
	}
	
	/**
     * mousePressed check which button is pressed and handel forward and backwards movement
	 * @param  ev  mouse event
     */
	public void mousePressed(MouseEvent ev) {
		mx=ev.getX(); //get mouse x
		my=ev.getY(); //get mouse y
		
		if(state==false){
			if(up.intersects(new Rectangle((int)mx, (int)my,1,1))){
				state=true; //latch to stay
				forward(); //car goes forward  
				upC = Color.red;
			}
			if(down.intersects(new Rectangle((int)mx, (int)my,1,1))){
				state=true; //latch to stay
				backward(); //car goes backwards  
				downC = Color.red;
			}
		}  
	} 

	/**
     * mousePressed reset car
	 * @param  ev  mouse event
     */
	public void mouseReleased(MouseEvent ev) {
		mx=ev.getX();
		my=ev.getY();
		if(state==true){
			if(up.intersects(new Rectangle((int)mx, (int)my,1,1))){
				resetCar(1); //reset car
				upC = Color.gray;
				state=false; //remove latch
			}
			if(down.intersects(new Rectangle((int)mx, (int)my,1,1))){
				resetCar(1); //reset car
				downC = Color.gray;
				state=false; //remove latch
			}
		} 
	}
	

	/**
     * reverse turns the car around (180 turn)
     */
	public void reverse(){
		boolean isCountable = true;

		if(reverseCounter==0){
			rightWheel();
		}
		else if(reverseCounter==5){
			forward();
		}
		else if(reverseCounter==12){
			resetCar(1);	
		}
		else if(reverseCounter==12+15){
			leftWheel();	
		}
		else if(reverseCounter==12+15+5){
			backward();
		}
		else if(reverseCounter==12+15+5+(12-5)){
			resetCar(1);
		}
		else if(reverseCounter==12+15+5+(12-5)+15){		
			reverseCounter = 0;
			if(counter==7){
				resetCar(0);
				isReverse = false;
				counter=0;
				
			}
			else{
				isCountable=false;
			}
			counter++;
		}
		if(isReverse==true && isCountable==true)
			reverseCounter++;
	} 
	
	/**
     * writeOut write out to COM7 port of Arduino
	 * @param  s  string to write out
     */
	public void writeOut(String s){
		try {
			out.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * forward write out to COM7 port "2"
     */
	public void forward(){
		writeOut("2"); 
	}
	
	/**
     * backward write out to COM7 port "1"
     */
	public void backward(){
		writeOut("1"); 
	}
	
	/**
     * leftWheel write out to COM7 port "4"
     */
	public void leftWheel(){
		writeOut("4"); 
	}
	
	/**
     * rightWheel write out to COM7 port "5"
     */
	public void rightWheel(){
		writeOut("5"); 
	}
	
	/**
     * resetCar write out to COM7 port "0" and on full car reset "3" aswell
	 * @param  which  on 0 full stop car and fix wheels, on 1 only stop car
     */
	public void resetCar(int which){
		writeOut("0"); 
		if(which!=1) 
			writeOut("3"); 
	}
	
	/**
     * connect connect to port
	 * @param  portName the port name to connect to
     */
    void connect ( String portName ) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName); //get port idetifier given port name
        if ( portIdentifier.isCurrentlyOwned() ){
            System.out.println("Error: Port is currently in use");
        } // if port not available
        else{
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            if ( commPort instanceof SerialPort ){
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                InputStream in = serialPort.getInputStream();
                out = serialPort.getOutputStream();
                input = new SerialReader(in);
                serialPort.addEventListener(input);
                serialPort.notifyOnDataAvailable(true);
                Thread.sleep(3000);  

            }
            else{
                System.out.println("Error: This is not a serial port");
            }
        } // if port is available, then connect to it   
    }
	
    /**
     * Handles the input coming from the serial port. A new line character
     * is treated as the end of a block in this example. 
     */
    public static class SerialReader implements SerialPortEventListener {
        private InputStream in;
        private byte[] buffer = new byte[1024];
        String buffer_string;
        public SerialReader ( InputStream in ){
            this.in = in;
        }
        public void serialEvent(SerialPortEvent arg0) {
            int data;
            try{
                int len = 0;
                while ( ( data = in.read()) > -1 ){
                    if ( data == '\n' ) {
                        break;
                    }
                    buffer[len++] = (byte) data;
                }
                buffer_string = new String(buffer,0,len);
            }
            catch ( IOException e ){
                e.printStackTrace();
                System.exit(-1);
            }             
        }
	}
	
	//unused methods from MouseListener
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
}//RC_Car class

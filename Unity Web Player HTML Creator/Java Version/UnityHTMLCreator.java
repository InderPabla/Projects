import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Unity Web Player's HTML file creator. This programs allows user to create a custom HTML file for unity.3d files. 
 * With the created HTML file .unity3d or .unity2d games can run on browsers.
 */
public class UnityHTMLCreator implements ActionListener
{
	JFrame window = new JFrame("Unity HTML Design"); //window
	int windowWidth,windowHeight; //window dimensions
	
	//buttons
	JButton createButton = new JButton("Create"); 
	JButton backgroundColorButton = new JButton("Color"); 
	JButton borderColorButton = new JButton("Color");
	
	//colors
	Color backgroundColor = Color.red;
	Color borderColor = Color.red;
	Color windowColor;
	
	//panels to show-case color
	JPanel backgroundPanel = new JPanel();
	JPanel borderPanel = new JPanel();
	
	//fields for entering values
	JTextField nameField = new JTextField();
	JTextField widthField = new JTextField();
	JTextField heightField = new JTextField();
	JTextField titleField = new JTextField();
	JTextField headerField = new JTextField();	
	JTextField borderWidthField = new JTextField();
	JTextField borderHeightField = new JTextField();
	JTextField borderRadiusField= new JTextField();
	
	//labels to show field types
	JLabel backgroundLable = new JLabel("Background Color:");
	JLabel borderLable = new JLabel("Player Border Color:");
	JLabel nameLable = new JLabel("Unity3D File Name:");
	JLabel widthLable = new JLabel("Player Width:");
	JLabel heightLable = new JLabel("Player Height:");
	JLabel titleLable = new JLabel("Title:");
	JLabel headerLable = new JLabel("Player Header:");
	JLabel borderWidthLable = new JLabel("Player Border Width:");
	JLabel borderHeightLable = new JLabel("Player Border Height:");
	JLabel borderRadiusLable = new JLabel("Player Border Radius:");
	
	
	Hashtable<String,String> markers = new Hashtable<String,String>(); //hash table for corresponding markers in template.html 
	
	
	/**
	 * Create UnityHTMLCreator object
	 * @param arguments : main method does not use arguments 
	 */
	public static void main (String[] arguments)
	{
		 UnityHTMLCreator html = new UnityHTMLCreator(330,600);
	}
	
	/**
	 * Set window, fields, labels and buttons settings.
	 * Also insert HTML marker keys and values into hash table.
	 * @param wWidth : window width
	 * @param wHeight : window height
	 */
	public UnityHTMLCreator(int wWidth, int wHeight)
	{
		initWindow(wWidth,wHeight);
		setHTMLMarkers();
		setContents();
	}
	
	
	/**
	 * Window settings
	 * @param width : window width
	 * @param height : window height
	 */
	public void initWindow(int width, int height)
	{
		windowWidth = width;
		windowHeight = height;
		window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.white);
        window.setSize(windowWidth,windowHeight);
		window.setVisible(true);
		windowColor = new Color(204,255,102); //lime
		window.getContentPane().setBackground(windowColor);
		window.setLayout(null);
		
	}
	
	/**
	 * Insert HTML marker keys and values into hash table
	 */
	public void setHTMLMarkers()
	{
		markers.put("title","TITLEMARKER");
	    markers.put("header","HEADERMARKER");
	    markers.put("width","WIDTHMARKER");
	    markers.put("height","HEIGHTMARKER");
	    markers.put("name","NAMEMARKER");
		markers.put("color","BACKGROUNDMARKER");
		markers.put("border","BORDERCMARKER");
		markers.put("bwidth","BORDERWMARKER");
		markers.put("bheight","BORDERHMARKER");
		markers.put("bradius","BORDERRMARKER");
	}
	
	/**
	 * Add contents to window, set bounds and initialize fields. 
	 */
	public void setContents()
	{
			
		addContents();
		setContentBounds();
		initFields();
	}

	/**
	 * Add contents to window, add action listeners to buttons. 
	 */
	public void addContents()
	{
		window.add(backgroundLable); 
		window.add(borderLable);
		window.add(nameLable);
		window.add(widthLable);
		window.add(heightLable); 
		window.add(titleLable); 
		window.add(headerLable); 
		window.add(borderWidthLable); 
		window.add(borderHeightLable);
		window.add(borderRadiusLable);
		
		window.add(nameField);
		window.add(widthField);
		window.add(heightField); 
		window.add(titleField); 
		window.add(headerField); 
		window.add(borderWidthField); 
		window.add(borderHeightField);
		window.add(borderRadiusField);

		window.add(backgroundPanel);
		window.add(borderPanel);

		window.add(backgroundColorButton);
		backgroundColorButton.addActionListener(this);
		window.add(borderColorButton);
		borderColorButton.addActionListener(this);
		window.add(createButton);
		createButton.addActionListener(this);
	}
	

	/**
	 * Set content bounds of fields, buttons, lables and planels on screen. 
	 */
	public void setContentBounds(){
		int lableOffset = 10;
		int labelWidth = 500;
		int labelHeight = 25;
		int currentX = lableOffset;
		int currentY = 15;
		int multi = 5;
		
		nameLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		nameField.setBounds(currentX+115,currentY,170,labelHeight);
		currentY+=lableOffset*multi;
		
		backgroundLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		backgroundColorButton.setBounds (currentX+115,currentY-5,75,25);
		backgroundPanel.setBounds(currentX+200,currentY-5,75,25);
		currentY+=lableOffset*multi;

		widthLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		widthField.setBounds(currentX+115,currentY,170,labelHeight);
		currentY+=lableOffset*multi;
		
		heightLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		heightField.setBounds(currentX+115,currentY,170,labelHeight);
		currentY+=lableOffset*multi;
		
		titleLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		titleField.setBounds(currentX+115,currentY,170,labelHeight);
		currentY+=lableOffset*multi;
		
		headerLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		headerField.setBounds(currentX+115,currentY,170,labelHeight);
		currentY+=lableOffset*multi;
	
		borderLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		borderColorButton.setBounds (currentX+125,currentY-5,75,25);
		borderPanel.setBounds(currentX+210,currentY-5,75,25);
		currentY+=lableOffset*multi;
		
		borderWidthLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		borderWidthField.setBounds(currentX+125,currentY,170,labelHeight);
		currentY+=lableOffset*multi;
		
		borderHeightLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		borderHeightField.setBounds(currentX+125,currentY,170,labelHeight);
		currentY+=lableOffset*multi;
	
		borderRadiusLable.setBounds(currentX,currentY,labelWidth,labelHeight);
		borderRadiusField.setBounds(currentX+125,currentY,170,labelHeight);
		currentY+=lableOffset*multi;
		
		createButton.setBounds((windowWidth/2)-(75/2),currentY,75,25);
	}
	
	/**
	 * Initialize fields to some value so they are not null. 
	 */
	public void initFields()
	{
		backgroundPanel.setBackground(backgroundColor);
		borderPanel.setBackground(borderColor);
		backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		borderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		nameField.setText("File_Name.unity3d");
		widthField.setText("960");
		heightField.setText("600");
		titleField.setText("Game");
		headerField.setText("Game");
		borderWidthField.setText("5");
		borderHeightField.setText("5");
		borderRadiusField.setText("5");
	}
	
	/**
	 * Listen of buttons actions and act accordingly.
	 * @param event : event from active actions.
	 */
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource().equals(backgroundColorButton))
		{
			window.setVisible(false);
			backgroundColor = JColorChooser.showDialog(null, "Background Color", Color.red);
			window.setVisible(true);  
			if(backgroundColor == null)
				backgroundColor= Color.red;
			backgroundPanel.setBackground(backgroundColor);
		}
		
		if (event.getSource().equals(borderColorButton))
		{
			window.setVisible(false);
			borderColor = JColorChooser.showDialog(null, "Border Color", Color.red);
			window.setVisible(true); 
			if(borderColor == null)
				borderColor = Color.red;
			borderPanel.setBackground(borderColor);
		}
		
		if (event.getSource().equals(createButton))
		{
			if(!nameField.getText().equals("")  && 
			   !widthField.getText().equals("") &&
			   !heightField.getText().equals("") && 
			   !headerField.getText().equals(""))
			{
				if (nameField.getText().endsWith(".unity3d") || nameField.getText().endsWith(".unity2d"))
				{
					if(borderWidthField.getText().equals(""))
						borderWidthField.setText("0");
					if(borderHeightField.getText().equals(""))
						borderHeightField.setText("0");
					if(borderRadiusField.getText().equals(""))
						borderRadiusField.setText("0");
					
					String name = nameField.getText();
					String unityName = name;
					
					name = name.replace(".unity3d", ".html");
					name = name.replace(".unity3d", ".html");
					
					if(name.equals("index.html"))
						name = "index_unity.html";
					if(name.equals("template.html"))
						name = "template_unity.html";
					
					String hexBackgroundColor = String.format("#%06X", (0xFFFFFF & backgroundColor.getRGB()));
					String hexBorderColor = String.format("#%06X", (0xFFFFFF & borderColor.getRGB()));
					
					String fileContent = "";
					try 
					{
						fileContent = readFile ("template.html");
						fileContent = fileContent.replace(markers.get("title"), titleField.getText());
						fileContent = fileContent.replace(markers.get("width"), widthField.getText());
						fileContent = fileContent.replace(markers.get("height"), heightField.getText());
						fileContent = fileContent.replace(markers.get("name"), unityName);
						fileContent = fileContent.replace(markers.get("color"), hexBackgroundColor);
						fileContent = fileContent.replace(markers.get("border"), hexBorderColor);
						fileContent = fileContent.replace(markers.get("bwidth"), borderWidthField.getText());
						fileContent = fileContent.replace(markers.get("bheight"), borderHeightField.getText());
						fileContent = fileContent.replace(markers.get("bradius"), borderRadiusField.getText());
						fileContent = fileContent.replace(markers.get("header"), headerField.getText());
						
						
						File file = new File(name);
						file.createNewFile();
						PrintWriter writer = new PrintWriter(name);
						writer.println(fileContent);
						writer.close();

						System.exit(0);
					} 
					catch (IOException e) 
					{
						System.out.println("template.html does not exist.");
					}
				}
			}
		}	
	}
	
	/**
	 * Read file given file name and output file contents.
	 * @param fileName : name of file to read.
	 * @return : return file contents.
	 * @throws : if file does not exist
	 */
	String readFile(String fileName) throws IOException 
	{
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
}

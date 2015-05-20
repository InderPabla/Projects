import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Class allows to create a customer query insert into the Customer table.
 * @author INDERPREET PABLA
 */
public class CustomerNew extends Operation{

	private JFrame frame;
	private Dimension dimension = new Dimension(400,400);
	
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField appDrugField;
	private JDateChooser dateChooser = new JDateChooser();
	
	/**
	 * Constructor for quick insert of information without need for visual display.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @param name Name of the customer.
	 * @param phone Phone number of the customer.
	 * @param email Email of the customer.
	 * @param dob Date of birth of the customer
	 * @param address Address of the customer.
	 * @param drugs Drug list of the customer(Advil,Benadryl).
	 */
	public CustomerNew(String server, String username, String password, String name, String phone, String email, Date dob, String address, String drugs) {
		createConnection(server,username,password,pharmacyName);
		insert(name,phone,email,dob,address,drugs,false);
	}
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public CustomerNew(String server, String username, String password) {
		createConnection(server,username,password,pharmacyName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//100,100 location on desktop of spawn
		frame.setBounds(100, 100, dimension.width, dimension.height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		JLabel label = new JLabel("New Customer");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(136, 11, 126, 20);
		frame.getContentPane().add(label);
		
		nameField = new JTextField();
		nameField.setBounds(10, 55, 150, 20);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameLabel.setBounds(10, 38, 32, 14);
		frame.getContentPane().add(nameLabel);
		
		JLabel nameAsteriskLabel = new JLabel("*");
		nameAsteriskLabel.setForeground(Color.RED);
		nameAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameAsteriskLabel.setBounds(44, 38, 18, 14);
		frame.getContentPane().add(nameAsteriskLabel);
		
		JLabel legendAsteriskLabel = new JLabel("*");
		legendAsteriskLabel.setForeground(Color.RED);
		legendAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendAsteriskLabel.setBounds(10, 337, 7, 14);
		frame.getContentPane().add(legendAsteriskLabel);
		
		JLabel legendLabel = new JLabel("Must be filled");
		legendLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendLabel.setBounds(20, 337, 140, 14);
		frame.getContentPane().add(legendLabel);
		
		JLabel phoneLabel = new JLabel("Phone Number");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneLabel.setBounds(10, 86, 82, 14);
		frame.getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(10, 103, 150, 20);
		frame.getContentPane().add(phoneField);
		
		JLabel phoneAsteriskLabel = new JLabel("*");
		phoneAsteriskLabel.setForeground(Color.RED);
		phoneAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneAsteriskLabel.setBounds(95, 86, 18, 14);
		frame.getContentPane().add(phoneAsteriskLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		emailLabel.setBounds(10, 134, 32, 14);
		frame.getContentPane().add(emailLabel);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(10, 151, 150, 20);
		frame.getContentPane().add(emailField);
		
		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dobLabel.setBounds(10, 182, 71, 14);
		frame.getContentPane().add(dobLabel);
		
		dateChooser.setBounds(10, 197, 150, 20);
		frame.getContentPane().add(dateChooser);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 228, 46, 14);
		frame.getContentPane().add(addressLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 245, 150, 20);
		frame.getContentPane().add(addressField);
		
		JLabel addressAsteriskLabel = new JLabel("*");
		addressAsteriskLabel.setForeground(Color.RED);
		addressAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressAsteriskLabel.setBounds(59, 228, 18, 14);
		frame.getContentPane().add(addressAsteriskLabel);
		
		JLabel dobAsteriskLabel = new JLabel("*");
		dobAsteriskLabel.setForeground(Color.RED);
		dobAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dobAsteriskLabel.setBounds(84, 182, 18, 14);
		frame.getContentPane().add(dobAsteriskLabel);
		
		JLabel appDrugLabel = new JLabel("Approved Drug(s)");
		appDrugLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		appDrugLabel.setBounds(10, 276, 101, 14);
		frame.getContentPane().add(appDrugLabel);
		
		appDrugField = new JTextField();
		appDrugField.setColumns(10);
		appDrugField.setBounds(10, 293, 150, 20);
		frame.getContentPane().add(appDrugField);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(nameField.getText().equals("") || phoneField.getText().equals("") || dateChooser.getDate()==null  || addressField.getText().equals(""))){
					insert(nameField.getText(),phoneField.getText(),emailField.getText(),dateChooser.getDate(),addressField.getText(),appDrugField.getText(),true);
					frame.dispose();					
				}
			}
		});
		createButton.setForeground(Color.RED);
		createButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		createButton.setBounds(170, 328, 89, 23);
		frame.getContentPane().add(createButton);
		frame.setVisible(true);
	}
	
	/**
	 * Inserting customer data to Customer table in Pharmacy database.
	 * @param name Name of the customer.
	 * @param phone Phone number of the customer.
	 * @param email Email of the customer.
	 * @param dob Date of birth of the customer
	 * @param address Address of the customer.
	 * @param drugs Drug list of the customer(Advil,Benadryl).
	 * @param makeTable If true a window will open to show a table with the customer number index and relevant information. 
	 */
	public void insert(String name, String phone, String email, Date dob, String address, String drugs,boolean makeTable) {
		try{
			String insertCustQuery,insertLogQuery;
			String strDob =  dateToStringFormat(dob);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(maxCustIndexQuery);	
			resultSet.next();
			int index= 0;
			if(resultSet.getObject(1)!=null){
				index = (int) resultSet.getObject(1);
			}
			index++;
			insertCustQuery = "INSERT INTO CUSTOMER(CUST_NUM,CUST_NAME,CUST_PHN_NUM,CUST_EMAIL,CUST_DOB,CUST_ADRS,CUST_APRVD_DRUG)"+
							  "VALUES ("+
							  index+","+
							  "\""+name+"\","+
							  "\""+phone+"\","+
							  "\""+email+"\","+
							  "\""+strDob+"\","+
							  "\""+address+"\","+
							  "\""+drugs+"\""+
							  ")";
			statement.executeUpdate(insertCustQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"INSERT"+"\","+	
						  	 "\""+"CUSTOMER"+"\","+
						  	 "CURRENT_TIMESTAMP"+	
							 ")";
			statement.executeUpdate(insertLogQuery);
			statement.close();
			
			if(makeTable){
				new Table(new Object[][] {{index,name,phone,email,strDob,address,drugs},},custColumnNames);
			}
		}catch(Exception e){}		
	}		
}

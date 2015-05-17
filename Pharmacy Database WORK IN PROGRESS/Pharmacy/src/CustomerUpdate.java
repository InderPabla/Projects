import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;


/**
 * Class allows to update a customer's data within the Customer table.
 * @author INDERPREET PABLA
 */
public class CustomerUpdate extends Operation{

	private JFrame frame;	
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField drugField;
	private JDateChooser dateChooser = new JDateChooser();
	private JTextField indexField;
	private JButton updateButton = new JButton("Update");
	private JButton searchButton = new JButton("Search");
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public CustomerUpdate(String server, String username, String password) {
		createConnection(server,username,password,pharmacyName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//100,100 location on desktop of spawn
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		JLabel updateLabel = new JLabel("Update Customer");
		updateLabel.setForeground(Color.RED);
		updateLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		updateLabel.setBounds(125, 11, 150, 20);
		frame.getContentPane().add(updateLabel);
		
		nameField = new JTextField();
		nameField.setBounds(10, 155, 150, 20);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameLabel.setBounds(10, 138, 32, 14);
		frame.getContentPane().add(nameLabel);
		
		JLabel legendAsteriskLabel = new JLabel("*");
		legendAsteriskLabel.setForeground(Color.RED);
		legendAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendAsteriskLabel.setBounds(10, 437, 7, 14);
		frame.getContentPane().add(legendAsteriskLabel);
		
		JLabel legendLabel = new JLabel("Must be filled");
		legendLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendLabel.setBounds(20, 437, 140, 14);
		frame.getContentPane().add(legendLabel);
		
		JLabel phoneLabel = new JLabel("Phone Number");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneLabel.setBounds(10, 186, 82, 14);
		frame.getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(10, 203, 150, 20);
		frame.getContentPane().add(phoneField);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		emailLabel.setBounds(10, 234, 32, 14);
		frame.getContentPane().add(emailLabel);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(10, 251, 150, 20);
		frame.getContentPane().add(emailField);
		
		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dobLabel.setBounds(10, 282, 71, 14);
		frame.getContentPane().add(dobLabel);
		
		dateChooser.setBounds(10, 297, 150, 20);
		frame.getContentPane().add(dateChooser);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 328, 46, 14);
		frame.getContentPane().add(addressLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 345, 150, 20);
		frame.getContentPane().add(addressField);
		
		JLabel drugLabel = new JLabel("Approved Drug(s)");
		drugLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		drugLabel.setBounds(10, 376, 101, 14);
		frame.getContentPane().add(drugLabel);
		
		drugField = new JTextField();
		drugField.setColumns(10);
		drugField.setBounds(10, 393, 150, 20);
		frame.getContentPane().add(drugField);
		
		
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				update(Integer.parseInt(indexField.getText()),nameField.getText(),phoneField.getText(),emailField.getText(),dateChooser.getDate(),addressField.getText(),drugField.getText(),true);
				frame.dispose();
			}
		});
		updateButton.setForeground(Color.RED);
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateButton.setBounds(170, 428, 89, 23);
		frame.getContentPane().add(updateButton);
		
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	indexField.getText().equals("") || 
						!isInteger(indexField.getText())
				)){
					Object[][] data = getDataWithIndex(Integer.parseInt(indexField.getText()));

					nameField.setText((String) data[0][1]);
					phoneField.setText((String) data[0][2]);
					emailField.setText((String) data[0][3]);
					dateChooser.setDate(stringToDateFormat(data[0][4].toString()));
					addressField.setText((String) data[0][5]);
					drugField.setText((String) data[0][6]);
					
					searchButton.setEnabled(false);
					indexField.setEnabled(false);
					updateButton.setEnabled(true);
				}
			}
		});
		searchButton.setForeground(Color.RED);
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchButton.setBounds(10, 89, 89, 23);
		frame.getContentPane().add(searchButton);
		
		indexField = new JTextField();
		indexField.setColumns(10);
		indexField.setBounds(10, 58, 150, 20);
		frame.getContentPane().add(indexField);
		
		JLabel indexLabel = new JLabel("Customer Number");
		indexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexLabel.setBounds(10, 42, 102, 14);
		frame.getContentPane().add(indexLabel);
		
		JLabel indexAsteriskLabel = new JLabel("*");
		indexAsteriskLabel.setForeground(Color.RED);
		indexAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexAsteriskLabel.setBounds(115, 42, 7, 14);
		frame.getContentPane().add(indexAsteriskLabel);
		frame.setVisible(true);
	}
	
	/**
	 * Getting all customer data given index.
	 * @param index Index of the customer to get data from.
	 */
	public Object[][] getDataWithIndex(int index){
		Object[][] resultSetArray = null;
		try{
			String query = "SELECT * FROM CUSTOMER WHERE (CUST_NUM = "+index+")";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);	
			metaData = resultSet.getMetaData();	
			ArrayList<Object[]> resultSetList = new ArrayList<Object[]>();
			
			int numberOfColumns = metaData.getColumnCount();
			
			while(resultSet.next()){
				Object[] resultSetIndividual = new Object[numberOfColumns];
				for(int i=1 ; i<=numberOfColumns ; i++){
					resultSetIndividual[i-1] = resultSet.getObject(i);				
				}
				resultSetList.add(resultSetIndividual);
				index++;
			}
			
			resultSetArray = new Object[index][numberOfColumns];
			for(int i=0;i<1;i++){
				Object[] obj = resultSetList.get(i);
				for(int j =0;j<numberOfColumns;j++){
					resultSetArray[i][j] = obj[j]; 
				}
			}
		}catch(Exception e){System.out.println("err");}
		return resultSetArray;
	}
	
	/**
	 * Updating customer data in Customer table.
	 * @param index Index of the customer to update.
	 * @param name Name of the customer.
	 * @param phone Phone number of the customer.
	 * @param email Email of the customer.
	 * @param dob Date of birth of the customer
	 * @param address Address of the customer.
	 * @param drugs Drug list of the customer(Advil,Benadryl).
	 * @param makeTable If true a window will open to show a table with the customer number index and relevant information. 
	 */
	public void update(int index, String name, String phone, String email, Date dob, String address, String drugs,boolean makeTable) {
		try{
			String updateCustQuery,insertLogQuery;
			String strDob =  dateToStringFormat(dob);


			updateCustQuery = "UPDATE CUSTOMER SET "+
							  "CUST_NAME = \""+name+"\","+
							  "CUST_PHN_NUM = \""+phone+"\","+
							  "CUST_EMAIL = \""+email+"\","+
							  "CUST_DOB = \""+strDob+"\","+
							  "CUST_ADRS = \""+address+"\","+
							  "CUST_APRVD_DRUG = \""+drugs+"\" "+
							  "WHERE CUST_NUM = "+index;
							  
			statement.executeUpdate(updateCustQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"UPDATE"+"\","+	
						  	 "\""+"CUSTOMER"+"\","+
						  	 "CURRENT_TIMESTAMP"+	
							 ")";
			statement.executeUpdate(insertLogQuery);
			statement.close();
			
			if(makeTable){
				new Table(new Object[][] {{index,name,phone,email,strDob,address,drugs},},custColumnNames);
			}
		}catch(Exception e){System.out.println("err");}		
	}
	
}

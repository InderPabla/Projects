import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class allows to create a pharmacy query insert into the Pharmacy table.
 * @author INDERPREET PABLA
 */
public class PharmacyNew extends Operation{

	private JFrame frame;
	private JTextField addressField;
	private JTextField hoursField;
	private JTextField phoneField;
	private Dimension dimension = new Dimension(400,400);
	
	/**
	 * Constructor for quick insert of information without need for visual display.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @param address Address of the pharmacy store.
	 * @param storeHours Store hours the pharmacy store is open.
	 * @param phone Phone number of the pharmacy store.
	 */
	public PharmacyNew(String server, String username, String password, String address, String storeHours, String phone){
		createConnection(server,username,password,pharmacyName);
		insert(address,storeHours,phone,false);
	}
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public PharmacyNew(String server, String username, String password) {
		createConnection(server,username,password,pharmacyName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, dimension.width, dimension.height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel pharmacyLabel = new JLabel("New Pharmacy");
		pharmacyLabel.setForeground(new Color(255, 153, 0));
		pharmacyLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		pharmacyLabel.setBounds(136, 11, 126, 20);
		frame.getContentPane().add(pharmacyLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 55, 150, 20);
		frame.getContentPane().add(addressField);
		
		JLabel addressLabel = new JLabel("Store Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 38, 80, 14);
		frame.getContentPane().add(addressLabel);
		
		JLabel addressAsteriskLabel = new JLabel("*");
		addressAsteriskLabel.setForeground(Color.RED);
		addressAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressAsteriskLabel.setBounds(95, 38, 18, 14);
		frame.getContentPane().add(addressAsteriskLabel);
		
		JLabel legendAsteriskLabel = new JLabel("*");
		legendAsteriskLabel.setForeground(Color.RED);
		legendAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendAsteriskLabel.setBounds(10, 337, 7, 14);
		frame.getContentPane().add(legendAsteriskLabel);
		
		JLabel legendLabel = new JLabel("Must be filled");
		legendLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendLabel.setBounds(20, 337, 140, 14);
		frame.getContentPane().add(legendLabel);
		
		JLabel hoursLabel = new JLabel("Store Hours (9am-5pm)");
		hoursLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hoursLabel.setBounds(10, 86, 135, 14);
		frame.getContentPane().add(hoursLabel);
		
		hoursField = new JTextField();
		hoursField.setColumns(10);
		hoursField.setBounds(10, 103, 150, 20);
		frame.getContentPane().add(hoursField);
		
		JLabel hoursAsteriskLabel = new JLabel("*");
		hoursAsteriskLabel.setForeground(Color.RED);
		hoursAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hoursAsteriskLabel.setBounds(148, 86, 18, 14);
		frame.getContentPane().add(hoursAsteriskLabel);
		
		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneLabel.setBounds(10, 134, 35, 14);
		frame.getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(10, 151, 150, 20);
		frame.getContentPane().add(phoneField);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	addressField.getText().equals("") || 
						hoursField.getText().equals("") || 
						phoneField.getText().equals("")	
				)){										
					insert(	addressField.getText(),
							hoursField.getText(),
							phoneField.getText(),
							true);
					frame.dispose();	
				}
			}
		});
		createButton.setForeground(new Color(255, 153, 0));
		createButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		createButton.setBounds(170, 328, 89, 23);
		frame.getContentPane().add(createButton);
		
		JLabel phoneAsteriskLabel = new JLabel("*");
		phoneAsteriskLabel.setForeground(Color.RED);
		phoneAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneAsteriskLabel.setBounds(47, 134, 18, 14);
		frame.getContentPane().add(phoneAsteriskLabel);
	}
	
	/**
	 * Inserting inventory data to Pharmacy table in Pharmacy database.
	 * @param address Address of the pharmacy store.
	 * @param storeHours Store hours the pharmacy store is open.
	 * @param phone Phone number of the pharmacy store.
	 * @param makeTable If true a window will open to show a table with the store number index and relevant information.
	 */
	public void insert(String address, String storeHours, String phone,boolean makeTable){
		try{
			String insertCustQuery,insertLogQuery;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(maxPharIndexQuery);	
			resultSet.next();
			int index= 0;
			if(resultSet.getObject(1)!=null){
				index = (int) resultSet.getObject(1);
			}
			index++;
			insertCustQuery = "INSERT INTO PHARMACY(STORE_NUM,STORE_ADRS,STORE_HRS,STORE_PHN_NUM)"+
							  "VALUES ("+
							  index+","+
							  "\""+address+"\","+
							  "\""+storeHours+"\","+
							  "\""+phone+"\""+
							  ")";
			statement.executeUpdate(insertCustQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"INSERT"+"\","+	
						  	 "\""+"PHARMACY"+"\","+
						  	 "CURRENT_TIMESTAMP"+	
							 ")";
			statement.executeUpdate(insertLogQuery);
			statement.close();
			
			if(makeTable){
				new Table(new Object[][] {{index,address,storeHours,phone},},pharColumnNames);
			}			
		}catch(Exception e){System.out.println("phar err");}
	}
}

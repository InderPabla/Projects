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
 * Class allows to create a supplier query insert into the Supplier table.
 * @author INDERPREET PABLA
 */
public class SupplierNew extends Operation{

	private JFrame frame;
	Dimension dimension = new Dimension(400,400);
	private JTextField addressField;
	private JTextField nameField;
	private JTextField phoneField;
	
	/**
	 * Constructor for quick insert of information without need for visual display.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @param address Address of the supplier.
	 * @param name Name of the supplier.
	 * @param phone Phone number of the supplier.
	 */
	public SupplierNew(String server, String username, String password, String address, String name, String phone){
		createConnection(server,username,password,pharmacyName);
		insert(address,name,phone,false);
	}
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public SupplierNew(String server, String username, String password) {
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
		
		JLabel supplierLabel = new JLabel("New Supplier");
		supplierLabel.setForeground(new Color(0, 128, 0));
		supplierLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplierLabel.setBounds(136, 11, 126, 20);
		frame.getContentPane().add(supplierLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 55, 150, 20);
		frame.getContentPane().add(addressField);
		
		JLabel addressLabel = new JLabel("Supplier Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 38, 95, 14);
		frame.getContentPane().add(addressLabel);
		
		JLabel addressAsteriskLabel = new JLabel("*");
		addressAsteriskLabel.setForeground(Color.RED);
		addressAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressAsteriskLabel.setBounds(107, 38, 18, 14);
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
		
		JLabel nameLabel = new JLabel("Supplier Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameLabel.setBounds(10, 86, 81, 14);
		frame.getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(10, 103, 150, 20);
		frame.getContentPane().add(nameField);
		
		JLabel nameAsteriskLabel = new JLabel("*");
		nameAsteriskLabel.setForeground(Color.RED);
		nameAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameAsteriskLabel.setBounds(95, 86, 18, 14);
		frame.getContentPane().add(nameAsteriskLabel);
		
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
						nameField.getText().equals("") || 
						phoneField.getText().equals("")	
				)){										
					insert(	addressField.getText(),
							nameField.getText(),
							phoneField.getText(),
							true);
					frame.dispose();	
				}
			}
		});
		createButton.setForeground(new Color(0, 128, 0));
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
	 * Inserting inventory data to Supplier table in Pharmacy database.
	 * @param address Address of the supplier.
	 * @param name Name of the supplier.
	 * @param phone Phone number of the supplier.
	 * @param makeTable If true a window will open to show a table with the supplier number index and relevant information.
	 */
	public void insert(String address, String name, String phone,boolean makeTable){
		try{
			String insertCustQuery,insertLogQuery;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(maxSupIndexQuery);	
			resultSet.next();
			int index= 0;
			if(resultSet.getObject(1)!=null){
				index = (int) resultSet.getObject(1);
			}
			index++;
			insertCustQuery = "INSERT INTO SUPPLIER(SUP_NUM,SUP_ADRS,SUP_NAME,SUP_PHN_NUM)"+
							  "VALUES ("+
							  index+","+
							  "\""+address+"\","+
							  "\""+name+"\","+
							  "\""+phone+"\""+
							  ")";
			statement.executeUpdate(insertCustQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"INSERT"+"\","+	
						  	 "\""+"SUPPLIER"+"\","+
						  	 "CURRENT_TIMESTAMP"+	
							 ")";
			statement.executeUpdate(insertLogQuery);
			statement.close();
			
			if(makeTable){
				new Table(new Object[][] {{index,address,name,phone},},supColumnNames);
			}			
		}catch(Exception e){System.out.println("sup err");}
	}
}

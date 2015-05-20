import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Class allows to update a supplier in the Supplier table.
 * @author INDERPREET PABLA
 */
public class SupplierUpdate extends Operation{

	private JFrame frame;

	private JTextField addressField;
	private JTextField nameField;
	private JTextField phoneField;
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
	public SupplierUpdate(String server, String username, String password) {
		createConnection(server,username,password,pharmacyName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel supplierLabel = new JLabel("Update Supplier");
		supplierLabel.setForeground(new Color(0, 128, 0));
		supplierLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplierLabel.setBounds(136, 11, 131, 20);
		frame.getContentPane().add(supplierLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 155, 150, 20);
		frame.getContentPane().add(addressField);
		
		JLabel addressLabel = new JLabel("Supplier Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 138, 95, 14);
		frame.getContentPane().add(addressLabel);
		
		JLabel legendAsteriskLabel = new JLabel("*");
		legendAsteriskLabel.setForeground(Color.RED);
		legendAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendAsteriskLabel.setBounds(10, 437, 7, 14);
		frame.getContentPane().add(legendAsteriskLabel);
		
		JLabel legendLabel = new JLabel("Must be filled");
		legendLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendLabel.setBounds(20, 437, 140, 14);
		frame.getContentPane().add(legendLabel);
		
		JLabel nameLabel = new JLabel("Supplier Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameLabel.setBounds(10, 186, 81, 14);
		frame.getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(10, 203, 150, 20);
		frame.getContentPane().add(nameField);
		
		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneLabel.setBounds(10, 234, 35, 14);
		frame.getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(10, 251, 150, 20);
		frame.getContentPane().add(phoneField);
		
		
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {									
				update(	Integer.parseInt(indexField.getText()),
						addressField.getText(),
						nameField.getText(),
						phoneField.getText(),
						true);
				frame.dispose();	
			}
		});
		updateButton.setForeground(new Color(0, 128, 0));
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateButton.setBounds(170, 428, 89, 23);
		frame.getContentPane().add(updateButton);
		
		JLabel indexLabel = new JLabel("Inventory Number");
		indexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexLabel.setBounds(10, 42, 104, 14);
		frame.getContentPane().add(indexLabel);
		
		JLabel indexAsteriskLabel = new JLabel("*");
		indexAsteriskLabel.setForeground(Color.RED);
		indexAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexAsteriskLabel.setBounds(119, 42, 7, 14);
		frame.getContentPane().add(indexAsteriskLabel);
		
		indexField = new JTextField();
		indexField.setColumns(10);
		indexField.setBounds(10, 58, 150, 20);
		frame.getContentPane().add(indexField);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	!isInteger(indexField.getText()) ||
						indexField.getText().equals("") 	
				)){										
					Object[][] data = getDataWithIndex(Integer.parseInt(indexField.getText()));
					addressField.setText((String)data[0][1]);
					nameField.setText((String)data[0][2]);
					phoneField.setText((String)data[0][3]);
					
					searchButton.setEnabled(false);
					indexField.setEnabled(false);
					updateButton.setEnabled(true);
				}
			}
		});
		searchButton.setForeground(new Color(0, 128, 0));
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchButton.setBounds(10, 89, 89, 23);
		frame.getContentPane().add(searchButton);
	}
	
	/**
	 * Getting all supplier data given index.
	 * @param index Index of the supplier to get data from.
	 */
	public Object[][] getDataWithIndex(int index){
		Object[][] resultSetArray = null;
		try{
			String query = "SELECT * FROM SUPPLIER WHERE (SUP_NUM = "+index+")";
			
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
	 * Updating supplier data in Supplier table.
	 * @param index Index of the supplier to update.
	 * @param address Address of the supplier.
	 * @param name Name of the supplier.
	 * @param phone Phone number of the supplier.
	 * @param makeTable If true a window will open to show a table with the supplier number index and relevant information.
	 */
	public void update(int index, String address, String name, String phone,boolean makeTable){
		try{
			String updateSupQuery,insertLogQuery;
			
			
			updateSupQuery = "UPDATE SUPPLIER SET "+
					  "SUP_ADRS = \""+address+"\","+
					  "SUP_NAME = \""+name+"\","+
					  "SUP_PHN_NUM = \""+phone+"\""+
					  "WHERE SUP_NUM = "+index;
			statement.executeUpdate(updateSupQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"UPDATE"+"\","+	
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

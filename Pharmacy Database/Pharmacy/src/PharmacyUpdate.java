import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Class allows to update a pharmacy in the Pharmacy table.
 * @author INDERPREET PABLA
 */
public class PharmacyUpdate extends Operation{

	private JFrame frame;
	
	private JTextField addressField;
	private JTextField hoursField;
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
	public PharmacyUpdate(String server, String username, String password) {
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
		
		JLabel pharmacyLabel = new JLabel("Update Pharmacy");
		pharmacyLabel.setForeground(new Color(255, 153, 0));
		pharmacyLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		pharmacyLabel.setBounds(130, 11, 150, 20);
		frame.getContentPane().add(pharmacyLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 155, 150, 20);
		frame.getContentPane().add(addressField);
		
		JLabel addressLabel = new JLabel("Store Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 138, 80, 14);
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
		
		JLabel hoursLabel = new JLabel("Store Hours (9am-5pm)");
		hoursLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hoursLabel.setBounds(10, 186, 135, 14);
		frame.getContentPane().add(hoursLabel);
		
		hoursField = new JTextField();
		hoursField.setColumns(10);
		hoursField.setBounds(10, 203, 150, 20);
		frame.getContentPane().add(hoursField);
		
		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneLabel.setBounds(10, 234, 35, 14);
		frame.getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(10, 251, 150, 20);
		frame.getContentPane().add(phoneField);
		
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				update(	Integer.parseInt(indexField.getText()),
						addressField.getText(),
						hoursField.getText(),
						phoneField.getText(),
						true);
				frame.dispose();	
			}
		});
		updateButton.setForeground(new Color(255, 153, 0));
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateButton.setBounds(170, 428, 89, 23);
		frame.getContentPane().add(updateButton);
		
		indexField = new JTextField();
		indexField.setColumns(10);
		indexField.setBounds(10, 58, 150, 20);
		frame.getContentPane().add(indexField);
		
		JLabel indexLabel = new JLabel("Pharmacy Number");
		indexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexLabel.setBounds(10, 42, 104, 14);
		frame.getContentPane().add(indexLabel);
		
		JLabel indexAsteriskLabel = new JLabel("*");
		indexAsteriskLabel.setForeground(Color.RED);
		indexAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexAsteriskLabel.setBounds(119, 42, 7, 14);
		frame.getContentPane().add(indexAsteriskLabel);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(	!isInteger(indexField.getText()) ||
						indexField.getText().equals("") 	
				)){										
					Object[][] data = getDataWithIndex(Integer.parseInt(indexField.getText()));
					addressField.setText((String)data[0][1]);
					hoursField.setText((String)data[0][2]);
					phoneField.setText((String)data[0][3]);
					
					searchButton.setEnabled(false);
					indexField.setEnabled(false);
					updateButton.setEnabled(true);
				}
			}
		});
		
		searchButton.setForeground(new Color(255, 153, 0));
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchButton.setBounds(10, 89, 89, 23);
		frame.getContentPane().add(searchButton);
	}
	
	/**
	 * Getting all pharmacy data given index.
	 * @param index Index of the pharmacy to get data from.
	 */
	public Object[][] getDataWithIndex(int index){
		Object[][] resultSetArray = null;
		try{
			String query = "SELECT * FROM PHARMACY WHERE (STORE_NUM = "+index+")";
			
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
	 * Updating pharmacy data in Pharmacy table.
	 * @param index Index of the pharmacy to update.
	 * @param address Address of the pharmacy.
	 * @param name Name of the pharmacy.
	 * @param phone Phone number of the pharmacy.
	 * @param makeTable If true a window will open to show a table with the pharmacy number index and relevant information.
	 */
	public void update(int index, String address, String storeHours, String phone,boolean makeTable){
		try{
			String updateSupQuery,insertLogQuery;
			
			
			updateSupQuery = "UPDATE PHARMACY SET "+
					  "STORE_ADRS = \""+address+"\","+
					  "STORE_HRS = \""+storeHours+"\","+
					  "STORE_PHN_NUM = \""+phone+"\""+
					  "WHERE STORE_NUM = "+index;
			statement.executeUpdate(updateSupQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"UPDATE"+"\","+	
						  	 "\""+"PHARMACY"+"\","+
						  	 "CURRENT_TIMESTAMP"+	
							 ")";
			statement.executeUpdate(insertLogQuery);
			statement.close();
			
			if(makeTable){
				new Table(new Object[][] {{index,address,storeHours,phone},},supColumnNames);
			}			
		}catch(Exception e){System.out.println("sup err");}
	}
}
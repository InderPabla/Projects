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
 * Class allows to update an inventory in the Inventory table.
 * @author INDERPREET PABLA
 */
public class InventoryUpdate extends Operation{

	private JFrame frame;
	
	private JTextField icfsField;
	private JTextField ictcField;
	private JTextField itemDescriptionField;
	private JTextField itemInsCovField;
	private JTextField itemPresField;
	private JTextField numberField;
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
	public InventoryUpdate(String server, String username, String password) {
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
		
		JLabel inventoryLabel = new JLabel("Update Inventory");
		inventoryLabel.setForeground(Color.BLACK);
		inventoryLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		inventoryLabel.setBounds(119, 11, 150, 20);
		frame.getContentPane().add(inventoryLabel);
		
		icfsField = new JTextField();
		icfsField.setColumns(10);
		icfsField.setBounds(10, 155, 150, 20);
		frame.getContentPane().add(icfsField);
		
		JLabel icfsLabel = new JLabel("Item Cost From Supplier");
		icfsLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		icfsLabel.setBounds(10, 138, 140, 14);
		frame.getContentPane().add(icfsLabel);
		
		JLabel legendAsteriskLabel = new JLabel("*");
		legendAsteriskLabel.setForeground(Color.RED);
		legendAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendAsteriskLabel.setBounds(10, 437, 7, 14);
		frame.getContentPane().add(legendAsteriskLabel);
		
		JLabel legendLabel = new JLabel("Must be filled");
		legendLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendLabel.setBounds(20, 437, 140, 14);
		frame.getContentPane().add(legendLabel);
		
		JLabel ictcLabel = new JLabel("Item Cost To Customer");
		ictcLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ictcLabel.setBounds(10, 186, 131, 14);
		frame.getContentPane().add(ictcLabel);
		
		ictcField = new JTextField();
		ictcField.setColumns(10);
		ictcField.setBounds(10, 203, 150, 20);
		frame.getContentPane().add(ictcField);
		
		JLabel itemDescriptionLabel = new JLabel("Item Description");
		itemDescriptionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemDescriptionLabel.setBounds(10, 234, 95, 14);
		frame.getContentPane().add(itemDescriptionLabel);
		
		itemDescriptionField = new JTextField();
		itemDescriptionField.setColumns(10);
		itemDescriptionField.setBounds(10, 251, 150, 20);
		frame.getContentPane().add(itemDescriptionField);
		
		
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				int ins = Integer.parseInt(itemInsCovField.getText());
				int pers = Integer.parseInt(itemPresField.getText());
				if((ins==0||ins==1)&&(pers==0||pers==1)){
					update(	Integer.parseInt(indexField.getText()),
							Float.parseFloat(icfsField.getText()), 
							Float.parseFloat(ictcField.getText()),
							itemDescriptionField.getText(),
							Integer.parseInt(itemInsCovField.getText()), 
							Integer.parseInt(itemPresField.getText()), 	
							Integer.parseInt(numberField.getText()), 
							true);
					frame.dispose();
				}
				
			}
		});
		updateButton.setForeground(Color.BLACK);
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateButton.setBounds(170, 428, 89, 23);
		frame.getContentPane().add(updateButton);
		
		JLabel itemInsCovLabel = new JLabel("Item Insurance Coverage (0 = No, 1 = Yes)");
		itemInsCovLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemInsCovLabel.setBounds(10, 282, 242, 14);
		frame.getContentPane().add(itemInsCovLabel);
		
		itemInsCovField = new JTextField();
		itemInsCovField.setColumns(10);
		itemInsCovField.setBounds(10, 299, 150, 20);
		frame.getContentPane().add(itemInsCovField);
		
		JLabel itemPresLabel = new JLabel("Item Prescription Requried (0 = No, 1 = Yes)");
		itemPresLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemPresLabel.setBounds(10, 330, 252, 14);
		frame.getContentPane().add(itemPresLabel);
		
		itemPresField = new JTextField();
		itemPresField.setColumns(10);
		itemPresField.setBounds(10, 347, 150, 20);
		frame.getContentPane().add(itemPresField);
		
		JLabel numberLabel = new JLabel("Supplier Number");
		numberLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numberLabel.setBounds(10, 378, 93, 14);
		frame.getContentPane().add(numberLabel);
		
		numberField = new JTextField();
		numberField.setColumns(10);
		numberField.setBounds(10, 395, 150, 20);
		frame.getContentPane().add(numberField);
		
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
			public void actionPerformed(ActionEvent event ) {
				if(!(	!isInteger(indexField.getText()) ||
						indexField.getText().equals("") 	
				)){					
					Object[][] data = getDataWithIndex(Integer.parseInt(indexField.getText()));
					icfsField.setText(data[0][1]+"");
					ictcField.setText(data[0][2]+"");
					itemDescriptionField.setText((String)data[0][3]);
					itemInsCovField.setText(data[0][4]+"");
					itemPresField.setText(data[0][5]+"");
					numberField.setText(data[0][6]+"");
					
					searchButton.setEnabled(false);
					indexField.setEnabled(false);
					updateButton.setEnabled(true);
				}
			}
		});
		searchButton.setForeground(Color.BLUE);
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchButton.setBounds(10, 89, 89, 23);
		frame.getContentPane().add(searchButton);
	}
	
	/**
	 * Getting all inventory data given index.
	 * @param index Index of the inventory to get data from.
	 */
	public Object[][] getDataWithIndex(int index){
		Object[][] resultSetArray = null;
		try{
			String query = "SELECT * FROM INVENTORY WHERE (ITEM_NUM = "+index+")";
			
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
	 * Updating inventory data in Inventory table.
	 * @param index Index of the inventory to update.
	 * @param itemCostFromSup Item cost from supplier.
	 * @param itemCostToCust Item cost to customer.
	 * @param itemDesc Item description (its name).
	 * @param itemInsCovg 1 = Yes, the item is insured, 0 = No, it's not.
	 * @param itemPrescription 1 = Yes, the item requires a prescription, 0 = No, it doesn't.
	 * @param supNum The supplier number who supplied the product.
	 * @param makeTable If true a window will open to show a table with the item number index and relevant information. 
	 */
	public void update(int index, float itemCostFromSup, float itemCostToCust, String itemDesc, int itemInsCovg, int itemPrescription, int supNum,boolean makeTable){
		try{
			String updateInvQuery,insertLogQuery;
			
			updateInvQuery = "UPDATE INVENTORY SET "+
					  "ITEM_CST_FROM_SUP = "+itemCostFromSup+","+
					  "ITEM_CST_TO_CUST = "+itemCostToCust+","+
					  "ITEM_DESC = \""+itemDesc+"\","+
					  "ITEM_INS_COVG = "+itemInsCovg+","+
					  "ITEM_PRESCRIPTION = "+itemPrescription+","+
					  "SUP_NUM = "+supNum+" "+
					  "WHERE ITEM_NUM = "+index;
			
			statement.executeUpdate(updateInvQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"UPDATE"+"\","+	
						  	 "\""+"INVENTORY"+"\","+
						  	 "CURRENT_TIMESTAMP"+	
							 ")";
			statement.executeUpdate(insertLogQuery);
			statement.close();
			
			if(makeTable){
				new Table(new Object[][] {{index,itemCostFromSup,itemCostToCust,itemDesc,itemInsCovg,itemPrescription, supNum},},invColumnNames);
			}			
		}catch(Exception e){System.out.println("inv err");}
	}

}
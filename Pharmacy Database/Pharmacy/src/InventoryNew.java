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
 * Class allows to create an inventory query insert into the Inventory table.
 * @author INDERPREET PABLA
 */
public class InventoryNew extends Operation{

	private JFrame frame;
	private Dimension dimension = new Dimension(400,400);
	
	private JTextField icfsField;
	private JTextField ictcField;
	private JTextField itemDescriptionField;
	private JTextField itemInsCovField;
	private JTextField itemPresField;
	private JTextField numberField;
	
	/**
	 * Constructor for quick insert of information without need for visual display.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @param itemCostFromSup Item cost from supplier.
	 * @param itemCostToCust Item cost to customer.
	 * @param itemDesc Item description (its name).
	 * @param itemInsCovg 1 = Yes, the item is insured, 0 = No, it's not.
	 * @param itemPrescription 1 = Yes, the item requires a prescription, 0 = No, it doesn't.
	 * @param supNum The supplier number who supplied the product.
	 */
	public InventoryNew(String server, String username, String password, float itemCostFromSup, float itemCostToCust, String itemDesc, int itemInsCovg, int itemPrescription, int supNum){
		createConnection(server,username,password,pharmacyName);
		insert(itemCostFromSup,itemCostToCust,itemDesc,itemInsCovg,itemPrescription, supNum,false);
	}
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public InventoryNew(String server, String username, String password) {
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
		
		JLabel inventoryLabel = new JLabel("New Inventory");
		inventoryLabel.setForeground(Color.BLACK);
		inventoryLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		inventoryLabel.setBounds(136, 11, 126, 20);
		frame.getContentPane().add(inventoryLabel);
		
		icfsField = new JTextField();
		icfsField.setColumns(10);
		icfsField.setBounds(10, 55, 150, 20);
		frame.getContentPane().add(icfsField);
		
		JLabel icfsLabel = new JLabel("Item Cost From Supplier");
		icfsLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		icfsLabel.setBounds(10, 38, 140, 14);
		frame.getContentPane().add(icfsLabel);
		
		JLabel icfsAsteriskLabel = new JLabel("*");
		icfsAsteriskLabel.setForeground(Color.RED);
		icfsAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		icfsAsteriskLabel.setBounds(150, 38, 18, 14);
		frame.getContentPane().add(icfsAsteriskLabel);
		
		JLabel legendAsteriskLabel = new JLabel("*");
		legendAsteriskLabel.setForeground(Color.RED);
		legendAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendAsteriskLabel.setBounds(10, 337, 7, 14);
		frame.getContentPane().add(legendAsteriskLabel);
		
		JLabel legendLabel = new JLabel("Must be filled");
		legendLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendLabel.setBounds(20, 337, 140, 14);
		frame.getContentPane().add(legendLabel);
		
		JLabel ictcLabel = new JLabel("Item Cost To Customer");
		ictcLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ictcLabel.setBounds(10, 86, 131, 14);
		frame.getContentPane().add(ictcLabel);
		
		ictcField = new JTextField();
		ictcField.setColumns(10);
		ictcField.setBounds(10, 103, 150, 20);
		frame.getContentPane().add(ictcField);
		
		JLabel ictcAsteriskLabel = new JLabel("*");
		ictcAsteriskLabel.setForeground(Color.RED);
		ictcAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ictcAsteriskLabel.setBounds(144, 86, 18, 14);
		frame.getContentPane().add(ictcAsteriskLabel);
		
		JLabel itemDescriptionLabel = new JLabel("Item Description");
		itemDescriptionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemDescriptionLabel.setBounds(10, 134, 95, 14);
		frame.getContentPane().add(itemDescriptionLabel);
		
		itemDescriptionField = new JTextField();
		itemDescriptionField.setColumns(10);
		itemDescriptionField.setBounds(10, 151, 150, 20);
		frame.getContentPane().add(itemDescriptionField);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	!isFloat(icfsField.getText()) ||
						icfsField.getText().equals("") || 
						!isFloat(ictcField.getText()) ||
						ictcField.getText().equals("") ||
						itemDescriptionField.getText().equals("") ||
						!isInteger(itemInsCovField.getText()) ||
						itemInsCovField.getText().equals("") ||
						!isInteger(itemPresField.getText()) ||
						itemPresField.getText().equals("") ||
						!isInteger(numberField.getText()) ||
						numberField.getText().equals("") 	
				)){					
					int ins = Integer.parseInt(itemInsCovField.getText());
					int pers = Integer.parseInt(itemPresField.getText());
					if((ins==0||ins==1)&&(pers==0||pers==1)){
						insert(	Float.parseFloat(icfsField.getText()), 
								Float.parseFloat(ictcField.getText()),
								itemDescriptionField.getText(),
								Integer.parseInt(itemInsCovField.getText()), 
								Integer.parseInt(itemPresField.getText()), 	
								Integer.parseInt(numberField.getText()), 
								true);
						frame.dispose();
					}
				}
			}
		});
		createButton.setForeground(Color.BLACK);
		createButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		createButton.setBounds(170, 328, 89, 23);
		frame.getContentPane().add(createButton);
		
		JLabel itemInsCovLabel = new JLabel("Item Insurance Coverage (0 = No, 1 = Yes)");
		itemInsCovLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemInsCovLabel.setBounds(10, 182, 242, 14);
		frame.getContentPane().add(itemInsCovLabel);
		
		JLabel itemInsCovAsteriskLabel = new JLabel("*");
		itemInsCovAsteriskLabel.setForeground(Color.RED);
		itemInsCovAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemInsCovAsteriskLabel.setBounds(255, 182, 18, 14);
		frame.getContentPane().add(itemInsCovAsteriskLabel);
		
		itemInsCovField = new JTextField();
		itemInsCovField.setColumns(10);
		itemInsCovField.setBounds(10, 199, 150, 20);
		frame.getContentPane().add(itemInsCovField);
		
		JLabel itemPresLabel = new JLabel("Item Prescription Requried (0 = No, 1 = Yes)");
		itemPresLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemPresLabel.setBounds(10, 230, 252, 14);
		frame.getContentPane().add(itemPresLabel);
		
		JLabel itemPresAsteriskLabel = new JLabel("*");
		itemPresAsteriskLabel.setForeground(Color.RED);
		itemPresAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemPresAsteriskLabel.setBounds(263, 230, 18, 14);
		frame.getContentPane().add(itemPresAsteriskLabel);
		
		itemPresField = new JTextField();
		itemPresField.setColumns(10);
		itemPresField.setBounds(10, 247, 150, 20);
		frame.getContentPane().add(itemPresField);
		
		JLabel numberLabel = new JLabel("Supplier Number");
		numberLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numberLabel.setBounds(10, 278, 93, 14);
		frame.getContentPane().add(numberLabel);
		
		JLabel numberAsteriskLabel = new JLabel("*");
		numberAsteriskLabel.setForeground(Color.RED);
		numberAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numberAsteriskLabel.setBounds(107, 278, 18, 14);
		frame.getContentPane().add(numberAsteriskLabel);
		
		numberField = new JTextField();
		numberField.setColumns(10);
		numberField.setBounds(10, 295, 150, 20);
		frame.getContentPane().add(numberField);
		
		JLabel itemDescriptionAsteriskLabel = new JLabel("*");
		itemDescriptionAsteriskLabel.setForeground(Color.RED);
		itemDescriptionAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemDescriptionAsteriskLabel.setBounds(107, 134, 18, 14);
		frame.getContentPane().add(itemDescriptionAsteriskLabel);
	}
	
	/**
	 * Inserting inventory data to Inventory table in Pharmacy database.
	 * @param itemCostFromSup Item cost from supplier.
	 * @param itemCostToCust Item cost to customer.
	 * @param itemDesc Item description (its name).
	 * @param itemInsCovg 1 = Yes, the item is insured, 0 = No, it's not.
	 * @param itemPrescription 1 = Yes, the item requires a prescription, 0 = No, it doesn't.
	 * @param supNum The supplier number who supplied the product.
	 * @param makeTable If true a window will open to show a table with the item number index and relevant information. 
	 */
	public void insert(float itemCostFromSup, float itemCostToCust, String itemDesc, int itemInsCovg, int itemPrescription, int supNum,boolean makeTable){
		try{
			String insertCustQuery,insertLogQuery;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(maxInvIndexQuery);	
			resultSet.next();
			int index= 0;
			if(resultSet.getObject(1)!=null){
				index = (int) resultSet.getObject(1);
			}
			index++;
			insertCustQuery = "INSERT INTO INVENTORY(ITEM_NUM,ITEM_CST_FROM_SUP,ITEM_CST_TO_CUST,ITEM_DESC,ITEM_INS_COVG,ITEM_PRESCRIPTION,SUP_NUM)"+
							  "VALUES ("+
							  index+","+
							  itemCostFromSup+","+
							  itemCostToCust+","+
							  "\""+itemDesc+"\","+
							  itemInsCovg+","+
							  itemPrescription+","+
							  supNum+
							  ")";
			statement.executeUpdate(insertCustQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"INSERT"+"\","+	
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
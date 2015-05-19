import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

/**
 * Class allows to search for inventories in the Inventory table.
 * @author INDERPREET PABLA
 */
public class InventorySearch extends Operation{

	private JFrame frame;
	
	private JTextField indexField1;
	private JTextField indexField2;
	private JTextField itemDescField;
	private JTextField icfsField1;
	private JTextField icfsField2;
	private JTextField ictcField1;
	private JTextField ictcField2;
	private JTextField itemInsCovgField;
	private JTextField itemPresField;
	private JTextField numberField1;
	private JTextField numberField2;

	/**
	 * Create the application.
	 */
	public InventorySearch(String server, String username, String password) {
		createConnection(server,username,password,pharmacyName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 430, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 415, 462);
		scrollPane.setVisible(true);
		frame.getContentPane().add(scrollPane);
		
		JPanel searchInvPanel = new JPanel();
		scrollPane.setViewportView(searchInvPanel);
		searchInvPanel.setForeground(Color.DARK_GRAY);
		searchInvPanel.setLayout(null);
		searchInvPanel.setPreferredSize(new Dimension(415, 620));
		
		JLabel searchInvLabel = new JLabel("Search Inventory");
		searchInvLabel.setBounds(126, 11, 148, 20);
		searchInvLabel.setForeground(Color.BLACK);
		searchInvLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchInvPanel.add(searchInvLabel);
		
		JPanel indexPanelOut = new JPanel();
		indexPanelOut.setBounds(4, 35, 376, 67);
		indexPanelOut.setForeground(Color.RED);
		indexPanelOut.setBackground(Color.LIGHT_GRAY);
		indexPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Number Search (ITEM_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		searchInvPanel.add(indexPanelOut);
		indexPanelOut.setLayout(null);
		
		JPanel indexPanelIn = new JPanel();
		indexPanelIn.setBackground(Color.WHITE);
		indexPanelIn.setBounds(6, 16, 364, 43);
		indexPanelOut.add(indexPanelIn);
		indexPanelIn.setLayout(null);
		
		JLabel indexLabel = new JLabel("Item Number Search (Ex: 1-10)");
		indexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexLabel.setBounds(10, 0, 211, 14);
		indexPanelIn.add(indexLabel);
		
		indexField1 = new JTextField();
		indexField1.setBounds(10, 17, 107, 20);
		indexPanelIn.add(indexField1);
		indexField1.setColumns(10);
		
		indexField2 = new JTextField();
		indexField2.setColumns(10);
		indexField2.setBounds(139, 17, 107, 20);
		indexPanelIn.add(indexField2);
		
		JLabel indexDashLabel = new JLabel("-");
		indexDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		indexDashLabel.setBounds(125, 20, 10, 14);
		indexPanelIn.add(indexDashLabel);
		
		JButton indexButton = new JButton("Search");
		indexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	!isInteger(indexField1.getText()) ||
						indexField1.getText().equals("") ||
						!isInteger(indexField2.getText()) ||
						indexField2.getText().equals("")
				)){
					searchByIndex(Integer.parseInt(indexField1.getText()),Integer.parseInt(indexField2.getText()),true);			
				}
			}
		});
		indexButton.setBounds(265, 16, 89, 23);
		indexPanelIn.add(indexButton);
		
		JPanel icfsPanelOut = new JPanel();
		icfsPanelOut.setLayout(null);
		icfsPanelOut.setForeground(Color.RED);
		icfsPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Cost From Supplier (ITEM_CST_FROM_SUP)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		icfsPanelOut.setBackground(Color.LIGHT_GRAY);
		icfsPanelOut.setBounds(4, 113, 376, 67);
		searchInvPanel.add(icfsPanelOut);
		
		JPanel icfsPanelIn = new JPanel();
		icfsPanelIn.setLayout(null);
		icfsPanelIn.setBackground(Color.WHITE);
		icfsPanelIn.setBounds(6, 16, 364, 43);
		icfsPanelOut.add(icfsPanelIn);
		
		JLabel icfsLabel = new JLabel("Item Cost From Supplier (Ex: 3.45-6.99)");
		icfsLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		icfsLabel.setBounds(10, 0, 222, 14);
		icfsPanelIn.add(icfsLabel);
		
		icfsField1 = new JTextField();
		icfsField1.setColumns(10);
		icfsField1.setBounds(10, 17, 107, 20);
		icfsPanelIn.add(icfsField1);
		
		icfsField2 = new JTextField();
		icfsField2.setColumns(10);
		icfsField2.setBounds(139, 17, 107, 20);
		icfsPanelIn.add(icfsField2);
		
		JLabel icfsDashLabel = new JLabel("-");
		icfsDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		icfsDashLabel.setBounds(125, 20, 10, 14);
		icfsPanelIn.add(icfsDashLabel);
		
		JButton icfsButton = new JButton("Search");
		icfsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	!isFloat(icfsField1.getText()) ||
						icfsField1.getText().equals("") ||
						!isFloat(icfsField2.getText()) ||
						icfsField2.getText().equals("")
				)){
					searchByItemCostFromSupplier(Float.parseFloat(icfsField1.getText()),Float.parseFloat(icfsField2.getText()),true);			
				}
			}
		});
		icfsButton.setBounds(265, 16, 89, 23);
		icfsPanelIn.add(icfsButton);
		
		JPanel ictcPanelOut = new JPanel();
		ictcPanelOut.setLayout(null);
		ictcPanelOut.setForeground(Color.RED);
		ictcPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Cost To Customer (ITEM_CST_TO_CUST)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ictcPanelOut.setBackground(Color.LIGHT_GRAY);
		ictcPanelOut.setBounds(4, 191, 376, 67);
		searchInvPanel.add(ictcPanelOut);
		
		JPanel ictcPanelIn = new JPanel();
		ictcPanelIn.setLayout(null);
		ictcPanelIn.setBackground(Color.WHITE);
		ictcPanelIn.setBounds(6, 16, 364, 43);
		ictcPanelOut.add(ictcPanelIn);
		
		JLabel ictcLabel = new JLabel("Item Cost To Customer (Ex: 3.45-6.99)");
		ictcLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ictcLabel.setBounds(10, 0, 222, 14);
		ictcPanelIn.add(ictcLabel);
		
		ictcField1 = new JTextField();
		ictcField1.setColumns(10);
		ictcField1.setBounds(10, 17, 107, 20);
		ictcPanelIn.add(ictcField1);
		
		ictcField2 = new JTextField();
		ictcField2.setColumns(10);
		ictcField2.setBounds(139, 17, 107, 20);
		ictcPanelIn.add(ictcField2);
		
		JLabel ictcDashLabel = new JLabel("-");
		ictcDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		ictcDashLabel.setBounds(125, 20, 10, 14);
		ictcPanelIn.add(ictcDashLabel);
		
		JButton ictcButton = new JButton("Search");
		ictcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	!isFloat(ictcField1.getText()) ||
						ictcField1.getText().equals("") ||
						!isFloat(ictcField2.getText()) ||
						ictcField2.getText().equals("")
				)){
					searchByItemCostToCustomer(Float.parseFloat(ictcField1.getText()),Float.parseFloat(ictcField2.getText()),true);			
				}
			}
		});
		ictcButton.setBounds(265, 16, 89, 23);
		ictcPanelIn.add(ictcButton);
		
		JPanel descPanelOut = new JPanel();
		descPanelOut.setBounds(4, 269, 376, 67);
		descPanelOut.setLayout(null);
		descPanelOut.setForeground(Color.RED);
		descPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Description (ITEM_DESC)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		descPanelOut.setBackground(Color.LIGHT_GRAY);
		searchInvPanel.add(descPanelOut);
		
		JPanel descPanelIn = new JPanel();
		descPanelIn.setLayout(null);
		descPanelIn.setBackground(Color.WHITE);
		descPanelIn.setBounds(6, 16, 364, 43);
		descPanelOut.add(descPanelIn);
		
		JLabel itemDescLabel = new JLabel("Item Description (Ex: \"Advil\")");
		itemDescLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemDescLabel.setBounds(10, 0, 196, 14);
		descPanelIn.add(itemDescLabel);
		
		itemDescField = new JTextField();
		itemDescField.setColumns(10);
		itemDescField.setBounds(10, 17, 245, 20);
		descPanelIn.add(itemDescField);
		
		JButton itemDescButton = new JButton("Search");
		itemDescButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchByItemDescription(itemDescField.getText(),true);
			}
		});
		itemDescButton.setBounds(265, 16, 89, 23);
		descPanelIn.add(itemDescButton);
		
		JPanel itemInsCovgPanelOut = new JPanel();
		itemInsCovgPanelOut.setLayout(null);
		itemInsCovgPanelOut.setForeground(Color.RED);
		itemInsCovgPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Insurance Coverage (ITEM_INS_COVG)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		itemInsCovgPanelOut.setBackground(Color.LIGHT_GRAY);
		itemInsCovgPanelOut.setBounds(4, 347, 376, 67);
		searchInvPanel.add(itemInsCovgPanelOut);
		
		JPanel itemInsCovgPanelIn = new JPanel();
		itemInsCovgPanelIn.setLayout(null);
		itemInsCovgPanelIn.setBackground(Color.WHITE);
		itemInsCovgPanelIn.setBounds(6, 16, 364, 43);
		itemInsCovgPanelOut.add(itemInsCovgPanelIn);
		
		JLabel itemInsCovgLabel = new JLabel("Item Insurance Coverage (Ex: 1 = Yes, 0 = No)");
		itemInsCovgLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemInsCovgLabel.setBounds(10, 0, 261, 14);
		itemInsCovgPanelIn.add(itemInsCovgLabel);
		
		itemInsCovgField = new JTextField();
		itemInsCovgField.setColumns(10);
		itemInsCovgField.setBounds(10, 17, 245, 20);
		itemInsCovgPanelIn.add(itemInsCovgField);
		
		JButton itemInsCovgButton = new JButton("Search");
		itemInsCovgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(itemInsCovgField.getText().equals("0") || itemInsCovgField.getText().equals("1")){
					searchByItemInsuranceCoverage(Integer.parseInt(itemInsCovgField.getText()),true);
				}
			}
		});
		itemInsCovgButton.setBounds(265, 16, 89, 23);
		itemInsCovgPanelIn.add(itemInsCovgButton);
		
		JPanel itemPresPanelOut = new JPanel();
		itemPresPanelOut.setLayout(null);
		itemPresPanelOut.setForeground(Color.RED);
		itemPresPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Prescription (ITEM_PRESCRIPTION)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		itemPresPanelOut.setBackground(Color.LIGHT_GRAY);
		itemPresPanelOut.setBounds(4, 425, 376, 67);
		searchInvPanel.add(itemPresPanelOut);
		
		JPanel itemPresPanelIn = new JPanel();
		itemPresPanelIn.setLayout(null);
		itemPresPanelIn.setBackground(Color.WHITE);
		itemPresPanelIn.setBounds(6, 16, 364, 43);
		itemPresPanelOut.add(itemPresPanelIn);
		
		JLabel itemPresLabel = new JLabel("Item Prescription (Ex: 1 = Yes, 0 = No)");
		itemPresLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		itemPresLabel.setBounds(10, 0, 261, 14);
		itemPresPanelIn.add(itemPresLabel);
		
		itemPresField = new JTextField();
		itemPresField.setColumns(10);
		itemPresField.setBounds(10, 17, 245, 20);
		itemPresPanelIn.add(itemPresField);
		
		JButton itemPresButton = new JButton("Search");
		itemPresButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(itemPresField.getText().equals("0") || itemPresField.getText().equals("1")){
					searchByItemPrescription(Integer.parseInt(itemPresField.getText()),true);
				}
			}
		});
		itemPresButton.setBounds(265, 16, 89, 23);
		itemPresPanelIn.add(itemPresButton);
		
		JPanel numberPanelOut = new JPanel();
		numberPanelOut.setLayout(null);
		numberPanelOut.setForeground(Color.RED);
		numberPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Supplier Number (SUP_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		numberPanelOut.setBackground(Color.LIGHT_GRAY);
		numberPanelOut.setBounds(4, 503, 376, 67);
		searchInvPanel.add(numberPanelOut);
		
		JPanel numberPanelIn = new JPanel();
		numberPanelIn.setLayout(null);
		numberPanelIn.setBackground(Color.WHITE);
		numberPanelIn.setBounds(6, 16, 364, 43);
		numberPanelOut.add(numberPanelIn);
		
		JLabel numberLabel = new JLabel("Supplier Number (Ex: 1-3)");
		numberLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numberLabel.setBounds(10, 0, 211, 14);
		numberPanelIn.add(numberLabel);
		
		numberField1 = new JTextField();
		numberField1.setColumns(10);
		numberField1.setBounds(10, 17, 107, 20);
		numberPanelIn.add(numberField1);
		
		numberField2 = new JTextField();
		numberField2.setColumns(10);
		numberField2.setBounds(139, 17, 107, 20);
		numberPanelIn.add(numberField2);
		
		JLabel numberDashLabel = new JLabel("-");
		numberDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		numberDashLabel.setBounds(125, 20, 10, 14);
		numberPanelIn.add(numberDashLabel);
		
		JButton numberButton = new JButton("Search");
		numberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	!isInteger(numberField1.getText()) ||
						numberField1.getText().equals("") ||
						!isInteger(numberField2.getText()) ||
						numberField2.getText().equals("")
				)){
					searchBySupplierNumber(Integer.parseInt(numberField1.getText()),Integer.parseInt(numberField2.getText()),true);			
				}
			}
		});
		numberButton.setBounds(265, 16, 89, 23);
		numberPanelIn.add(numberButton);
		
		JButton allSearchButton = new JButton("Search With All");
		allSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int index1,index2;
				float icfs1,icfs2;
				float ictc1,ictc2;
				int number1,number2;
				int itemInsCovg,itemPres;
				
				if(!isInteger(indexField1.getText()) || 
				   !isInteger(indexField2.getText()) ||
				   indexField1.getText().equals("") ||
				   indexField2.getText().equals("")
				){
					index1 = -1;
					index2 = -1;
				}
				else{
					index1 = Integer.parseInt(indexField1.getText());
					index2 = Integer.parseInt(indexField2.getText());
				}
				
				if(!isFloat(icfsField1.getText()) || 
				   !isFloat(icfsField2.getText()) ||
				   icfsField1.getText().equals("") ||
				   icfsField2.getText().equals("")
				){
						icfs1 = -1f;
						icfs2 = -1f;
				}
				else{
					icfs1 = Float.parseFloat(icfsField1.getText());
					icfs2 = Float.parseFloat(icfsField2.getText());
				}
				
				if(!isFloat(ictcField1.getText()) || 
				   !isFloat(ictcField2.getText()) ||
				   ictcField1.getText().equals("") ||
				   ictcField2.getText().equals("")
				){
						ictc1 = -1f;
						ictc2 = -1f;
				}
				else{
					ictc1 = Float.parseFloat(ictcField1.getText());
					ictc2 = Float.parseFloat(ictcField2.getText());
				}
				
				if(!isInteger(numberField1.getText()) || 
				   !isInteger(numberField2.getText()) ||
				   numberField1.getText().equals("") ||
				   numberField2.getText().equals("")
				){
					number1 = -1;
					number2 = -1;
				}
				else{
					number1 = Integer.parseInt(numberField1.getText());
					number2 = Integer.parseInt(numberField2.getText());
				}
				
				if(itemInsCovgField.getText().equals("0")||itemInsCovgField.getText().equals("1")){
					itemInsCovg = Integer.parseInt(itemInsCovgField.getText());
				}
				else{
					itemInsCovg = -1;
				}
				
				if(itemPresField.getText().equals("0")||itemPresField.getText().equals("1")){
					itemPres = Integer.parseInt(itemPresField.getText());
				}
				else{
					itemPres = -1;
				}
				
				searchByAll(index1,index2,
						icfs1,icfs2,
						ictc1,ictc2,
						itemDescField.getText(),
						itemInsCovg,itemPres,
						number1,number2,
						true);
			}
		});
		allSearchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		allSearchButton.setBounds(126, 581, 138, 23);
		searchInvPanel.add(allSearchButton);
		frame.setVisible(true);
	}
	
	/**
	 * Query search using index constraints (index1>= searched indices <=index2).
	 * @param index1 Lower index bound
	 * @param index2 Higher index bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByIndex(int index1,int index2,boolean makeTable){
		String query = "SELECT * FROM INVENTORY WHERE (ITEM_NUM >="+index1+" && ITEM_NUM<="+index2+")";
		search(query, makeTable);
	}

	/**
	 * Query search items costs from supplier with cost constraints (icfs1>= searched costs <=icfs2).
	 * @param icfs1 Lower cost bound
	 * @param icfs2 Higher cost bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByItemCostFromSupplier(float icfs1,float icfs2,boolean makeTable){
		String query = "SELECT * FROM INVENTORY WHERE (ITEM_CST_FROM_SUP >="+icfs1+" && ITEM_CST_FROM_SUP<="+icfs2+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search items costs to customer with cost constraints (ictc1>= searched costs <=ictc2).
	 * @param ictc1 Lower cost bound
	 * @param ictc2 Higher cost bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByItemCostToCustomer(float ictc1,float ictc2,boolean makeTable){
		String query = "SELECT * FROM INVENTORY WHERE (ITEM_CST_TO_CUST >="+ictc1+" && ITEM_CST_TO_CUST<="+ictc2+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which titles in the Employee table contain the substring title.
	 * @param itemDesc Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByItemDescription(String itemDesc,boolean makeTable){
		String query = "SELECT * FROM INVENTORY WHERE (ITEM_DESC LIKE '%"+itemDesc+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search item insurance coverages.
	 * @param itemInsCovg 1 = Yes, 0 = No.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByItemInsuranceCoverage(int itemInsCovg,boolean makeTable){
		String query = "SELECT * FROM INVENTORY WHERE (ITEM_INS_COVG ="+itemInsCovg+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search item prescriptions.
	 * @param itemPres 1 = Yes, 0 = No.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByItemPrescription(int itemPres,boolean makeTable){
		String query = "SELECT * FROM INVENTORY WHERE (ITEM_PRESCRIPTION ="+itemPres+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search items using constraints (number1>= searched suppliers <=index2).
	 * @param number1 Lower number bound
	 * @param number2 Higher number bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchBySupplierNumber(int number1,int number2,boolean makeTable){
		String query = "SELECT * FROM INVENTORY WHERE (SUP_NUM >="+number1+" && SUP_NUM<="+number2+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find items in inventory given various constraints.	 
	 * @param index1 Lower index bound.
	 * @param index2 Higher index bound.
	 * @param icfs1 Lower cost bound.
	 * @param icfs2 Higher cost bound.
	 * @param ictc1 Lower cost bound.
	 * @param ictc2 Higher cost bound.
	 * @param itemDesc Substring to check.
	 * @param itemInsCovg 1 = Yes, 0 = No.
	 * @param itemPres 1 = Yes, 0 = No.
	 * @param number1 Lower number bound.
	 * @param number2 Higher number bound.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByAll(int index1,int index2,float icfs1,float icfs2,float ictc1,float ictc2,String itemDesc,int itemInsCovg,int itemPres,int number1,int number2,boolean makeTable){
		String query = "SELECT * FROM INVENTORY";
		
		if(!(index1 == -1 || index2 == -1)){
			query += " WHERE (ITEM_NUM >="+index1+" && ITEM_NUM<="+index2+")";
		}
		
		if(!(icfs1 == -1 || icfs2 == -1)){
			query = "SELECT * FROM ("+query+") AS A WHERE (ITEM_CST_FROM_SUP >="+icfs1+" && ITEM_CST_FROM_SUP<="+icfs2+")";
		}
		
		if(!(ictc1 == -1 || ictc2 == -1)){
			query = "SELECT * FROM ("+query+") AS A WHERE (ITEM_CST_TO_CUST >="+ictc1+" && ITEM_CST_TO_CUST<="+ictc2+")";
		}
		
		query = "SELECT * FROM ("+query+") AS A WHERE (ITEM_DESC LIKE '%"+itemDesc+"%')";
		
		if(!(itemInsCovg==-1)){
			query = "SELECT * FROM ("+query+") AS A WHERE (ITEM_INS_COVG ="+itemInsCovg+")";
		}
		
		if(!(itemPres==-1)){
			query = "SELECT * FROM ("+query+") AS A WHERE (ITEM_PRESCRIPTION ="+itemPres+")";
		}
		
		if(!(number1 == -1 || number2 == -1)){
			query = "SELECT * FROM ("+query+") AS A WHERE (SUP_NUM >="+number1+" && SUP_NUM<="+number2+")";
		}
		search(query, makeTable);
	}
	
	/**
	 * Search Inventory data with the given query and get data. 
	 * @param query Search with given query and get data.
	 * @param makeTable If true a window will open to show a table with the Inventory number index and relevant information.
	 */
	public void search(String query, boolean makeTable){
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);	
			metaData = resultSet.getMetaData();	
			ArrayList<Object[]> resultSetList = new ArrayList<Object[]>();
			Object[][] resultSetArray = null;
			int numberOfColumns = metaData.getColumnCount();			
			int index =0;
			
			while(resultSet.next()){
				Object[] resultSetIndividual = new Object[numberOfColumns];
				for(int i=1 ; i<=numberOfColumns ; i++){
					resultSetIndividual[i-1] = resultSet.getObject(i);				
				}
				resultSetList.add(resultSetIndividual);
				index++;
			}
			resultSetArray = new Object[index][numberOfColumns];
			for(int i=0;i<index;i++){
				Object[] obj = resultSetList.get(i);
				for(int j =0;j<numberOfColumns;j++){
					resultSetArray[i][j] = obj[j]; 
				}
			}
			if(makeTable){
				new Table(resultSetArray,invColumnNames);
			}
			
		}
		catch(Exception e){System.out.println("err");}
	}
}
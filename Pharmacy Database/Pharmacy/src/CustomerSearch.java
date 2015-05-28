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

import com.toedter.calendar.JDateChooser;

import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class allows to search for customers in the Customer table.
 * @author INDERPREET PABLA
 */
public class CustomerSearch extends Operation{

	private JFrame frame;
	
	private JTextField indexField1;
	private JTextField indexField2;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField drugField;
	private JDateChooser dobField1 = new JDateChooser();
	private JDateChooser dobField2 = new JDateChooser();

	/**
	 * Create the application.
	 */
	public CustomerSearch(String server, String username, String password) {
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
		
		JPanel searchCustPanel = new JPanel();
		scrollPane.setViewportView(searchCustPanel);
		searchCustPanel.setForeground(Color.DARK_GRAY);
		searchCustPanel.setLayout(null);
		searchCustPanel.setPreferredSize(new Dimension(415, 620));
		
		JLabel searchCustLabel = new JLabel("Search Customer");
		searchCustLabel.setBounds(126, 11, 138, 20);
		searchCustLabel.setForeground(Color.RED);
		searchCustLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchCustPanel.add(searchCustLabel);
		
		JPanel numPanelOut = new JPanel();
		numPanelOut.setBounds(4, 35, 376, 67);
		numPanelOut.setForeground(Color.RED);
		numPanelOut.setBackground(Color.LIGHT_GRAY);
		numPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Number Search (CUST_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		searchCustPanel.add(numPanelOut);
		numPanelOut.setLayout(null);
		
		JPanel numPanelIn = new JPanel();
		numPanelIn.setBackground(Color.WHITE);
		numPanelIn.setBounds(6, 16, 364, 43);
		numPanelOut.add(numPanelIn);
		numPanelIn.setLayout(null);
		
		JLabel indexLabel = new JLabel("Customer Number Search (Ex: 1-10)");
		indexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexLabel.setBounds(10, 0, 202, 14);
		numPanelIn.add(indexLabel);
		
		indexField1 = new JTextField();
		indexField1.setBounds(10, 17, 107, 20);
		numPanelIn.add(indexField1);
		indexField1.setColumns(10);
		
		indexField2 = new JTextField();
		indexField2.setColumns(10);
		indexField2.setBounds(139, 17, 107, 20);
		numPanelIn.add(indexField2);
		
		JLabel indexDashLabel = new JLabel("-");
		indexDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		indexDashLabel.setBounds(125, 20, 10, 14);
		numPanelIn.add(indexDashLabel);
		
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
		numPanelIn.add(indexButton);
		
		JPanel namePanelOut = new JPanel();
		namePanelOut.setBounds(4, 113, 376, 67);
		namePanelOut.setLayout(null);
		namePanelOut.setForeground(Color.RED);
		namePanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Name (CUST_NAME)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		namePanelOut.setBackground(Color.LIGHT_GRAY);
		searchCustPanel.add(namePanelOut);
		
		JPanel namePanelIn = new JPanel();
		namePanelIn.setLayout(null);
		namePanelIn.setBackground(Color.WHITE);
		namePanelIn.setBounds(6, 16, 364, 43);
		namePanelOut.add(namePanelIn);
		
		JLabel nameLabel = new JLabel("Customer Name Sub-String (Ex: \"Doe\")");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameLabel.setBounds(10, 0, 219, 14);
		namePanelIn.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(10, 17, 245, 20);
		namePanelIn.add(nameField);
		
		JButton nameButton = new JButton("Search");
		nameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchByName(nameField.getText(),true);
			}
		});
		nameButton.setBounds(265, 16, 89, 23);
		namePanelIn.add(nameButton);
		
		JPanel phonePanelOut = new JPanel();
		phonePanelOut.setBounds(4, 191, 376, 67);
		phonePanelOut.setLayout(null);
		phonePanelOut.setForeground(Color.RED);
		phonePanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Phone Number (CUST_PHN_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		phonePanelOut.setBackground(Color.LIGHT_GRAY);
		searchCustPanel.add(phonePanelOut);
		
		JPanel phonePanelIn = new JPanel();
		phonePanelIn.setLayout(null);
		phonePanelIn.setBackground(Color.WHITE);
		phonePanelIn.setBounds(6, 16, 364, 43);
		phonePanelOut.add(phonePanelIn);
		
		JLabel phoneLabel = new JLabel("Customer Phone Number Sub-String (Ex: \"905\")");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneLabel.setBounds(10, 0, 268, 14);
		phonePanelIn.add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(10, 17, 245, 20);
		phonePanelIn.add(phoneField);
		
		JButton phoneButton = new JButton("Search");
		phoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchByPhone(phoneField.getText(),true);
			}
		});
		phoneButton.setBounds(265, 16, 89, 23);
		phonePanelIn.add(phoneButton);
		
		JPanel emailPanelOut = new JPanel();
		emailPanelOut.setBounds(4, 269, 376, 67);
		emailPanelOut.setLayout(null);
		emailPanelOut.setForeground(Color.RED);
		emailPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Email (CUST_EMAIL)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		emailPanelOut.setBackground(Color.LIGHT_GRAY);
		searchCustPanel.add(emailPanelOut);
		
		JPanel emailPanelIn = new JPanel();
		emailPanelIn.setLayout(null);
		emailPanelIn.setBackground(Color.WHITE);
		emailPanelIn.setBounds(6, 16, 364, 43);
		emailPanelOut.add(emailPanelIn);
		
		JLabel emailLabel = new JLabel("Customer Email Sub-String (Ex: \"@hotmail.ca\")");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		emailLabel.setBounds(10, 0, 268, 14);
		emailPanelIn.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(10, 17, 245, 20);
		emailPanelIn.add(emailField);
		
		JButton emailButton = new JButton("Search");
		emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchByEmail(emailField.getText(),true);
			}
		});
		emailButton.setBounds(265, 16, 89, 23);
		emailPanelIn.add(emailButton);
		
		JPanel dobPanelOut = new JPanel();
		dobPanelOut.setBounds(4, 347, 376, 67);
		dobPanelOut.setLayout(null);
		dobPanelOut.setForeground(Color.RED);
		dobPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Date Of Birth (CUST_DOB)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		dobPanelOut.setBackground(Color.LIGHT_GRAY);
		searchCustPanel.add(dobPanelOut);
		
		JPanel dobPanelIn = new JPanel();
		dobPanelIn.setLayout(null);
		dobPanelIn.setBackground(Color.WHITE);
		dobPanelIn.setBounds(6, 16, 364, 43);
		dobPanelOut.add(dobPanelIn);
		
		JLabel dobLabel = new JLabel("Customer Date Of Birth (Ex: \"Date1-Date2\")");
		dobLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dobLabel.setBounds(10, 0, 268, 14);
		dobPanelIn.add(dobLabel);
		
		JButton dobButton = new JButton("Search");
		dobButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	dobField1.getDate()==null ||
						dobField2.getDate()==null
				)){
					searchByDateOfBirth(dobField1.getDate(),dobField2.getDate(),true);
				}
			}
		});
		dobButton.setBounds(265, 16, 89, 23);
		dobPanelIn.add(dobButton);
		
		JLabel dobDashLabel = new JLabel("-");
		dobDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		dobDashLabel.setBounds(125, 22, 10, 14);
		dobPanelIn.add(dobDashLabel);
		
		
		dobField1.setBounds(10, 17, 105, 20);
		dobPanelIn.add(dobField1);
		
		dobField2.setBounds(145, 17, 105, 20);
		dobPanelIn.add(dobField2);
		
		JPanel addressPanelOut = new JPanel();
		addressPanelOut.setBounds(4, 425, 376, 67);
		addressPanelOut.setLayout(null);
		addressPanelOut.setForeground(Color.RED);
		addressPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Address (CUST_ADRS)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		addressPanelOut.setBackground(Color.LIGHT_GRAY);
		searchCustPanel.add(addressPanelOut);
		
		JPanel addressPanelIn = new JPanel();
		addressPanelIn.setLayout(null);
		addressPanelIn.setBackground(Color.WHITE);
		addressPanelIn.setBounds(6, 16, 364, 43);
		addressPanelOut.add(addressPanelIn);
		
		JLabel addressLabel = new JLabel("Customer Address Sub-String (Ex: \"King St.\")");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 0, 253, 14);
		addressPanelIn.add(addressLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 17, 245, 20);
		addressPanelIn.add(addressField);
		
		JButton addressButton = new JButton("Search");
		addressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchByAddress(addressField.getText(),true);
			}
		});
		addressButton.setBounds(265, 16, 89, 23);
		addressPanelIn.add(addressButton);
		
		JPanel drugPanelOut = new JPanel();
		drugPanelOut.setLayout(null);
		drugPanelOut.setForeground(Color.RED);
		drugPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Approved Drug (CUST_APRVD_DRUG)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		drugPanelOut.setBackground(Color.LIGHT_GRAY);
		drugPanelOut.setBounds(4, 503, 376, 67);
		searchCustPanel.add(drugPanelOut);
		
		JPanel drugPanelIn = new JPanel();
		drugPanelIn.setLayout(null);
		drugPanelIn.setBackground(Color.WHITE);
		drugPanelIn.setBounds(6, 16, 364, 43);
		drugPanelOut.add(drugPanelIn);
		
		JLabel drugLabel = new JLabel("Customer Drug Sub-String (Ex: \"Advil\")");
		drugLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		drugLabel.setBounds(10, 0, 220, 14);
		drugPanelIn.add(drugLabel);
		
		drugField = new JTextField();
		drugField.setColumns(10);
		drugField.setBounds(10, 17, 245, 20);
		drugPanelIn.add(drugField);
		
		JButton drugButton = new JButton("Search");
		drugButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchByDrug(drugField.getText(),true);
			}
		});
		drugButton.setBounds(265, 16, 89, 23);
		drugPanelIn.add(drugButton);
		
		JButton allSearchButton = new JButton("Search With All");
		allSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int index1,index2;
				 
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
				
				searchByAll(index1,
							index2,
							nameField.getText(),
							phoneField.getText(),
							emailField.getText(),
							addressField.getText(),
							drugField.getText(),
							dobField1.getDate(),
							dobField2.getDate(),
							true);
			}
		});
		allSearchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		allSearchButton.setBounds(126, 581, 138, 23);
		searchCustPanel.add(allSearchButton);
		frame.setVisible(true);
	}
	
	/**
	 * Query search using index constraints (index1>= searched indices <=index2).
	 * @param index1 Lower index bound
	 * @param index2 Higher index bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByIndex(int index1,int index2,boolean makeTable){
		String query = "SELECT * FROM CUSTOMER WHERE (CUST_NUM >="+index1+" && CUST_NUM<="+index2+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which names in the Customer table contain the substring name.
	 * @param name Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByName(String name,boolean makeTable){
		String query = "SELECT * FROM CUSTOMER WHERE (CUST_NAME LIKE '%"+name+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which phone numbers in the Customer table contain the substring phone.
	 * @param phone Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByPhone(String phone,boolean makeTable){
		String query = "SELECT * FROM CUSTOMER WHERE (CUST_PHN_NUM LIKE '%"+phone+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which emails in the Customer table contain the substring email.
	 * @param email Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByEmail(String email,boolean makeTable){
		String query = "SELECT * FROM CUSTOMER WHERE (CUST_EMAIL LIKE '%"+email+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which addresses numbers in the Customer table contain the substring address.
	 * @param address Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByAddress(String address,boolean makeTable){
		String query = "SELECT * FROM CUSTOMER WHERE (CUST_ADRS LIKE '%"+address+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which drugs numbers in the Customer table contain the substring drug.
	 * @param drug Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByDrug(String drug,boolean makeTable){
		String query = "SELECT * FROM CUSTOMER WHERE (CUST_APRVD_DRUG LIKE '%"+drug+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find customers birthdays lie within given date range.
	 * @param date1 Date lower bound.
	 * @param date2 Date higher bound.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByDateOfBirth(Date date1,Date date2,boolean makeTable){
		String date1Str = dateToStringFormat(date1);
		String date2Str = dateToStringFormat(date2);
		String query = "SELECT * FROM CUSTOMER WHERE (CUST_DOB BETWEEN \""+date1Str+"\" AND \""+date2Str+"\")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find customers given various constraints. 
	 * @param index1 Lower index bound
	 * @param index2 Higher index bound
	 * @param name Substring to check.
	 * @param phone Substring to check.
	 * @param email Substring to check.
	 * @param address Substring to check.
	 * @param drug Substring to check.
	 * @param date1 Date lower bound.
	 * @param date2 Date higher bound.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByAll(int index1,int index2,String name,String phone,String email,String address,String drug,Date date1,Date date2,boolean makeTable){
		String query = "SELECT * FROM CUSTOMER";
		boolean isDateAvailable;
		String date1Str="",date2Str="";
		
		if(date1==null || date2==null){
			isDateAvailable = false;
		}
		else{
			date1Str = dateToStringFormat(date1);
			date2Str = dateToStringFormat(date2);
			isDateAvailable = true;
		}
		
		if(!(index1 == -1 || index2 == -1)){
			query += " WHERE (CUST_NUM >="+index1+" && CUST_NUM<="+index2+")";
		}
		
		query = "SELECT * FROM ("+query+") AS A WHERE (CUST_NAME LIKE '%"+name+"%')";
		query = "SELECT * FROM ("+query+") AS A WHERE (CUST_PHN_NUM LIKE '%"+phone+"%')";
		query = "SELECT * FROM ("+query+") AS A WHERE (CUST_EMAIL LIKE '%"+email+"%')";
		query = "SELECT * FROM ("+query+") AS A WHERE (CUST_ADRS LIKE '%"+address+"%')";
		query = "SELECT * FROM ("+query+") AS A WHERE (CUST_APRVD_DRUG LIKE '%"+drug+"%')";
		
		if(isDateAvailable==true){
			query = "SELECT * FROM ("+query+") AS A WHERE (CUST_DOB BETWEEN \""+date1Str+"\" AND \""+date2Str+"\")";
			search(query, makeTable);
		}
		else{
			search(query, makeTable);
		}
	}
	
	/**
	 * Search Customer data with the given query and get data. 
	 * @param query Search with given query and get data.
	 * @param makeTable If true a window will open to show a table with the customer number index and relevant information.
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
				new Table(resultSetArray,custColumnNames);
			}
			
		}
		catch(Exception e){System.out.println("err");}
	}
}

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;

import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class allows to search for and employee in the Employee table.
 * @author INDERPREET PABLA
 */
public class EmployeeSearch extends Operation{

	private JFrame frame;
	
	private JTextField indexField1;
	private JTextField indexField2;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField hireWageField1;
	private JTextField hireWageField2;
	private JTextField titleField;
	private JTextField numberField1;
	private JTextField numberField2;
	
	private JDateChooser dobField1 = new JDateChooser();
	private JDateChooser dobField2 = new JDateChooser();
	private JDateChooser hireDateField1 = new JDateChooser();
	private JDateChooser hireDateField2 = new JDateChooser();
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public EmployeeSearch (String server, String username, String password) {
		createConnection(server,username,password,pharmacyName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 430, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 415, 462);
		frame.getContentPane().add(scrollPane);
		
		JPanel searchEmpPanel = new JPanel();
		scrollPane.setViewportView(searchEmpPanel);
		searchEmpPanel.setLayout(null);
		searchEmpPanel.setPreferredSize(new Dimension(415, 775));
		searchEmpPanel.setForeground(Color.DARK_GRAY);
		
		JLabel searchEmpLabel = new JLabel("Search Employee");
		searchEmpLabel.setForeground(Color.BLUE);
		searchEmpLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchEmpLabel.setBounds(126, 11, 139, 20);
		searchEmpPanel.add(searchEmpLabel);
		
		JPanel indexPanelOut = new JPanel();
		indexPanelOut.setLayout(null);
		indexPanelOut.setForeground(Color.RED);
		indexPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Number Search (EMP_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		indexPanelOut.setBackground(Color.LIGHT_GRAY);
		indexPanelOut.setBounds(4, 35, 376, 67);
		searchEmpPanel.add(indexPanelOut);
		
		JPanel indexPanelIn = new JPanel();
		indexPanelIn.setLayout(null);
		indexPanelIn.setBackground(Color.WHITE);
		indexPanelIn.setBounds(6, 16, 364, 43);
		indexPanelOut.add(indexPanelIn);
		
		JLabel indexLabel = new JLabel("Employee Number Search (Ex: 1-10)");
		indexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexLabel.setBounds(10, 0, 202, 14);
		indexPanelIn.add(indexLabel);
		
		indexField1 = new JTextField();
		indexField1.setColumns(10);
		indexField1.setBounds(10, 17, 107, 20);
		indexPanelIn.add(indexField1);
		
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
		
		JPanel namePanelOut = new JPanel();
		namePanelOut.setLayout(null);
		namePanelOut.setForeground(Color.RED);
		namePanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Name (EMP_NAME)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		namePanelOut.setBackground(Color.LIGHT_GRAY);
		namePanelOut.setBounds(4, 113, 376, 67);
		searchEmpPanel.add(namePanelOut);
		
		JPanel namePanelIn = new JPanel();
		namePanelIn.setLayout(null);
		namePanelIn.setBackground(Color.WHITE);
		namePanelIn.setBounds(6, 16, 364, 43);
		namePanelOut.add(namePanelIn);
		
		JLabel nameLabel = new JLabel("Employee Name Sub-String (Ex: \"Doe\")");
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
		phonePanelOut.setLayout(null);
		phonePanelOut.setForeground(Color.RED);
		phonePanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Phone Number (EMP_PHN_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		phonePanelOut.setBackground(Color.LIGHT_GRAY);
		phonePanelOut.setBounds(4, 191, 376, 67);
		searchEmpPanel.add(phonePanelOut);
		
		JPanel phonePanelIn = new JPanel();
		phonePanelIn.setLayout(null);
		phonePanelIn.setBackground(Color.WHITE);
		phonePanelIn.setBounds(6, 16, 364, 43);
		phonePanelOut.add(phonePanelIn);
		
		JLabel phoneLabel = new JLabel("Employee Phone Number Sub-String (Ex: \"905\")");
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
		
		JPanel dobPanelOut = new JPanel();
		dobPanelOut.setLayout(null);
		dobPanelOut.setForeground(Color.RED);
		dobPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Date Of Birth (EMP_DOB)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		dobPanelOut.setBackground(Color.LIGHT_GRAY);
		dobPanelOut.setBounds(4, 269, 376, 67);
		searchEmpPanel.add(dobPanelOut);
		
		JPanel dobPanelIn = new JPanel();
		dobPanelIn.setLayout(null);
		dobPanelIn.setBackground(Color.WHITE);
		dobPanelIn.setBounds(6, 16, 364, 43);
		dobPanelOut.add(dobPanelIn);
		
		JLabel dobLabel = new JLabel("Employee Date Of Birth (Ex: \"Date1-Date2\")");
		dobLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dobLabel.setBounds(10, 0, 268, 14);
		dobPanelIn.add(dobLabel);
		
		JLabel dobDashLabel = new JLabel("-");
		dobDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		dobDashLabel.setBounds(125, 22, 10, 14);
		dobPanelIn.add(dobDashLabel);
		
		dobField1.setBounds(10, 17, 105, 20);
		dobPanelIn.add(dobField1);
			
		dobField2.setBounds(145, 17, 105, 20);
		dobPanelIn.add(dobField2);
		
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
		
		JPanel addressPanelOut = new JPanel();
		addressPanelOut.setLayout(null);
		addressPanelOut.setForeground(Color.RED);
		addressPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Address (EMP_ADRS)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		addressPanelOut.setBackground(Color.LIGHT_GRAY);
		addressPanelOut.setBounds(4, 347, 376, 67);
		searchEmpPanel.add(addressPanelOut);
		
		JPanel addressPanelIn = new JPanel();
		addressPanelIn.setLayout(null);
		addressPanelIn.setBackground(Color.WHITE);
		addressPanelIn.setBounds(6, 16, 364, 43);
		addressPanelOut.add(addressPanelIn);
		
		JLabel addressLabel = new JLabel("Employee Address Sub-String (Ex: \"King St.\")");
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
		
		JPanel hireDatePanelOut = new JPanel();
		hireDatePanelOut.setLayout(null);
		hireDatePanelOut.setForeground(Color.RED);
		hireDatePanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Hire Date (EMP_HIRE_DATE)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		hireDatePanelOut.setBackground(Color.LIGHT_GRAY);
		hireDatePanelOut.setBounds(4, 425, 376, 67);
		searchEmpPanel.add(hireDatePanelOut);
		
		JPanel hireDatePanelIn = new JPanel();
		hireDatePanelIn.setLayout(null);
		hireDatePanelIn.setBackground(Color.WHITE);
		hireDatePanelIn.setBounds(6, 16, 364, 43);
		hireDatePanelOut.add(hireDatePanelIn);
		
		JLabel hireDateLabel = new JLabel("Employee Hire Date (Ex: \"Date1-Date2\")");
		hireDateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hireDateLabel.setBounds(10, 0, 268, 14);
		hireDatePanelIn.add(hireDateLabel);
		
		JLabel hireDateDashLabel = new JLabel("-");
		hireDateDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		hireDateDashLabel.setBounds(125, 22, 10, 14);
		hireDatePanelIn.add(hireDateDashLabel);
		
		hireDateField1.setBounds(10, 17, 105, 20);
		hireDatePanelIn.add(hireDateField1);		
		
		hireDateField2.setBounds(145, 17, 105, 20);
		hireDatePanelIn.add(hireDateField2);
		
		JButton hireDateButton = new JButton("Search");
		hireDateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	hireDateField1.getDate()==null ||
						hireDateField2.getDate()==null
				)){
					searchByDateOfBirth(hireDateField1.getDate(),hireDateField2.getDate(),true);
				}
			}
		});
		hireDateButton.setBounds(265, 16, 89, 23);
		hireDatePanelIn.add(hireDateButton);
		
		JPanel hireWagePanelOut = new JPanel();
		hireWagePanelOut.setLayout(null);
		hireWagePanelOut.setForeground(Color.RED);
		hireWagePanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Hire Wage (EMP_HRLY_WGE)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		hireWagePanelOut.setBackground(Color.LIGHT_GRAY);
		hireWagePanelOut.setBounds(4, 503, 376, 67);
		searchEmpPanel.add(hireWagePanelOut);
		
		JPanel hireWagePanelIn = new JPanel();
		hireWagePanelIn.setLayout(null);
		hireWagePanelIn.setBackground(Color.WHITE);
		hireWagePanelIn.setBounds(6, 16, 364, 43);
		hireWagePanelOut.add(hireWagePanelIn);
		
		JLabel hireWageLabel = new JLabel("Employee Hire Wage (Ex: 45.8-78.0)");
		hireWageLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hireWageLabel.setBounds(10, 0, 202, 14);
		hireWagePanelIn.add(hireWageLabel);
		
		hireWageField1 = new JTextField();
		hireWageField1.setColumns(10);
		hireWageField1.setBounds(10, 17, 107, 20);
		hireWagePanelIn.add(hireWageField1);
		
		hireWageField2 = new JTextField();
		hireWageField2.setColumns(10);
		hireWageField2.setBounds(139, 17, 107, 20);
		hireWagePanelIn.add(hireWageField2);
		
		JLabel hireWageDashLabel = new JLabel("-");
		hireWageDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		hireWageDashLabel.setBounds(125, 20, 10, 14);
		hireWagePanelIn.add(hireWageDashLabel);
		
		JButton hireWageButton = new JButton("Search");
		hireWageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	!isFloat(hireWageField1.getText()) ||
						hireWageField1.getText().equals("") ||
						!isFloat(hireWageField2.getText()) ||
						hireWageField2.getText().equals("")
				)){
					searchByHourlyWage(Float.parseFloat(hireWageField1.getText()),Float.parseFloat(hireWageField2.getText()),true);			
				}
			}
		});
		hireWageButton.setBounds(265, 16, 89, 23);
		hireWagePanelIn.add(hireWageButton);
		
		JPanel titlePanelOut = new JPanel();
		titlePanelOut.setLayout(null);
		titlePanelOut.setForeground(Color.RED);
		titlePanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Title (EMP_TITLE)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		titlePanelOut.setBackground(Color.LIGHT_GRAY);
		titlePanelOut.setBounds(4, 581, 376, 67);
		searchEmpPanel.add(titlePanelOut);
		
		JPanel titlePanelIn = new JPanel();
		titlePanelIn.setLayout(null);
		titlePanelIn.setBackground(Color.WHITE);
		titlePanelIn.setBounds(6, 16, 364, 43);
		titlePanelOut.add(titlePanelIn);
		
		JLabel titleLabel = new JLabel("Employee Title Sub-String (Ex: \"Manager\")");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		titleLabel.setBounds(10, 0, 245, 14);
		titlePanelIn.add(titleLabel);
		
		titleField = new JTextField();
		titleField.setColumns(10);
		titleField.setBounds(10, 17, 245, 20);
		titlePanelIn.add(titleField);
		
		JButton titleButton = new JButton("Search");
		titleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchByTitle(titleField.getText(),true);
			}
		});
		titleButton.setBounds(265, 16, 89, 23);
		titlePanelIn.add(titleButton);
		
		JPanel numberPanelOut = new JPanel();
		numberPanelOut.setLayout(null);
		numberPanelOut.setForeground(Color.RED);
		numberPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Store Number Search (STORE_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		numberPanelOut.setBackground(Color.LIGHT_GRAY);
		numberPanelOut.setBounds(4, 659, 376, 67);
		searchEmpPanel.add(numberPanelOut);
		
		JPanel numberPanelIn = new JPanel();
		numberPanelIn.setLayout(null);
		numberPanelIn.setBackground(Color.WHITE);
		numberPanelIn.setBounds(6, 16, 364, 43);
		numberPanelOut.add(numberPanelIn);
		
		JLabel numberLabel = new JLabel("Employee Store Number Search (Ex: 1-3)");
		numberLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numberLabel.setBounds(10, 0, 236, 14);
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
					searchByStoreNumber(Integer.parseInt(numberField1.getText()),Integer.parseInt(numberField2.getText()),true);			
				}
			}
		});
		numberButton.setBounds(265, 16, 89, 23);
		numberPanelIn.add(numberButton);
		
		JButton allSearchButton = new JButton("Search With All");
		allSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int index1,index2;
				float wage1,wage2;
				int number1,number2;
				
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
				
				if(!isFloat(hireWageField1.getText()) || 
				   !isFloat(hireWageField2.getText()) ||
				   hireWageField1.getText().equals("") ||
				   hireWageField2.getText().equals("")
				){
						wage1 = -1f;
						wage2 = -1f;
				}
				else{
					wage1 = Float.parseFloat(hireWageField1.getText());
					wage2 = Float.parseFloat(hireWageField2.getText());
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
				
				searchByAll(index1,
						index2,
						nameField.getText(),
						phoneField.getText(),
						dobField1.getDate(),
						dobField2.getDate(),
						addressField.getText(),
						hireDateField1.getDate(),
						hireDateField2.getDate(),
						wage1,
						wage2,
						titleField.getText(),
						number1,
						number2,
						true);
				
			}
		});
		allSearchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		allSearchButton.setBounds(126, 737, 138, 23);
		searchEmpPanel.add(allSearchButton);
	}
	
	/**
	 * Query search using index constraints (index1>= searched indices <=index2).
	 * @param index1 Lower index bound
	 * @param index2 Higher index bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByIndex(int index1,int index2,boolean makeTable){
		String query = "SELECT * FROM EMPLOYEE WHERE (EMP_NUM >="+index1+" && EMP_NUM<="+index2+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which names in the Employee table contain the substring name.
	 * @param name Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByName(String name,boolean makeTable){
		String query = "SELECT * FROM EMPLOYEE WHERE (EMP_NAME LIKE '%"+name+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which phone numbers in the Employee table contain the substring phone.
	 * @param phone Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByPhone(String phone,boolean makeTable){
		String query = "SELECT * FROM EMPLOYEE WHERE (EMP_PHN_NUM LIKE '%"+phone+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find employee's birthdays lie within given date range.
	 * @param dob1 Date lower bound.
	 * @param dob2 Date higher bound.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByDateOfBirth(Date dob1,Date dob2,boolean makeTable){
		String date1Str = dateToStringFormat(dob1);
		String date2Str = dateToStringFormat(dob2);
		String query = "SELECT * FROM EMPLOYEE WHERE (EMP_DOB BETWEEN \""+date1Str+"\" AND \""+date2Str+"\")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which addresses numbers in the Employee table contain the substring address.
	 * @param address Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByAddress(String address,boolean makeTable){
		String query = "SELECT * FROM EMPLOYEE WHERE (EMP_ADRS LIKE '%"+address+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find employee's hire date lie within given date range.
	 * @param hireDate1 Date lower bound.
	 * @param hireDate2 Date higher bound.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByHireDate(Date hireDate1,Date hireDate2,boolean makeTable){
		String date1Str = dateToStringFormat(hireDate1);
		String date2Str = dateToStringFormat(hireDate2);
		String query = "SELECT * FROM EMPLOYEE WHERE (EMP_HIRE_DATE BETWEEN \""+date1Str+"\" AND \""+date2Str+"\")";
		search(query, makeTable);
	}
	
	/**
	 * Query search using wage constraints (wage1>= searched wages <=wage2).
	 * @param wage1 Lower wage bound
	 * @param wage2 Higher wage bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByHourlyWage(float wage1,float wage2,boolean makeTable){
		String query = "SELECT * FROM EMPLOYEE WHERE (EMP_HRLY_WGE >="+wage1+" && EMP_HRLY_WGE<="+wage2+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which titles in the Employee table contain the substring title.
	 * @param title Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByTitle(String title,boolean makeTable){
		String query = "SELECT * FROM EMPLOYEE WHERE (EMP_TITLE LIKE '%"+title+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search store number using constraints (number1>= searched store numbers <=index2).
	 * @param number1 Lower number bound
	 * @param number2 Higher number bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByStoreNumber(int number1,int number2,boolean makeTable){
		String query = "SELECT * FROM EMPLOYEE WHERE (STORE_NUM >="+number1+" && STORE_NUM<="+number2+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find employees given various constraints.
	 * @param index1 Lower index bound.
	 * @param index2 Higher index bound.
	 * @param name Substring to check.
	 * @param phone Substring to check.
	 * @param dob1 Lower date of birth bound.
	 * @param dob2 Higher date of birth bound.
	 * @param address Substring to check.
	 * @param hireDate1 Lower hire date bound.
	 * @param hireDate2 Higher hire date bound.
	 * @param wage1 Lower wage bound.
	 * @param wage2 Higher wage bound.
	 * @param title Substring to check.
	 * @param number1 Lower store number bound.
	 * @param number2 Higher store number bound.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByAll(int index1,int index2,
							String name,
							String phone,
							Date dob1,Date dob2,
							String address,
							Date hireDate1,Date hireDate2,
							float wage1,float wage2,
							String title,
							int number1,int number2,
							boolean makeTable){
		
		String date1Str="",date2Str="";
		String query = "SELECT * FROM EMPLOYEE";
		
		if(!(index1 == -1 || index2 == -1)){
			query += " WHERE (CUST_NUM >="+index1+" && CUST_NUM<="+index2+")";
		}
		
		query = "SELECT * FROM ("+query+") AS A WHERE (EMP_NAME LIKE '%"+name+"%')";
		query = "SELECT * FROM ("+query+") AS A WHERE (EMP_PHN_NUM LIKE '%"+phone+"%')";
		query = "SELECT * FROM ("+query+") AS A WHERE (EMP_ADRS LIKE '%"+address+"%')";
		
		if(!(wage1 == -1f || wage2 == -1f)){
			query = "SELECT * FROM ("+query+") AS A WHERE (EMP_HRLY_WGE >="+wage1+" && EMP_HRLY_WGE<="+wage2+")";
		}
		
		query = "SELECT * FROM ("+query+") AS A WHERE (EMP_TITLE LIKE '%"+title+"%')";
		
		if(!(number1 == -1 || number2 == -1)){
			
			query = "SELECT * FROM ("+query+") AS A WHERE (STORE_NUM >="+number1+" && STORE_NUM<="+number2+")";
		}
		
		if(dob1 !=null && dob2!=null){
			date1Str = dateToStringFormat(dob1);
			date2Str = dateToStringFormat(dob2);
			query = "SELECT * FROM ("+query+") AS A WHERE (EMP_DOB BETWEEN \""+date1Str+"\" AND \""+date2Str+"\")";
		}
		
		if(hireDate1 !=null && hireDate2!=null){
			date1Str = dateToStringFormat(hireDate1);
			date2Str = dateToStringFormat(hireDate2);
			query = "SELECT * FROM ("+query+") AS A WHERE (EMP_HIRE_DATE BETWEEN \""+date1Str+"\" AND \""+date2Str+"\")";
		}
		
		search(query, makeTable);
	}
	
	/**
	 * Search Employee data with the given query and get data. 
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
				new Table(resultSetArray,empColumnNames);
			}
			
		}
		catch(Exception e){System.out.println("err");}
	}
}
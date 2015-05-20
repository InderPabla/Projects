import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class allows to update an employee in the Employee table.
 * @author INDERPREET PABLA
 */
public class EmployeeUpdate extends Operation{

	private JFrame frame;
	
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField hireWageField;
	private JTextField titleField;
	private JTextField numberField;
	private JDateChooser dobField = new JDateChooser();
	private JDateChooser hireDateField = new JDateChooser();
	private JTextField indexField;
	private JButton searchButton = new JButton("Search");
	private JButton updateButton = new JButton("Update");
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public EmployeeUpdate(String server, String username, String password) {
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
		
		JLabel updateLabel = new JLabel("Update Employee");
		updateLabel.setForeground(Color.BLUE);
		updateLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		updateLabel.setBounds(125, 11, 146, 20);
		frame.getContentPane().add(updateLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(10, 155, 150, 20);
		frame.getContentPane().add(nameField);
		
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
		phoneLabel.setBounds(10, 186, 92, 14);
		frame.getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(10, 203, 150, 20);
		frame.getContentPane().add(phoneField);
		
		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dobLabel.setBounds(10, 234, 71, 14);
		frame.getContentPane().add(dobLabel);
		
		
		dobField.setBounds(10, 249, 150, 20);
		frame.getContentPane().add(dobField);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 280, 46, 14);
		frame.getContentPane().add(addressLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 297, 150, 20);
		frame.getContentPane().add(addressField);
		
		JLabel hireWageLabel = new JLabel("Hire Wage (per hour)");
		hireWageLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hireWageLabel.setBounds(10, 376, 119, 14);
		frame.getContentPane().add(hireWageLabel);
		
		hireWageField = new JTextField();
		hireWageField.setColumns(10);
		hireWageField.setBounds(10, 393, 150, 20);
		frame.getContentPane().add(hireWageField);
		
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				update(Integer.parseInt(indexField.getText()),
						nameField.getText(), 
						phoneField.getText(),
						dobField.getDate(),
						addressField.getText(), 
						hireDateField.getDate(), 
						Float.parseFloat(hireWageField.getText()),
						titleField.getText(), 
						Integer.parseInt(numberField.getText()),
						true);
				frame.dispose();
			}
		});
		updateButton.setForeground(Color.BLUE);
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateButton.setBounds(170, 428, 89, 23);
		frame.getContentPane().add(updateButton);
		
		JLabel hireDateLabel = new JLabel("Hire Date");
		hireDateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hireDateLabel.setBounds(10, 328, 53, 14);
		frame.getContentPane().add(hireDateLabel);
		
		hireDateField.setBounds(10, 343, 150, 20);
		frame.getContentPane().add(hireDateField);
		
		JLabel titleLabel = new JLabel("Work Title");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		titleLabel.setBounds(224, 138, 58, 14);
		frame.getContentPane().add(titleLabel);
		
		titleField = new JTextField();
		titleField.setColumns(10);
		titleField.setBounds(224, 155, 150, 20);
		frame.getContentPane().add(titleField);
		
		JLabel numberLabel = new JLabel("Store Number");
		numberLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numberLabel.setBounds(224, 186, 79, 14);
		frame.getContentPane().add(numberLabel);
		
		numberField = new JTextField();
		numberField.setColumns(10);
		numberField.setBounds(224, 203, 150, 20);
		frame.getContentPane().add(numberField);
		
		JLabel indexLabel = new JLabel("Employee Number");
		indexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexLabel.setBounds(10, 42, 102, 14);
		frame.getContentPane().add(indexLabel);
		
		indexField = new JTextField();
		indexField.setColumns(10);
		indexField.setBounds(10, 58, 150, 20);
		frame.getContentPane().add(indexField);
		
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	indexField.getText().equals("") || 
						!isInteger(indexField.getText())
				)){
					Object[][] data = getDataWithIndex(Integer.parseInt(indexField.getText()));

					nameField.setText((String) data[0][1]);
					phoneField.setText((String) data[0][2]);
					dobField.setDate(stringToDateFormat(data[0][3].toString()));
					addressField.setText((String) data[0][4]);
					hireDateField.setDate(stringToDateFormat(data[0][5].toString()));
					hireWageField.setText(data[0][6]+"");
					titleField.setText((String) data[0][7]);
					numberField.setText(data[0][8]+"");
					
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
		
		JLabel indexAsteriskLabel = new JLabel("*");
		indexAsteriskLabel.setForeground(Color.RED);
		indexAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexAsteriskLabel.setBounds(115, 42, 7, 14);
		frame.getContentPane().add(indexAsteriskLabel);
	}
	
	/**
	 * Getting all employee data given index.
	 * @param index Index of the employee to get data from.
	 */
	public Object[][] getDataWithIndex(int index){
		Object[][] resultSetArray = null;
		try{
			String query = "SELECT * FROM EMPLOYEE WHERE (EMP_NUM = "+index+")";
			
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
	 * Updating employee data in Employee table.
	 * @param index Index of the employee to update.
	 * @param name Name of employee.
	 * @param phone Phone number of the employee.
	 * @param dob Date of birth of the employee.
	 * @param address Address of the employee.
	 * @param hireDate Hire date of the employee.
	 * @param hireWage Hire wage of the employee.
	 * @param title Work title of the employee.
	 * @param storeNumber Store number employee will work in.
	 * @param makeTable If true a window will open to show a table with the employee number index and relevant information. 
	 */
	public void update(int index, String name, String phone, Date dob, String address, Date hireDate, float hireWage, String title, int storeNumber,boolean makeTable){
		try{
			String updateEmpQuery,insertLogQuery;
			String strDob =  dateToStringFormat(dob);
			String strHireDate =  dateToStringFormat(hireDate);
			
			updateEmpQuery = "UPDATE EMPLOYEE SET "+
					  "EMP_NAME = \""+name+"\","+
					  "EMP_PHN_NUM = \""+phone+"\","+
					  "EMP_DOB = \""+strDob+"\","+
					  "EMP_ADRS = \""+address+"\","+
					  "EMP_HIRE_DATE = \""+strHireDate+"\","+
					  "EMP_HRLY_WGE = "+hireWage+","+
					  "EMP_TITLE = \""+title+"\","+
					  "STORE_NUM = "+storeNumber+" "+
					  "WHERE EMP_NUM = "+index;
			
			statement.executeUpdate(updateEmpQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"UPDATE"+"\","+	
						  	 "\""+"EMPLOYEE"+"\","+
						  	 "CURRENT_TIMESTAMP"+	
							 ")";
			statement.executeUpdate(insertLogQuery);
			statement.close();
			
			if(makeTable){
				new Table(new Object[][] {{index,name,phone,strDob,address,strHireDate,hireWage,title,storeNumber},},empColumnNames);
			}			
		}catch(Exception e){System.out.println("emp err");}
	}
}
import java.awt.Dimension;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class allows to create an employee query insert into the Employee table.
 * @author INDERPREET PABLA
 */
public class EmployeeNew extends Operation{

	private JFrame frame;
	private Dimension dimension = new Dimension(400,400);
	
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField hireWageField;
	private JTextField titleField;
	private JTextField numberField;
	private JDateChooser dobField = new JDateChooser();
	private JDateChooser hireDateField = new JDateChooser();
		
	/**
	 * Constructor for quick insert of information without need for visual display.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @param name Name of employee.
	 * @param phone Phone number of the employee.
	 * @param dob Date of birth of the employee.
	 * @param address Address of the employee.
	 * @param hireDate Hire date of the employee.
	 * @param hireWage Hire wage of the employee.
	 * @param title Work title of the employee.
	 * @param storeNumber Store number employee will work in.
	 */
	public EmployeeNew(String server, String username, String password, String name, String phone, Date dob, String address, Date hireDate, float hireWage, String title, int storeNumber){
		createConnection(server,username,password,pharmacyName);
		insert(name,phone,dob,address,hireDate,hireWage,title,storeNumber,false);
	}
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public EmployeeNew(String server, String username, String password) {
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
		
		JLabel employeeLabel = new JLabel("New Employee");
		employeeLabel.setForeground(Color.BLUE);
		employeeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		employeeLabel.setBounds(136, 11, 126, 20);
		frame.getContentPane().add(employeeLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(10, 55, 150, 20);
		frame.getContentPane().add(nameField);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameLabel.setBounds(10, 38, 32, 14);
		frame.getContentPane().add(nameLabel);
		
		JLabel nameAsteriskLabel = new JLabel("*");
		nameAsteriskLabel.setForeground(Color.RED);
		nameAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameAsteriskLabel.setBounds(44, 38, 18, 14);
		frame.getContentPane().add(nameAsteriskLabel);
		
		JLabel legendAsteriskLabel = new JLabel("*");
		legendAsteriskLabel.setForeground(Color.RED);
		legendAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendAsteriskLabel.setBounds(10, 337, 7, 14);
		frame.getContentPane().add(legendAsteriskLabel);
		
		JLabel legendLabel = new JLabel("Must be filled");
		legendLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		legendLabel.setBounds(20, 337, 140, 14);
		frame.getContentPane().add(legendLabel);
		
		JLabel phoneLabel = new JLabel("Phone Number");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneLabel.setBounds(10, 86, 92, 14);
		frame.getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(10, 103, 150, 20);
		frame.getContentPane().add(phoneField);
		
		JLabel phoneAsteriskLabel = new JLabel("*");
		phoneAsteriskLabel.setForeground(Color.RED);
		phoneAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		phoneAsteriskLabel.setBounds(95, 86, 18, 14);
		frame.getContentPane().add(phoneAsteriskLabel);
		
		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dobLabel.setBounds(10, 134, 71, 14);
		frame.getContentPane().add(dobLabel);
		
		
		dobField.setBounds(10, 149, 150, 20);
		frame.getContentPane().add(dobField);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressLabel.setBounds(10, 180, 46, 14);
		frame.getContentPane().add(addressLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(10, 197, 150, 20);
		frame.getContentPane().add(addressField);
		
		JLabel addressAsteriskLabel = new JLabel("*");
		addressAsteriskLabel.setForeground(Color.RED);
		addressAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addressAsteriskLabel.setBounds(59, 180, 18, 14);
		frame.getContentPane().add(addressAsteriskLabel);
		
		JLabel dobAsteriskLabel = new JLabel("*");
		dobAsteriskLabel.setForeground(Color.RED);
		dobAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dobAsteriskLabel.setBounds(84, 134, 18, 14);
		frame.getContentPane().add(dobAsteriskLabel);
		
		JLabel hireWageLabel = new JLabel("Hire Wage (per hour)");
		hireWageLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hireWageLabel.setBounds(10, 276, 119, 14);
		frame.getContentPane().add(hireWageLabel);
		
		hireWageField = new JTextField();
		hireWageField.setColumns(10);
		hireWageField.setBounds(10, 293, 150, 20);
		frame.getContentPane().add(hireWageField);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	nameField.getText().equals("") || 
						phoneField.getText().equals("") || 
						dobField.getDate()==null  || 
						addressField.getText().equals("") ||
						hireDateField.getDate()==null ||
						!isFloat(hireWageField.getText()) ||
						hireWageField.getText().equals("") ||
						titleField.getText().equals("") ||
						!isInteger(numberField.getText()) ||
						numberField.getText().equals("")	
				)){
					insert(	nameField.getText(), 
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
			}
		});
		createButton.setForeground(Color.BLUE);
		createButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		createButton.setBounds(170, 328, 89, 23);
		frame.getContentPane().add(createButton);
		
		JLabel hireDateLabel = new JLabel("Hire Date");
		hireDateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hireDateLabel.setBounds(10, 228, 53, 14);
		frame.getContentPane().add(hireDateLabel);
		
		JLabel hireDateAsteriskLabel = new JLabel("*");
		hireDateAsteriskLabel.setForeground(Color.RED);
		hireDateAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hireDateAsteriskLabel.setBounds(63, 228, 18, 14);
		frame.getContentPane().add(hireDateAsteriskLabel);
		
		hireDateField.setBounds(10, 243, 150, 20);
		frame.getContentPane().add(hireDateField);
		
		JLabel hireWageAsteriskLabel = new JLabel("*");
		hireWageAsteriskLabel.setForeground(Color.RED);
		hireWageAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hireWageAsteriskLabel.setBounds(136, 276, 18, 14);
		frame.getContentPane().add(hireWageAsteriskLabel);
		
		JLabel titleLabel = new JLabel("Work Title");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		titleLabel.setBounds(224, 38, 58, 14);
		frame.getContentPane().add(titleLabel);
		
		JLabel titleAsteriskLabel = new JLabel("*");
		titleAsteriskLabel.setForeground(Color.RED);
		titleAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		titleAsteriskLabel.setBounds(285, 38, 18, 14);
		frame.getContentPane().add(titleAsteriskLabel);
		
		titleField = new JTextField();
		titleField.setColumns(10);
		titleField.setBounds(224, 55, 150, 20);
		frame.getContentPane().add(titleField);
		
		JLabel numberLabel = new JLabel("Store Number");
		numberLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numberLabel.setBounds(224, 86, 79, 14);
		frame.getContentPane().add(numberLabel);
		
		JLabel numberAsteriskLabel = new JLabel("*");
		numberAsteriskLabel.setForeground(Color.RED);
		numberAsteriskLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numberAsteriskLabel.setBounds(306, 86, 18, 14);
		frame.getContentPane().add(numberAsteriskLabel);
		
		numberField = new JTextField();
		numberField.setColumns(10);
		numberField.setBounds(224, 103, 150, 20);
		frame.getContentPane().add(numberField);
	}
	
	/**
	 * Inserting employee data to Employee table in Pharmacy database.
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
	public void insert(String name, String phone, Date dob, String address, Date hireDate, float hireWage, String title, int storeNumber,boolean makeTable){
		try{
			String insertCustQuery,insertLogQuery;
			String strDob =  dateToStringFormat(dob);
			String strHireDate =  dateToStringFormat(hireDate);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(maxEmpIndexQuery);	
			resultSet.next();
			int index= 0;
			if(resultSet.getObject(1)!=null){
				index = (int) resultSet.getObject(1);
			}
			index++;
			insertCustQuery = "INSERT INTO EMPLOYEE(EMP_NUM,EMP_NAME,EMP_PHN_NUM,EMP_DOB,EMP_ADRS,EMP_HIRE_DATE,EMP_HRLY_WGE,EMP_TITLE,STORE_NUM)"+
							  "VALUES ("+
							  index+","+
							  "\""+name+"\","+
							  "\""+phone+"\","+
							  "\""+strDob+"\","+
							  "\""+address+"\","+
							  "\""+strHireDate+"\","+
							  hireWage+","+
							  "\""+title+"\","+
							  storeNumber+
							  ")";
			statement.executeUpdate(insertCustQuery);
			
			insertLogQuery = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON)"+ 
						  	 "VALUES ("+
						  	 "\""+"INSERT"+"\","+	
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
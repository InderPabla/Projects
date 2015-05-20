import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import javax.swing.JPanel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JLabel;

/**
 * Class for organizing multiple database action that user can pick from. 
 * @author INDERPREET PABLA
 */
public class PharmacyAction extends Operation{

	private JFrame frame;
	private JPanel customerPanel = new JPanel();
	private JPanel employeePanel = new JPanel();
	private JPanel inventoryPanel = new JPanel();
	private JPanel pharmacyPanel = new JPanel();
	private JPanel supplierPanel = new JPanel();
	private JLabel databaseLabel = new JLabel("Pharmacy Database: ");
	private JLabel databaseStatusLabel = new JLabel("Exists");

	String server,username,password;

	String createDatabaseQuery = "CREATE DATABASE PHARMACY";
	
	private int customerColumnSize = 7;
	private int employeeColumnSize = 9;
	private int inventoryColumnSize = 7;
	private int pharmacyColumnSize = 4;
	private int supplierColumnSize = 4;
	//private int logColumnSize = 4;
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public PharmacyAction(String server, String username, String password) {
		createConnection(server,username,password,"");
		this.server=server;
		this.username=username;
		this.password=password;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 541, 310);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton customerButton = new JButton("<html>Customer<br />Operation</html>");
		customerButton.setBounds(10, 11, 93, 41);
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				customerPanel.setVisible(!customerPanel.isVisible());
			}
		});
		frame.getContentPane().setLayout(null);
		customerButton.setBackground(Color.RED);
		customerButton.setForeground(Color.RED);
		customerButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(customerButton);
		
		JButton employeeButton = new JButton("<html>Employee<br />Operation</html>");
		employeeButton.setBounds(113, 11, 93, 41);
		employeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				employeePanel.setVisible(!employeePanel.isVisible());
			}
		});
		employeeButton.setBackground(Color.BLUE);
		employeeButton.setForeground(Color.BLUE);
		employeeButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(employeeButton);
		
		JButton inventoryButton = new JButton("<html>Inventory<br />Operation</html>");
		inventoryButton.setBounds(216, 11, 93, 41);
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				inventoryPanel.setVisible(!inventoryPanel.isVisible());
			}
		});
		inventoryButton.setBackground(Color.BLACK);
		inventoryButton.setForeground(Color.BLACK);
		inventoryButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(inventoryButton);
		
		JButton pharmacyButton = new JButton("<html>Pharmacy<br />Operation</html>");
		pharmacyButton.setBounds(319, 11, 93, 41);
		pharmacyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				pharmacyPanel.setVisible(!pharmacyPanel.isVisible());
			}
		});
		pharmacyButton.setBackground(new Color(255, 153, 0));
		pharmacyButton.setForeground(new Color(255, 153, 0));
		pharmacyButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(pharmacyButton);
		
		JButton supplierButton = new JButton("<html>Supplier<br />Operation</html>");
		supplierButton.setBounds(420, 11, 93, 41);
		supplierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				supplierPanel.setVisible(!supplierPanel.isVisible());
			}
		});
		supplierButton.setBackground(new Color(0, 153, 51));
		supplierButton.setForeground(new Color(0, 153, 0));
		supplierButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(supplierButton);
		customerPanel.setBounds(10, 63, 93, 148);
		frame.getContentPane().add(customerPanel);
		customerPanel.setLayout(null);
		customerPanel.setVisible(false);
		
		JButton newCustButton = new JButton("<html>New<br />Customer</html>");
		newCustButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		newCustButton.setForeground(new Color(255, 0, 0));
		newCustButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new CustomerNew(server,username,password);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});	
			}
		});
		newCustButton.setBounds(0, 0, 93, 42);
		customerPanel.add(newCustButton);
		
		JButton updateCustButton = new JButton("<html>Update<br />Customer</html>");
		updateCustButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new CustomerUpdate(server,username,password);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});	
			}
		});
		updateCustButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updateCustButton.setForeground(new Color(255, 0, 0));
		updateCustButton.setBounds(0, 53, 93, 42);
		customerPanel.add(updateCustButton);
		
		JButton searchCustButton = new JButton("<html>Search</html>");
		searchCustButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchCustButton.setForeground(new Color(255, 0, 0));
		searchCustButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new CustomerSearch(server,username,password);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});	
			}
		});
		searchCustButton.setBounds(0, 106, 93, 42);
		customerPanel.add(searchCustButton);
		employeePanel.setBounds(113, 63, 93, 148);
		frame.getContentPane().add(employeePanel);
		employeePanel.setLayout(null);
		employeePanel.setVisible(false);
		
		JButton newEmpButton = new JButton("<html>New<br />Employee</html>");
		newEmpButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		newEmpButton.setForeground(new Color(0, 0, 255));
		newEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new EmployeeNew(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		newEmpButton.setBounds(0, 0, 93, 42);
		employeePanel.add(newEmpButton);
		
		JButton updateEmpButton = new JButton("<html>Update<br />Employee</html>");
		updateEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new EmployeeUpdate(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		updateEmpButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updateEmpButton.setForeground(new Color(0, 0, 255));
		updateEmpButton.setBounds(0, 53, 93, 42);
		employeePanel.add(updateEmpButton);
		
		JButton searchEmpButton = new JButton("<html>Search</html>");
		searchEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new EmployeeSearch(server,username,password);
						} catch (Exception e) {System.out.println("err");}
					}
				});
			}
		});
		searchEmpButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchEmpButton.setForeground(new Color(0, 0, 255));
		searchEmpButton.setBounds(0, 106, 93, 42);
		employeePanel.add(searchEmpButton);
		inventoryPanel.setBounds(216, 63, 93, 148);
		frame.getContentPane().add(inventoryPanel);
		inventoryPanel.setLayout(null);
		inventoryPanel.setVisible(false);
		
		JButton newInvButton = new JButton("<html>New<br />Inventory</html>");
		newInvButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		newInvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new InventoryNew(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		newInvButton.setBounds(0, 0, 93, 42);
		inventoryPanel.add(newInvButton);
		
		JButton updateInvButton = new JButton("<html>Update<br />Inventory</html>");
		updateInvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new InventoryUpdate(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		updateInvButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updateInvButton.setBounds(0, 53, 93, 42);
		inventoryPanel.add(updateInvButton);
		
		JButton searchInvButton = new JButton("<html>Search</html>");
		searchInvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new InventorySearch(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		searchInvButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchInvButton.setBounds(0, 106, 93, 42);
		inventoryPanel.add(searchInvButton);
		pharmacyPanel.setBounds(319, 63, 93, 148);
		frame.getContentPane().add(pharmacyPanel);
		pharmacyPanel.setLayout(null);
		pharmacyPanel.setVisible(false);
		
		JButton newPharButton = new JButton("<html>New<br />Pharmacy</html>");
		newPharButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		newPharButton.setForeground(new Color(255, 153, 0));
		newPharButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new PharmacyNew(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		newPharButton.setBounds(0, 0, 93, 42);
		pharmacyPanel.add(newPharButton);
		
		JButton updatePharButton = new JButton("<html>Update<br />Pharmacy</html>");
		updatePharButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new PharmacyUpdate(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		updatePharButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updatePharButton.setForeground(new Color(255, 153, 0));
		updatePharButton.setBounds(0, 53, 93, 42);
		pharmacyPanel.add(updatePharButton);
		
		JButton searchPharButton = new JButton("<html>Search</html>");
		searchPharButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new PharmacySearch(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		searchPharButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchPharButton.setForeground(new Color(255, 153, 0));
		searchPharButton.setBounds(0, 106, 93, 42);
		pharmacyPanel.add(searchPharButton);
		supplierPanel.setBounds(420, 63, 93, 148);
		frame.getContentPane().add(supplierPanel);
		supplierPanel.setLayout(null);
		supplierPanel.setVisible(false);
		
		JButton newSupButton = new JButton("<html>New<br />Supplier</html>");
		newSupButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		newSupButton.setForeground(new Color(0, 128, 0));
		newSupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new SupplierNew(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		newSupButton.setBounds(0, 0, 93, 42);
		supplierPanel.add(newSupButton);
		
		JButton updateSupButton = new JButton("<html>Update<br />Supplier</html>");
		updateSupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new SupplierUpdate(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		updateSupButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updateSupButton.setForeground(new Color(0, 128, 0));
		updateSupButton.setBounds(0, 53, 93, 42);
		supplierPanel.add(updateSupButton);
		
		JButton searchSupButton = new JButton("<html>Search</html>");
		searchSupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new SupplierSearch(server,username,password);
						} catch (Exception e) {}
					}
				});
			}
		});
		searchSupButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchSupButton.setForeground(new Color(0, 128, 0));
		searchSupButton.setBounds(0, 106, 93, 42);
		supplierPanel.add(searchSupButton);
		databaseLabel.setBounds(10, 247, 103, 14);
		frame.getContentPane().add(databaseLabel);
		databaseStatusLabel.setBounds(114, 247, 155, 14);
		
		
		databaseStatusLabel.setForeground(new Color(0, 100, 0));
		frame.getContentPane().add(databaseStatusLabel);
		
		JButton selfQueryButton = new JButton("Do Your Own SQL Query");
		selfQueryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new SelfQuery(server,username,password);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		selfQueryButton.setBounds(358, 238, 155, 23);
		frame.getContentPane().add(selfQueryButton);
		
		JButton sampleButton = new JButton("Sample Populate");
		sampleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you wish to create a sample populate.\n"
																	  + "This will create a sample entiers into the database \n"
																	  + "and allow you to see if the database is working.","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					samplePopulate();
				}
			}
		});
		sampleButton.setBounds(193, 238, 155, 23);
		frame.getContentPane().add(sampleButton);
		
		JButton searchLogButton = new JButton("Log Search");
		searchLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new LogSearch(server,username,password);
						} catch (Exception error) {}
					}
				});
			}
		});
		searchLogButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchLogButton.setForeground(new Color(255, 51, 255));
		searchLogButton.setBounds(10, 222, 103, 23);
		frame.getContentPane().add(searchLogButton);
		frame.setVisible(true);
		
		pharmacyDatabaseCheck();
	}

	
	/**
	 * Check if pharmacy database exists. If not, then create the database.
	 */
	public void pharmacyDatabaseCheck(){
		boolean exists = false;
		try {
			resultSet = connection.getMetaData().getCatalogs();
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
	            if(databaseName.equals(pharmacyName)){
	            	exists = true;
	            	break;
	            }
		    }
		    resultSet.close();
		} catch (Exception e) {e.printStackTrace();}
		
		if(!exists){
			
			databaseStatusLabel.setText("Just created");
			try {
				Scanner scan = new Scanner(new FileReader("tables.txt"));
				statement = connection.createStatement();
				statement.executeUpdate(createDatabaseQuery);
				createConnection(server,username,password,pharmacyName);
				while(scan.hasNext()){
					statement = connection.createStatement();
					statement.executeUpdate(scan.nextLine());
					
				}
				scan.close();
				statement.close();
			}
			catch(Exception e){
				databaseStatusLabel.setText("Error in creating database");
				databaseStatusLabel.setForeground(Color.red);
			}
		}
	}
	
	/**
	 * Sample populate the database to check if everything works. 
	 */
	public void samplePopulate(){
		Object[][] custSampleData = 
		{
			{"Caroline Baker","9051111111","cbaker@gmail.com","1998/12/04","1234 Crazy Ave","0001"},
			{"Charles Edmunds","4169872031","cedmunds@hotmail.com","1990/10/26","5125 Tech Rd","0023"},
			{"David Anderson","9058763145","danderson@gmail.com","1995/01/08","10 Wonderland St","0004,0005,0009"},
			{"Natalie Greene","9873524584","ngreene@outlook.com","1942/03/12","365 Moon Cres","0001,0003,0005,0023"},
			{"Fiona Bond","6476874854","fbond@rogers.com","1987/05/13","8747 Street Rd","0002,0003,0005,0006,0333"},
		};
		
		Object[][] empSampleData = 
		{
			{"Eric Reid","1234567890","1990/06/08","123 Best Rd","2003/04/04",25.96f,"Manager",0001},
			{"John Russell","5478548961","1986/11/08","5640 Hoodington Rd","2005/07/03",10.50f,"Cashier",0002},
			{"Anthony Payne","3632145117","1995/12/15","654 Billybob Ave","2009/08/13",15.85f,"Sales Associate",0003},
			{"Amy Mackay","2546978546","2001/01/01","9824 Jimmy St","2011/09/10",20.36f,"Supervisor",0002},
			{"Tracey Fisher","9876543210","1956/02/12","30 Saturn Cres","1999/03/09",10.50f,"Cashier",0001},
			{"Inder Pabla","9876543210","1901/02/12","30 Test Cres","1922/03/09",10.50f,"Janitor",0001},
		};
		
		Object[][] invSampleData = 
		{
			{1.50f,5.96f,"Advil",1,0,0001},
			{1.76f,7.80f,"Tylenol",1,1,0002},
			{3.47f,4.98f,"Motrin",0,0,0003},
			{2.34f,6.93f,"Pepto-Bismol",0,1,0004},
			{5.64f,10.35f, "Buckleys",1,0,0005},
		};
		
		Object[][] pharSampleData = 
		{
			{"123 Medical Rd","9am-5pm","9876543210"},
			{"321 Pharmacy Dr","9am-6pm","1587946352"},
	        {"879 Doctor Ave","10am-1pm","0213525748"},
	        {"1536 West Rd","7am-9pm","3602514898"},
			{"6351 East St","11am-7pm","7458961320"},
		};
		
		Object[][] supSampleData = 
		{
			{"123 Supplier St","Total Health","123654897"},
		    {"85 Road St","Lighthouse","9876521420"},
		    {"96 Fitness Ave","Medical Supplier","3642587954"},
		    {"110 Health Rd","Health Solutions","1239865741"},
		    {"12345 Candy Cres","Pfizer","3625147896"},
		};
		
		Object[] newCustColumnNames = new Object[customerColumnSize-1];
		for(int i =1;i<customerColumnSize;i++){
			newCustColumnNames[i-1] = custColumnNames[i];
		}
		
		Object[] newEmpColumnNames = new Object[employeeColumnSize-1];
		for(int i =1;i<employeeColumnSize;i++){
			newEmpColumnNames[i-1] = empColumnNames[i];
		}
		
		Object[] newInvColumnNames = new Object[inventoryColumnSize-1];
		for(int i =1;i<inventoryColumnSize;i++){
			newInvColumnNames[i-1] = invColumnNames[i];
		}
		
		Object[] newPharColumnNames = new Object[pharmacyColumnSize-1];
		for(int i =1;i<pharmacyColumnSize;i++){
			newPharColumnNames[i-1] = pharColumnNames[i];
		}
		
		Object[] newSupColumnNames = new Object[supplierColumnSize-1];
		for(int i =1;i<supplierColumnSize;i++){
			newSupColumnNames[i-1] = supColumnNames[i];
		}
		
		for(int i  = 0;i<custSampleData.length;i++){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date dob = formatter.parse((String) custSampleData[i][3]);
				new CustomerNew(server,username,password,
						(String)custSampleData[i][0],
						(String)custSampleData[i][1],
						(String)custSampleData[i][2],
						dob,
						(String)custSampleData[i][4],
						(String)custSampleData[i][5]);
			} catch (ParseException e) {}
		}
		
		for(int i  = 0;i<empSampleData.length;i++){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date dob = formatter.parse((String) empSampleData[i][2]);
				Date hireDate = formatter.parse((String) empSampleData[i][4]);	
				new EmployeeNew(server,username,password,
						(String)empSampleData[i][0],
						(String)empSampleData[i][1],
						dob,
						(String)empSampleData[i][3],
						hireDate,
						(float)empSampleData[i][5],
						(String)empSampleData[i][6],
						(int)empSampleData[i][7]);
			} catch (ParseException e) {}	
		}
		
		for(int i  = 0;i<invSampleData.length;i++){
			new InventoryNew(server,username,password,
					(float)invSampleData[i][0],
					(float)invSampleData[i][1],
					(String)invSampleData[i][2],
					(int)invSampleData[i][3],
					(int)invSampleData[i][4],
					(int)invSampleData[i][5]);	
		}

		for(int i  = 0;i<pharSampleData.length;i++){
			new PharmacyNew(server,username,password,
					(String)pharSampleData[i][0],
					(String)pharSampleData[i][1],
					(String)pharSampleData[i][2]);	
		}
		
		for(int i  = 0;i<supSampleData.length;i++){
			new SupplierNew(server,username,password,
					(String)supSampleData[i][0],
					(String)supSampleData[i][1],
					(String)supSampleData[i][2]);	
		}
		
		new Table(custSampleData,newCustColumnNames);
		new Table(empSampleData,newEmpColumnNames);
		new Table(invSampleData,newInvColumnNames);
		new Table(pharSampleData,newPharColumnNames);
		new Table(supSampleData,newSupColumnNames);
	}
}
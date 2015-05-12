import javax.swing.JFrame;
import java.sql.Connection;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * Class allows to search for customers in the Customer table.
 * @author INDERPREET PABLA
 */
public class CustomerSearch extends Operation{

	private JFrame frame;
	Connection connection;	
	
	String pharmacyName = "pharmacy";
	String maxIndexQuery = "SELECT MAX(CUST_NUM) AS max FROM customer";
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;


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
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel searchCustPanel = new JPanel();
		searchCustPanel.setForeground(Color.DARK_GRAY);
		searchCustPanel.setBounds(0, 0, 384, 462);
		frame.getContentPane().add(searchCustPanel);
		searchCustPanel.setLayout(null);
		
		JLabel searchCustLabel = new JLabel("Search Customer");
		searchCustLabel.setForeground(Color.RED);
		searchCustLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchCustLabel.setBounds(126, 11, 138, 20);
		searchCustPanel.add(searchCustLabel);
		
		JPanel numPanelOut = new JPanel();
		numPanelOut.setForeground(Color.RED);
		numPanelOut.setBackground(Color.LIGHT_GRAY);
		numPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Numer Search (CUST_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		numPanelOut.setBounds(4, 35, 376, 85);
		searchCustPanel.add(numPanelOut);
		numPanelOut.setLayout(null);
		
		JPanel numPanelIn = new JPanel();
		numPanelIn.setBackground(Color.WHITE);
		numPanelIn.setBounds(6, 16, 364, 62);
		numPanelOut.add(numPanelIn);
		numPanelIn.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Number Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 151, 14);
		numPanelIn.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 28, 107, 20);
		numPanelIn.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(139, 28, 107, 20);
		numPanelIn.add(textField_1);
		
		JLabel label = new JLabel("-");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(125, 31, 10, 14);
		numPanelIn.add(label);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(265, 27, 89, 23);
		numPanelIn.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.RED);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Numer Search (CUST_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(4, 131, 376, 85);
		searchCustPanel.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(6, 16, 364, 62);
		panel.add(panel_1);
		
		JLabel label_1 = new JLabel("Customer Number Search");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 11, 151, 14);
		panel_1.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 28, 107, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(139, 28, 107, 20);
		panel_1.add(textField_3);
		
		JLabel label_2 = new JLabel("-");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(125, 31, 10, 14);
		panel_1.add(label_2);
		
		JButton button = new JButton("Search");
		button.setBounds(265, 27, 89, 23);
		panel_1.add(button);
		frame.setVisible(true);
	}
}

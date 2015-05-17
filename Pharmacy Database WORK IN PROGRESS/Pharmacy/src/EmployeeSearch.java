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

/**
 * Class allows to search for and employee in the Employee table.
 * @author INDERPREET PABLA
 */
public class EmployeeSearch {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the application.
	 */
	public EmployeeSearch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 430, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 415, 463);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(415, 620));
		panel.setForeground(Color.DARK_GRAY);
		
		JLabel label = new JLabel("Search Customer");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(126, 11, 138, 20);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.RED);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Numer Search (CUST_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(4, 35, 376, 67);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(6, 16, 364, 43);
		panel_1.add(panel_2);
		
		JLabel label_1 = new JLabel("Customer Number Search (Ex: 1-10)");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 0, 202, 14);
		panel_2.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 17, 107, 20);
		panel_2.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(139, 17, 107, 20);
		panel_2.add(textField_1);
		
		JLabel label_2 = new JLabel("-");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(125, 20, 10, 14);
		panel_2.add(label_2);
		
		JButton button = new JButton("Search");
		button.setBounds(265, 16, 89, 23);
		panel_2.add(button);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(Color.RED);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Name (CUST_NAME)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(4, 113, 376, 67);
		panel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(6, 16, 364, 43);
		panel_3.add(panel_4);
		
		JLabel label_3 = new JLabel("Customer Name Sub-String (Ex: \"Doe\")");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 0, 219, 14);
		panel_4.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 17, 245, 20);
		panel_4.add(textField_2);
		
		JButton button_1 = new JButton("Search");
		button_1.setBounds(265, 16, 89, 23);
		panel_4.add(button_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setForeground(Color.RED);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Phone Number (CUST_PHN_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(4, 191, 376, 67);
		panel.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(6, 16, 364, 43);
		panel_5.add(panel_6);
		
		JLabel label_4 = new JLabel("Customer Phone Number Sub-String (Ex: \"905\")");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(10, 0, 268, 14);
		panel_6.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 17, 245, 20);
		panel_6.add(textField_3);
		
		JButton button_2 = new JButton("Search");
		button_2.setBounds(265, 16, 89, 23);
		panel_6.add(button_2);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setForeground(Color.RED);
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Email (CUST_EMAIL)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setBounds(4, 269, 376, 67);
		panel.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(6, 16, 364, 43);
		panel_7.add(panel_8);
		
		JLabel label_5 = new JLabel("Customer Email Sub-String (Ex: \"@hotmail.ca\")");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(10, 0, 268, 14);
		panel_8.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 17, 245, 20);
		panel_8.add(textField_4);
		
		JButton button_3 = new JButton("Search");
		button_3.setBounds(265, 16, 89, 23);
		panel_8.add(button_3);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setForeground(Color.RED);
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Date Of Birth (CUST_DOB)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setBackground(Color.LIGHT_GRAY);
		panel_9.setBounds(4, 347, 376, 67);
		panel.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBackground(Color.WHITE);
		panel_10.setBounds(6, 16, 364, 43);
		panel_9.add(panel_10);
		
		JLabel label_6 = new JLabel("Customer Date Of Birth (Ex: \"Date1-Date2\")");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(10, 0, 268, 14);
		panel_10.add(label_6);
		
		JButton button_4 = new JButton("Search");
		button_4.setBounds(265, 16, 89, 23);
		panel_10.add(button_4);
		
		JLabel label_7 = new JLabel("-");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_7.setBounds(125, 22, 10, 14);
		panel_10.add(label_7);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 17, 105, 20);
		panel_10.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(145, 17, 105, 20);
		panel_10.add(dateChooser_1);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setForeground(Color.RED);
		panel_11.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Address (CUST_ADRS)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_11.setBackground(Color.LIGHT_GRAY);
		panel_11.setBounds(4, 425, 376, 67);
		panel.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBackground(Color.WHITE);
		panel_12.setBounds(6, 16, 364, 43);
		panel_11.add(panel_12);
		
		JLabel label_8 = new JLabel("Customer Address Sub-String (Ex: \"King St.\")");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(10, 0, 253, 14);
		panel_12.add(label_8);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(10, 17, 245, 20);
		panel_12.add(textField_5);
		
		JButton button_5 = new JButton("Search");
		button_5.setBounds(265, 16, 89, 23);
		panel_12.add(button_5);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setForeground(Color.RED);
		panel_13.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Approved Drug (CUST_APRVD_DRUG)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_13.setBackground(Color.LIGHT_GRAY);
		panel_13.setBounds(4, 503, 376, 67);
		panel.add(panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBackground(Color.WHITE);
		panel_14.setBounds(6, 16, 364, 43);
		panel_13.add(panel_14);
		
		JLabel label_9 = new JLabel("Customer Drug Sub-String (Ex: \"Advil\")");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(10, 0, 220, 14);
		panel_14.add(label_9);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(10, 17, 245, 20);
		panel_14.add(textField_6);
		
		JButton button_6 = new JButton("Search");
		button_6.setBounds(265, 16, 89, 23);
		panel_14.add(button_6);
		
		JButton button_7 = new JButton("Search With All");
		button_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_7.setBounds(126, 581, 138, 23);
		panel.add(button_7);
	}
}

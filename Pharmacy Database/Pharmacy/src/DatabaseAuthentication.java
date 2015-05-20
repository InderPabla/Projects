import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Class for login authentication to server. 
 * @author INDERPREET PABLA
 */
public class DatabaseAuthentication extends Operation{
	
	private JFrame frame;
	private JTextField serverTextField;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JLabel statusInfoLabel = new JLabel("Not logged in");
	
	/**
	 * Launch the application.
	 * @param args No arguments needed
	 */
	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	        System.out.println("Error setting native LAF: " + e);
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseAuthentication window = new DatabaseAuthentication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DatabaseAuthentication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 164, 280);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton connectButton = new JButton("Connect");
		connectButton.setBounds(10, 209, 144, 30);
		connectButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(connectButton);
		
		serverTextField = new JTextField();
		serverTextField.setBounds(10, 30, 144, 30);
		panel.add(serverTextField);
		serverTextField.setColumns(10);
		
		JLabel serverLabel = new JLabel("Server Name");
		serverLabel.setBounds(27, 11, 127, 14);
		serverLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(serverLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(10, 90, 144, 30);
		usernameTextField.setColumns(10);
		panel.add(usernameTextField);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(38, 71, 116, 14);
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(usernameLabel);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(10, 150, 144, 30);
		passwordTextField.setColumns(10);
		panel.add(passwordTextField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(40, 131, 114, 14);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(passwordLabel);
		
		JLabel statusLabel = new JLabel("Status: ");
		statusLabel.setBounds(10, 250, 46, 14);
		panel.add(statusLabel);
		
		statusInfoLabel.setBounds(48, 250, 106, 14);
		panel.add(statusInfoLabel);
		
		serverTextField.setText("localhost");
		usernameTextField.setText("root");
		passwordTextField.setText("pass1");
		
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				if(!(serverTextField.getText().equals("") || usernameTextField.getText().equals(""))){
					try{			
						createConnection(serverTextField.getText(),usernameTextField.getText(),passwordTextField.getText(),"");
						statusInfoLabel.setText("Connecting");
						statusInfoLabel.setForeground(Color.green);
						new PharmacyAction(serverTextField.getText(),usernameTextField.getText(),passwordTextField.getText());
						frame.dispose();
					}
					catch (Exception e){
						statusInfoLabel.setText("Error In Connection");
						statusInfoLabel.setForeground(Color.red);
					}
					
				}
			}
		});
		frame.setBounds(100, 100, 200, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

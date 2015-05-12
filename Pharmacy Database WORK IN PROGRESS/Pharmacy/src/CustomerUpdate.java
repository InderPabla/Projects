import javax.swing.JFrame;

/**
 * Class allows to create a customer query update to a customer.
 * @author INDERPREET PABLA
 */
public class CustomerUpdate {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public CustomerUpdate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}

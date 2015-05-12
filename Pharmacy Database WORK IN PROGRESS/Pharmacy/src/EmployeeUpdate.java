import javax.swing.JFrame;

/**
 * Class allows to update an employee in the Employee table.
 * @author INDERPREET PABLA
 */
public class EmployeeUpdate {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public EmployeeUpdate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

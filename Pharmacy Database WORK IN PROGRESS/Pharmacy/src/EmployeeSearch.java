import javax.swing.JFrame;

/**
 * Class allows to search for and employee in the Employee table.
 * @author INDERPREET PABLA
 */
public class EmployeeSearch {

	private JFrame frame;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

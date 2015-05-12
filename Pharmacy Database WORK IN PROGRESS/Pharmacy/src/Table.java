import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 * Class allows to create a JTable with relevent infromaiton.
 * @author INDERPREET PABLA
 */
public class Table {

	private JFrame frame;
	private JTable table;
	
	/**
	 * Create a table.
	 * @param data Contains data.
	 * @param columnNames Contains column names.
	 */
	public Table(Object[][] data, Object[]columnNames) {
		initialize();
		table.setModel(new DefaultTableModel(
				data,
				columnNames
			));	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Table");
		titleLabel.setBounds(378, 11, 51, 20);
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(titleLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 864, 709);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		frame.setVisible(true);
	}
}

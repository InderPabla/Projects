import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 * Class allows do a self SQL query by the user.
 * @author INDERPREET PABLA
 */
public class SelfQuery extends Operation{

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	
	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public SelfQuery(String server, String username, String password) {
		
		createConnection(server,username,password,pharmacyName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSqlQuery = new JLabel("SQL Query");
		lblSqlQuery.setForeground(Color.BLACK);
		lblSqlQuery.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSqlQuery.setBounds(334, 11, 89, 20);
		frame.getContentPane().add(lblSqlQuery);
		
		textField = new JTextField();
		textField.setBounds(10, 42, 470, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSqlQueryStatement = new JLabel("SQL Query Statement");
		lblSqlQueryStatement.setForeground(Color.BLACK);
		lblSqlQueryStatement.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSqlQueryStatement.setBounds(10, 22, 128, 20);
		frame.getContentPane().add(lblSqlQueryStatement);
		
		JButton btnNewButton = new JButton("Query");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				query(textField.getText());
			}
		});
		btnNewButton.setBounds(509, 41, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 764, 370);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		frame.setVisible(true);
	}
	
	
	/**
	 * SQL query by user and then displayed results onto a table.
	 * @param sqlQuery String to query.
	 */
	public void query(String sqlQuery){
		int numberOfColumns = 0;
		try{
			Object[] metaDataArray;
			ArrayList<Object[]> resultSetList = new ArrayList<Object[]>();
			Object[][] resultSetArray = null;
			int index =0;
			statement = connection.createStatement();					
			resultSet = statement.executeQuery(sqlQuery);			
			metaData = resultSet.getMetaData();			
			numberOfColumns = metaData.getColumnCount();			
			metaDataArray = new Object[numberOfColumns];
			
			for(int i=1 ; i<=numberOfColumns ; i++){
				metaDataArray[i-1]= metaData.getColumnName(i);
			}
			
			while(resultSet.next()){
				Object[] resultSetIndividual = new Object[numberOfColumns];
				for(int i=1 ; i<=numberOfColumns ; i++){
					resultSetIndividual[i-1] = resultSet.getObject(i);				
				}
				resultSetList.add(resultSetIndividual);
				index++;
			}
			resultSetArray = new Object[index][numberOfColumns];
			for(int i=0;i<index;i++){
				Object[] obj = resultSetList.get(i);
				for(int j =0;j<numberOfColumns;j++){
					resultSetArray[i][j] = obj[j]; 
				}
			}
			
			table.setModel(new DefaultTableModel(
				resultSetArray,
				metaDataArray
			));	
		}
		catch(Exception e){}
	}
}

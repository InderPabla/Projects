import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Class allows to search for logs in the Log table.
 * @author INDERPREET PABLA
 */
public class LogSearch extends Operation{

	private JFrame frame;
	
	private JTextField indexField1;
	private JTextField indexField2;
	private JTextField operationField;
	private JTextField nameField;

	/**
	 * Windows Builder auto-parser constructor.
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @wbp.parser.constructor window builder parser
	 */
	public LogSearch(String server, String username, String password) {
		createConnection(server,username,password,pharmacyName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 430, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 415, 462);
		scrollPane.setVisible(true);
		frame.getContentPane().add(scrollPane);
		
		JPanel searchLogPanel = new JPanel();
		scrollPane.setViewportView(searchLogPanel);
		searchLogPanel.setForeground(Color.DARK_GRAY);
		searchLogPanel.setLayout(null);
		
		JLabel searchLogLabel = new JLabel("Search Log");
		searchLogLabel.setBounds(147, 11, 96, 20);
		searchLogLabel.setForeground(new Color(255, 0, 204));
		searchLogLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchLogPanel.add(searchLogLabel);
		
		JPanel indexPanelOut = new JPanel();
		indexPanelOut.setBounds(4, 35, 376, 67);
		indexPanelOut.setForeground(Color.RED);
		indexPanelOut.setBackground(Color.LIGHT_GRAY);
		indexPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log Number Search (TRANS_NUM)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		searchLogPanel.add(indexPanelOut);
		indexPanelOut.setLayout(null);
		
		JPanel indexPanelIn = new JPanel();
		indexPanelIn.setBackground(Color.WHITE);
		indexPanelIn.setBounds(6, 16, 364, 43);
		indexPanelOut.add(indexPanelIn);
		indexPanelIn.setLayout(null);
		
		JLabel indexLabel = new JLabel("Log Number Search (Ex: 1-10)");
		indexLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		indexLabel.setBounds(10, 0, 202, 14);
		indexPanelIn.add(indexLabel);
		
		indexField1 = new JTextField();
		indexField1.setBounds(10, 17, 107, 20);
		indexPanelIn.add(indexField1);
		indexField1.setColumns(10);
		
		indexField2 = new JTextField();
		indexField2.setColumns(10);
		indexField2.setBounds(139, 17, 107, 20);
		indexPanelIn.add(indexField2);
		
		JLabel indexDashLabel = new JLabel("-");
		indexDashLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		indexDashLabel.setBounds(125, 20, 10, 14);
		indexPanelIn.add(indexDashLabel);
		
		JButton indexButton = new JButton("Search");
		indexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!(	!isInteger(indexField1.getText()) ||
						indexField1.getText().equals("") ||
						!isInteger(indexField2.getText()) ||
						indexField2.getText().equals("")
				)){
					searchByIndex(Integer.parseInt(indexField1.getText()),Integer.parseInt(indexField2.getText()),true);			
				}
			}
		});
		indexButton.setBounds(265, 16, 89, 23);
		indexPanelIn.add(indexButton);
		
		JPanel operationPanelOut = new JPanel();
		operationPanelOut.setBounds(4, 113, 376, 67);
		operationPanelOut.setLayout(null);
		operationPanelOut.setForeground(Color.RED);
		operationPanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log Operation (OPERATION)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		operationPanelOut.setBackground(Color.LIGHT_GRAY);
		searchLogPanel.add(operationPanelOut);
		
		JPanel operationPanelIn = new JPanel();
		operationPanelIn.setLayout(null);
		operationPanelIn.setBackground(Color.WHITE);
		operationPanelIn.setBounds(6, 16, 364, 43);
		operationPanelOut.add(operationPanelIn);
		
		JLabel operationLabel = new JLabel("Log Operation Sub-String (Ex: \"Insert\")");
		operationLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		operationLabel.setBounds(10, 0, 260, 14);
		operationPanelIn.add(operationLabel);
		
		operationField = new JTextField();
		operationField.setColumns(10);
		operationField.setBounds(10, 17, 245, 20);
		operationPanelIn.add(operationField);
		
		JButton operationButton = new JButton("Search");
		operationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchByAddress(operationField.getText(),true);
			}
		});
		operationButton.setBounds(265, 16, 89, 23);
		operationPanelIn.add(operationButton);
		
		JPanel namePanelOut = new JPanel();
		namePanelOut.setLayout(null);
		namePanelOut.setForeground(Color.RED);
		namePanelOut.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log Table Name (TABLE_NAME)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		namePanelOut.setBackground(Color.LIGHT_GRAY);
		namePanelOut.setBounds(4, 191, 376, 67);
		searchLogPanel.add(namePanelOut);
		
		JPanel namePanelIn = new JPanel();
		namePanelIn.setLayout(null);
		namePanelIn.setBackground(Color.WHITE);
		namePanelIn.setBounds(6, 16, 364, 43);
		namePanelOut.add(namePanelIn);
		
		JLabel nameLabel = new JLabel("Log Table Name Sub-String (Ex: \"Customer\")");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameLabel.setBounds(10, 0, 317, 14);
		namePanelIn.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(10, 17, 245, 20);
		namePanelIn.add(nameField);
		
		JButton nameButton = new JButton("Search");
		nameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByName(nameField.getText(),true);
			}
		});
		nameButton.setBounds(265, 16, 89, 23);
		namePanelIn.add(nameButton);
		
		JButton allSearchButton = new JButton("Search With All");
		allSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int index1,index2;
				 
				if(!isInteger(indexField1.getText()) || 
				   !isInteger(indexField2.getText()) ||
				   indexField1.getText().equals("") ||
				   indexField2.getText().equals("")
				){
					index1 = -1;
					index2 = -1;
				}
				else{
					index1 = Integer.parseInt(indexField1.getText());
					index2 = Integer.parseInt(indexField2.getText());
				}
				
				searchByAll(index1,index2,operationField.getText(),nameField.getText(),true);
			}
		});
		allSearchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		allSearchButton.setBounds(126, 426, 138, 23);
		searchLogPanel.add(allSearchButton);
		frame.setVisible(true);
	}
	
	/**
	 * Query search using index constraints (index1>= searched indices <=index2).
	 * @param index1 Lower index bound
	 * @param index2 Higher index bound
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByIndex(int index1,int index2,boolean makeTable){
		String query = "SELECT * FROM LOG WHERE (TRANS_NUM >="+index1+" && TRANS_NUM<="+index2+")";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which logged table names in the Log table contain the substring name.
	 * @param hours Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByName(String name,boolean makeTable){
		String query = "SELECT * FROM LOG WHERE (TABLE_NAME LIKE '%"+name+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find which log operation in the Log table contain the substring operation.
	 * @param operation Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByAddress(String operation,boolean makeTable){
		String query = "SELECT * FROM LOG WHERE (OPERATION LIKE '%"+operation+"%')";
		search(query, makeTable);
	}
	
	/**
	 * Query search to find logs given various constraints.
	 * @param index1 Lower index bound
	 * @param index2 Higher index bound
	 * @param operation Substring to check.
	 * @param name Substring to check.
	 * @param phone Substring to check.
	 * @param makeTable True or false if a table should be created upon query.
	 */
	public void searchByAll(int index1,int index2,String operation,String name,boolean makeTable){
		String query = "SELECT * FROM LOG";
		
		if(!(index1 == -1 || index2 == -1)){
			query += " WHERE (TRANS_NUM >="+index1+" && TRANS_NUM<="+index2+")";
		}
		
		query = "SELECT * FROM ("+query+") AS A WHERE (OPERATION LIKE '%"+operation+"%')";
		query = "SELECT * FROM ("+query+") AS A WHERE (TABLE_NAME LIKE '%"+name+"%')";
		
		search(query,makeTable);
	}
	
	/**
	 * Search Log data with the given query and get data. 
	 * @param query Search with given query and get data.
	 * @param makeTable If true a window will open to show a table with the customer number index and relevant information.
	 */
	public void search(String query, boolean makeTable){
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);	
			metaData = resultSet.getMetaData();	
			ArrayList<Object[]> resultSetList = new ArrayList<Object[]>();
			Object[][] resultSetArray = null;
			int numberOfColumns = metaData.getColumnCount();			
			int index =0;
			
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
			if(makeTable){
				new Table(resultSetArray,logColumnNames);
			}
			
		}
		catch(Exception e){System.out.println("err");}
	}
}

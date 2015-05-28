import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Operation class has many common operation that will be needed by other child classes.
 * @author INDERPREET PABLA
 */
public class Operation {
	Connection connection = null;
	Statement statement = null;	
	ResultSet resultSet = null;	
	ResultSetMetaData metaData = null;
	
	String pharmacyName = "pharmacy";
	
	String[] custColumnNames = {"CUST_NUM","CUST_NAME","CUST_PHN_NUM","CUST_EMAIL","CUST_DOB","CUST_ADRS","CUST_APRVD_DRUG"};
	String[] empColumnNames = {"EMP_NUM","EMP_NAME","EMP_PHN_NUM","EMP_DOB","EMP_ADRS","EMP_HIRE_DATE","EMP_HRLY_WGE","EMP_TITLE","STORE_NUM"};	
	String[] invColumnNames = {"ITEM_NUM","ITEM_CST_FROM_SUP","ITEM_CST_TO_CUST","ITEM_DESC","ITEM_INS_COVG","ITEM_PRESCRIPTION","SUP_NUM"};
	String[] pharColumnNames = {"STORE_NUM","STORE_ADRS","STORE_HRS","STORE_PHN_NUM"};
	String[] supColumnNames = {"SUP_NUM","SUP_ADRS","SUP_NAME","SUP_PHN_NUM"};
	String[] logColumnNames = {"TRANS_NUM","OPERATION","TABLE_NAME","TIME_OF_ACTON"};
	
	String maxCustIndexQuery = "SELECT MAX(CUST_NUM) AS max FROM customer";
	String maxEmpIndexQuery = "SELECT MAX(EMP_NUM) AS max FROM employee";
	String maxInvIndexQuery = "SELECT MAX(ITEM_NUM) AS max FROM inventory";
	String maxPharIndexQuery = "SELECT MAX(STORE_NUM) AS max FROM pharmacy";
	String maxSupIndexQuery = "SELECT MAX(SUP_NUM) AS max FROM supplier";
	String maxLogIndexQuery = "SELECT MAX(TRANS_NUM) AS max FROM log";
	
	/**
	 * @param date Date to convert to string that can be queried by SQL.
	 * @return String which can be queries by SQL.
	 */
	public String dateToStringFormat(Date date){
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
        String dateFormat = DATE_FORMAT.format(date);
        return dateFormat;
	}
	
	/**
	 * @param date String date to convert to a Date object.
	 * @return Date which can be queries by SQL.
	 */
	@SuppressWarnings("deprecation")
	public Date stringToDateFormat(String date){
		
		
		String[] split = date.split("-");
		Date dateFormat = new Date(Integer.parseInt(split[0])-1900,Integer.parseInt(split[1])-1,Integer.parseInt(split[2]));
		return dateFormat;
	}
	
	/**
	 * @param server Name of the server to connect to.
	 * @param username Name of user.
	 * @param password Password to the server.
	 * @param db Database to connect to.
	 */
	public void createConnection(String server, String username, String password, String db){
		try{
			Class.forName("com.mysql.jdbc.Driver");			
			connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+db ,username,password);	
		}
		catch(Exception e){}
	}
	
	/**
	 * @param str String to check if it's a float.
	 * @return If error during parse then false, else true
	 */
	public boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	/**
	 * @param str String to check if it's an integer.
	 * @return If error during parse then false, else true
	 */
	public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
}
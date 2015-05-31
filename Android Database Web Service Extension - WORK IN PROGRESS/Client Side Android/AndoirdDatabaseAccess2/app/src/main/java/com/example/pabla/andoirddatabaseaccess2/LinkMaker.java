package com.example.pabla.andoirddatabaseaccess2;

/**
 * LinkMaker class handles all link creation for various activities.
 * @author INDERPREET PABLA
 */
public class LinkMaker
{
    //Link skeletons to be filled in.
    private final String authenticateLink = "http://[WEB-MARKER][PORT-MARKER]/[FOLDER-MARKER]/authenticate.php?server=[SERVER-MARKER]&username=[USERNAME-MARKER]&password=[PASSWORD-MARKER]";
    private final String databaseRetrieveLink = "http://[WEB-MARKER][PORT-MARKER]/[FOLDER-MARKER]/database_retrieve.php?server=[SERVER-MARKER]&username=[USERNAME-MARKER]&password=[PASSWORD-MARKER]";
    private final String tableRetrieveLink = "http://[WEB-MARKER][PORT-MARKER]/[FOLDER-MARKER]/table_retrieve.php?server=[SERVER-MARKER]&username=[USERNAME-MARKER]&password=[PASSWORD-MARKER]&database=[DATABASE-MARKER]";
    private final String columnRetrieveLink = "http://[WEB-MARKER][PORT-MARKER]/[FOLDER-MARKER]/column_retrieve.php?server=[SERVER-MARKER]&username=[USERNAME-MARKER]&password=[PASSWORD-MARKER]&database=[DATABASE-MARKER]&table=[TABLE-MARKER]";
    private final String insertLink = "http://[WEB-MARKER][PORT-MARKER]/[FOLDER-MARKER]/insert.php?server=[SERVER-MARKER]&username=[USERNAME-MARKER]&password=[PASSWORD-MARKER]&database=[DATABASE-MARKER]&table=[TABLE-MARKER]&length=[DATA-LENGTH-MARKER][DATA-MARKER]";
    private final String searchLink = "http://[WEB-MARKER][PORT-MARKER]/[FOLDER-MARKER]/search.php?server=[SERVER-MARKER]&username=[USERNAME-MARKER]&password=[PASSWORD-MARKER]&database=[DATABASE-MARKER]&table=[TABLE-MARKER]&length=[DATA-LENGTH-MARKER][DATA-MARKER]&bound1=[BOUND-LOWER-MARKER]&bound2=[BOUND-HIGHER-MARKER]";
    private final String updateLink = ""; //Not done
    private final String deleteLink = ""; //Not done

    //Tags which identify parts of the links are tagged markers.
    private final String WEB_TAG = "[WEB-MARKER]";
    private final String PORT_TAG = "[PORT-MARKER]";
    private final String FOLDER_TAG = "[FOLDER-MARKER]";
    private final String SERVER_TAG = "[SERVER-MARKER]";
    private final String USERNAME_TAG = "[USERNAME-MARKER]";
    private final String PASSWORD_TAG = "[PASSWORD-MARKER]";
    private final String DATABASE_TAG = "[DATABASE-MARKER]";
    private final String TABLE_TAG = "[TABLE-MARKER]";
    private final String DATA_LENGTH_TAG = "[DATA-LENGTH-MARKER]";
    private final String DATA_TAG = "[DATA-MARKER]";
    private final String BOUND_LOWER_TAG = "[BOUND-LOWER-MARKER]";
    private final String BOUND_HIGHER_TAG = "[BOUND-HIGHER-MARKER]";

    //Typical information filled with constructor calls.
    private String web;
    private int port;
    private String folder;
    private String server;
    private String username;
    private String password;
    private String database;
    private String table;
    private String[] data;

    String link; //link which is created to be used

    /**
     * Constructor saves 6 pieces of data. Used for authenticate.php and database_retrieve.php.
     * @param web The name of the website.
     * @param port The port number of the website to access.
     * @param folder The folders to where the webservice has the php files.
     * @param server Name of the server.
     * @param username Name of the user's account on the server.
     * @param password Password of the user on the account.
     */
    public LinkMaker(String web, String port, String folder,String server, String username, String password)
    {
        normalInstantiation(web,port,folder,server,username,password);
    }

    /**
     * Constructor saves 7 pieces of data. Used for table_retrieve.php.
     * @param web The name of the website.
     * @param port The port number of the website to access.
     * @param folder The folders to where the webservice has the php files.
     * @param server Name of the server.
     * @param username Name of the user's account on the server.
     * @param password Password of the user on the account.
     * @param database Name of the database
     */
    public LinkMaker(String web, String port, String folder,String server, String username, String password, String database)
    {
        normalInstantiation(web,port,folder,server,username,password);
        this.database = database;
    }

    /**
     * Constructor saves 8 pieces of data. Used for column_retrieve.php.
     * @param web The name of the website.
     * @param port The port number of the website to access.
     * @param folder The folders to where the webservice has the php files.
     * @param server Name of the server.
     * @param username Name of the user's account on the server.
     * @param password Password of the user on the account.
     * @param database Name of the database
     * @param table Name of the table.
     */
    public LinkMaker(String web, String port, String folder,String server, String username, String password, String database, String table)
    {
        normalInstantiation(web,port,folder,server,username,password);
        this.database = database;
        this.table = table;
    }

    /**
     * Constructor saves 9 pieces of data. Used for insert.php, search.php, update.php and delete.php
     * @param web The name of the website.
     * @param port The port number of the website to access.
     * @param folder The folders to where the webservice has the php files.
     * @param server Name of the server.
     * @param username Name of the user's account on the server.
     * @param password Password of the user on the account.
     * @param database Name of the database
     * @param table Name of the table.
     * @param data The data which need to be queried.
     */
    public LinkMaker(String web, String port, String folder,String server, String username, String password, String database, String table, String[] data)
    {
        normalInstantiation(web,port,folder,server,username,password);
        this.database = database;
        this.table = table;

        this.data = new String[data.length];
        for(int i = 0; i<data.length;i++)
        {
            this.data[i] = data[i];
        }
    }

    /**
     * Saves the data into global variables.
     * @param web The name of the website.
     * @param port The port number of the website to access.
     * @param folder The folders to where the webservice has the php files.
     * @param server Name of the server.
     * @param username Name of the user's account on the server.
     * @param password Password of the user on the account.
     */
    public void normalInstantiation(String web, String port, String folder,String server, String username, String password)
    {
        this.web = web;

        if(port.equals("") || !isInteger(port))
        {
            this.port = -1;
        }
        else
        {
            this.port = Integer.parseInt(port);
        }

        this.folder = folder;
        this.server = server;
        this.username = username;
        this.password = password;
    }

    /**
     * Makes authenticate link.
     * @return The link string which was created.
     */
    public String makeAuthenticateLink()
    {
        String link = authenticateLink;
        link = normalReplace(link);
        this.link = link;
        return link;
    }

    /**
     * Makes database retrieve link.
     * @return The link string which was created.
     */
    public String makeDatabaseRetrieveLink()
    {
        String link = databaseRetrieveLink;
        link = normalReplace(link);
        this.link = link;
        return link;
    }

    /**
     * Makes table retrieve link.
     * @return The link string which was created.
     */
    public String makeTableRetrieveLink()
    {
        String link = tableRetrieveLink;
        link = normalReplace(link);
        link = link.replace(DATABASE_TAG, database);
        this.link = link;
        return link;
    }

    /**
     * Makes column retrieve link.
     * @return The link string which was created.
     */
    public String makeColumnRetrieveLink()
    {
        String link = columnRetrieveLink;
        link = normalReplace(link);
        link = link.replace(DATABASE_TAG, database);
        link = link.replace(TABLE_TAG, table);
        this.link = link;
        return link;
    }

    /**
     * Makes insert link.
     * @return The link string which was created.
     */
    public String makeInsertLink()
    {
        String link = insertLink;
        link = normalReplace(link);
        link = link.replace(DATABASE_TAG, database);
        link = link.replace(TABLE_TAG, table);
        link = link.replace(DATA_LENGTH_TAG,data.length+"");

        String dataToInsert = "";
        for(int i = 0; i < data.length; i++)
        {
            dataToInsert += "&data"+i+"="+data[i];
        }
        link = link.replace(DATA_TAG,dataToInsert);

        this.link = link;
        return link;
    }

    /**
     * Makes search link.
     * @param lowerBound The lower bound to replace BOUND_LOWER_TAG
     * @param higherBound The higher bound to replace BOUND_HIGHER_TAG
     * @return The link string which was created.
     */
    public String makeSearchLink(int lowerBound, int higherBound)
    {
        String link = searchLink;
        link = normalReplace(link);
        link = link.replace(DATABASE_TAG, database);
        link = link.replace(TABLE_TAG, table);
        link = link.replace(DATA_LENGTH_TAG,data.length+"");

        String dataToInsert = "";
        for(int i = 0; i < data.length; i++)
        {
            dataToInsert += "&data"+i+"="+data[i];
        }
        link = link.replace(DATA_TAG,dataToInsert);

        link = link.replace(BOUND_LOWER_TAG,lowerBound+"");
        link = link.replace(BOUND_HIGHER_TAG,higherBound+"");

        this.link = link;
        return link;
    }

    /**
     * Replaces the first 6 data sets to there appropriate global variables.
     * @return The link string which was normalized.
     */
    private String normalReplace(String link)
    {
        String tempLink = link;
        tempLink = tempLink.replace(WEB_TAG,web);
        if(port!=-1)
        {
            tempLink = tempLink.replace(PORT_TAG,":"+port);
        }
        else
        {
            tempLink = tempLink.replace(PORT_TAG,"");
        }
        tempLink = tempLink.replace(FOLDER_TAG,folder);

        tempLink = tempLink.replace(SERVER_TAG,server);
        tempLink = tempLink.replace(USERNAME_TAG,username);
        tempLink = tempLink.replace(PASSWORD_TAG,password);

        return tempLink;
    }

    /**
     * Check if string is an integer.
     * @param check String to check if it's an integer.
     * @return If error during parse then false, else true
     */
    public boolean isInteger(String check)
    {
        try
        {
            int integer = Integer.parseInt(check);
            return true;
        }
        catch(Exception error)
        {
            return false;
        }
    }
}

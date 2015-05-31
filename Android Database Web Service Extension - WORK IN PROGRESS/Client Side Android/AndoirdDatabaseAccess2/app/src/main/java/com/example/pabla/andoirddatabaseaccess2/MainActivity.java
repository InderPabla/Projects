package com.example.pabla.andoirddatabaseaccess2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener
{

    private final String STATE_WEB_TAG = "WEB";
    private final String STATE_PORT_TAG = "PORT";
    private final String STATE_FOLDER_TAG = "FOLDER";
    private final String STATE_SERVER_TAG = "SERVER";
    private final String STATE_USERNAME_TAG = "USERNAME";
    private final String STATE_PASSWORD_TAG = "PASSWORD";
    private final String STATE_DATABASE_TAG = "DATABASE";
    private final String STATE_TABLE_TAG = "TABLE";
    private final String SUCCESS_TAG = "SUCCESS";

    private final int AUTHENTICATE_STATUS_TAG = 1;
    private final int DATABASE_STATUS_TAG = 2;
    private final int TABLE_STATUS_TAG = 3;
    private final int OPERATION_STATUS_TAG = 4;
    private final int OPERATION_SUB_STATUS_TAG = 5;

    private final String DATABASE_TITLE_TAG = "Database Names";
    private final String TABLE_TITLE_TAG = "Table Names";
    private final String COLUMN_TITLE_TAG = "Column Names";
    private final String OPERATION_TITLE_TAG = "Operations";
    private final String INSERT_TITLE_TAG = "Insert";
    private final String SEARCH_TITLE_TAG = "Search";

    private int currentStatusTag = -1;
    private String currentStatusOperationTag = "";

    private ButtonAnimate connectButton,operationButton;

    private LayoutLoader loader;

    JSONParser databaseNames,columnNames;

    private View authenticateView;

    Bundle savedInstanceState = new Bundle();

    String selectedDatabase;
    String selectedTable;

    ArrayList<String> tableOperations;
    String[] operations = {"Check Columns","Insert","Search","Update","Delete"};

    boolean operationInProgressLock = false;

    int boundDelta = 10;
    int bound1,bound2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        loader = new LayoutLoader(this);
        loader.loadMain();

        connectButton = new ButtonAnimate(this.findViewById(R.id.connectButton), Color.RED,500);

        setUserInputs("192.168.0.14", "80", "a", "localhost", "root", "pass1"); //--Used only for testing--

        tableOperations = new ArrayList<String>();
        for(int i = 0;i<operations.length;i++)
        {
            tableOperations.add(operations[i]);
        }

        bound1 = 0;
        bound2 = boundDelta;
    }

    public void nextLoadAction(View view)
    {
        bound1+=boundDelta;
        bound2+=boundDelta;
        operationAction(view);
    }

    public void previousLoadAction(View view)
    {
        if(bound1>=10)
        {
            bound1-=boundDelta;
            bound2-=boundDelta;
            operationAction(view);
        }
    }

    public void operationAction(View view)
    {
        if(operationInProgressLock == false)
        {
            if(currentStatusOperationTag.equals(operations[1]))
            {
                operationInProgressLock = true;
                String[] data = getUserInputs(true);
                LinkMaker insertLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],selectedDatabase,selectedTable,loader.getInputTexts());
                String link = insertLink.makeInsertLink();
                Connector connector = new Connector(link);
                String response = connector.connect();

                if(response.equals(SUCCESS_TAG))
                {
                    onBackPressed();
                }
                else
                {
                    operationButton.animate();
                }

                operationInProgressLock = false;
            }
            else if(currentStatusOperationTag.equals(operations[2]))
            {
                operationInProgressLock = true;
                String[] data = getUserInputs(true);
                LinkMaker searchLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],selectedDatabase,selectedTable,loader.getInputTexts());
                String link = searchLink.makeSearchLink(bound1, bound2);
                Connector connector = new Connector(link);
                String response = connector.connect();

                JSONParser tableData = new JSONParser(response,JSONParser.DATA_PARSE_TAG);
                for(int i = 0;i<columnNames.data1.size();i++){
                    Log.i("MyActivity",columnNames.data1.get(i));
                }
                loader.loadDataList(tableData.data1, columnNames.data1,SEARCH_TITLE_TAG);
                loader.list.setOnItemClickListener(this);

                currentStatusTag = OPERATION_STATUS_TAG;

                operationInProgressLock = false;
            }
        }
    }

    public void authenticate(View view)
    {
        String[] data = getUserInputs(false);

        LinkMaker authenticateLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5]);
        String link = authenticateLink.makeAuthenticateLink();
        Connector connector = new Connector(link);
        String response = connector.connect();

        currentStatusTag = AUTHENTICATE_STATUS_TAG;

        if(!response.equals(SUCCESS_TAG))
        {
             failedAuthenticationProcess(); //fail
        }
        else
        {
            passedAuthenticationProcess(); //pass
        }
    }

    public void failedAuthenticationProcess()
    {
        connectButton.animate();
    }

    public void passedAuthenticationProcess()
    {
        String[] data = getUserInputs(false);

        LinkMaker databaseRetrieveLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5]);
        String link = databaseRetrieveLink.makeDatabaseRetrieveLink();
        Connector connector = new Connector(link);
        String response = connector.connect();

        databaseNames = new JSONParser(response,JSONParser.DATABASE_PARSE_TAG);

        loader.loadTextList(databaseNames.data1,DATABASE_TITLE_TAG);
        loader.list.setOnItemClickListener(this);

        currentStatusTag = DATABASE_STATUS_TAG;
        saveAuthenticateState(data);
    }

    public void saveAuthenticateState(String[] data)
    {
        savedInstanceState.putString(STATE_WEB_TAG, data[0]);
        savedInstanceState.putString(STATE_PORT_TAG, data[1]);
        savedInstanceState.putString(STATE_FOLDER_TAG, data[2]);
        savedInstanceState.putString(STATE_SERVER_TAG, data[3]);
        savedInstanceState.putString(STATE_USERNAME_TAG, data[4]);
        savedInstanceState.putString(STATE_PASSWORD_TAG, data[5]);
    }

    public void loadAuthenticateState() {
        Log.i("MyActivity", savedInstanceState.getString(STATE_WEB_TAG));
        String web = savedInstanceState.getString(STATE_WEB_TAG);
        String port =  savedInstanceState.getString(STATE_PORT_TAG);
        String folder = savedInstanceState.getString(STATE_FOLDER_TAG);
        String server = savedInstanceState.getString(STATE_SERVER_TAG);
        String username = savedInstanceState.getString(STATE_USERNAME_TAG);
        String password = savedInstanceState.getString(STATE_PASSWORD_TAG);

        setUserInputs(web, port, folder, server, username, password);
    }

    public void getTables()
    {
        String[] data = getUserInputs(true);

        LinkMaker tableRetrieveLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],selectedDatabase);
        String link = tableRetrieveLink.makeTableRetrieveLink();
        Connector connector = new Connector(link);
        String response = connector.connect();
        JSONParser tableNames = new JSONParser(response,JSONParser.TABLE_PARSE_TAG);

        loader.loadTextList(tableNames.data1,TABLE_TITLE_TAG);
        loader.list.setOnItemClickListener(this);

        currentStatusTag = TABLE_STATUS_TAG;
    }

    public void getColumns()
    {
        String[] data = getUserInputs(true);

        LinkMaker columnRetrieveLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],selectedDatabase,selectedTable);
        String link = columnRetrieveLink.makeColumnRetrieveLink();
        Connector connector = new Connector(link);
        String response = connector.connect();
        columnNames = new JSONParser(response,JSONParser.COLUMN_PARSE_TAG);

        loader.loadTextList(columnNames.combined,COLUMN_TITLE_TAG);
        loader.list.setOnItemClickListener(this);

        currentStatusOperationTag = operations[0];
        currentStatusTag = OPERATION_SUB_STATUS_TAG;
    }

    public void getInserts()
    {
        String[] data = getUserInputs(true);

        LinkMaker columnRetrieveLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],selectedDatabase,selectedTable);
        String link = columnRetrieveLink.makeColumnRetrieveLink();
        Connector connector = new Connector(link);
        String response = connector.connect();
        columnNames = new JSONParser(response,JSONParser.COLUMN_PARSE_TAG);

        loader.loadInputList(columnNames.combined, INSERT_TITLE_TAG);
        loader.list.setOnItemClickListener(this);

        operationButton = new ButtonAnimate(this.findViewById(R.id.operationButton), Color.RED,500);
        currentStatusOperationTag = operations[1];
        currentStatusTag = OPERATION_SUB_STATUS_TAG;
    }

    public void getSearchs()
    {
        String[] data = getUserInputs(true);

        LinkMaker columnRetrieveLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],selectedDatabase,selectedTable);
        String link = columnRetrieveLink.makeColumnRetrieveLink();
        Connector connector = new Connector(link);
        String response = connector.connect();
        columnNames = new JSONParser(response,JSONParser.COLUMN_PARSE_TAG);

        loader.loadInputList(columnNames.combined, SEARCH_TITLE_TAG);
        loader.list.setOnItemClickListener(this);

        operationButton = new ButtonAnimate(this.findViewById(R.id.operationButton), Color.RED,500);
        currentStatusOperationTag = operations[2];
        currentStatusTag = OPERATION_SUB_STATUS_TAG;
    }

    public void setTableOperations()
    {
        loader.loadTextList(tableOperations, OPERATION_TITLE_TAG);
        loader.list.setOnItemClickListener(this);
        currentStatusTag = OPERATION_STATUS_TAG;
    }

    public String[] getUserInputs(boolean fromBundle)
    {
        if(fromBundle)
        {
            String web = savedInstanceState.getString(STATE_WEB_TAG);
            String port = savedInstanceState.getString(STATE_PORT_TAG);
            String folder = savedInstanceState.getString(STATE_FOLDER_TAG);
            String server = savedInstanceState.getString(STATE_SERVER_TAG);
            String username = savedInstanceState.getString(STATE_USERNAME_TAG);
            String password = savedInstanceState.getString(STATE_PASSWORD_TAG);

            return new String[]{web,
                    port,
                    folder,
                    server,
                    username,
                    password};
        }
        else
        {
            EditText webText = (EditText)this.findViewById(R.id.webField);
            EditText portText = (EditText)this.findViewById(R.id.portField);
            EditText folderText = (EditText)this.findViewById(R.id.folderField);
            EditText serverText = (EditText)this.findViewById(R.id.serverField);
            EditText usernameText = (EditText)this.findViewById(R.id.nameField);
            EditText passwordText = (EditText)this.findViewById(R.id.passField);

            return new String[]{webText.getText().toString(),
                                portText.getText().toString(),
                                folderText.getText().toString(),
                                serverText.getText().toString(),
                                usernameText.getText().toString(),
                                passwordText.getText().toString()};
        }
    }

    private void setUserInputs(String web, String port, String folder,String server, String username, String password)
    {
        EditText webText = (EditText)this.findViewById(R.id.webField);
        EditText portText = (EditText)this.findViewById(R.id.portField);
        EditText folderText = (EditText)this.findViewById(R.id.folderField);
        EditText serverText = (EditText)this.findViewById(R.id.serverField);
        EditText usernameText = (EditText)this.findViewById(R.id.nameField);
        EditText passwordText = (EditText)this.findViewById(R.id.passField);

        webText.setText(web);
        portText.setText(port);
        folderText.setText(folder);
        serverText.setText(server);
        usernameText.setText(username);
        passwordText.setText(password);
    }

    public void onItemClick(AdapterView<?> adapter, View view, int position, long identity)
    {
        String item = ((TextView) view).getText().toString();

        if(currentStatusTag == DATABASE_STATUS_TAG)
        {
            selectedDatabase = item;
            getTables();
        }

        else if(currentStatusTag == TABLE_STATUS_TAG)
        {
            selectedTable = item;
            setTableOperations();
        }

        else if(currentStatusTag == OPERATION_STATUS_TAG)
        {

            if(item.equals(operations[0]))
            {
                getColumns();
            }
            else if(item.equals(operations[1]))
            {
                getInserts();
            }
            else if(item.equals(operations[2]))
            {
                getSearchs();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            onBackPressed();
        }
        return true;
    }

    public void onBackPressed()
    {
        currentStatusTag --;

        if(currentStatusTag < AUTHENTICATE_STATUS_TAG)
        {
            finish();
        }

        else if(currentStatusTag == AUTHENTICATE_STATUS_TAG)
        {
            loader.loadMain();
            loadAuthenticateState();
        }

        else if(currentStatusTag == DATABASE_STATUS_TAG)
        {
            loader.loadTextList(databaseNames.data1,DATABASE_TITLE_TAG);
            loader.list.setOnItemClickListener(this);
        }

        else if(currentStatusTag == TABLE_STATUS_TAG)
        {
            getTables();
        }

        else if(currentStatusTag == OPERATION_STATUS_TAG)
        {
            setTableOperations();
        }
    }
}

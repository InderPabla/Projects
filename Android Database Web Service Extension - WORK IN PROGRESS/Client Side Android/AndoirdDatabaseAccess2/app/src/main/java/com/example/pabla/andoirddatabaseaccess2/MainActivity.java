package com.example.pabla.andoirddatabaseaccess2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener
{

    private final String STATE_WEB_TAG = "[WEB-MARKER]";
    private final String STATE_PORT_TAG = "[PORT-MARKER]";
    private final String STATE_FOLDER_TAG = "[FOLDER-MARKER]";
    private final String STATE_SERVER_TAG = "[SERVER-MARKER]";
    private final String STATE_USERNAME_TAG = "[USERNAME-MARKER]";
    private final String STATE_PASSWORD_TAG = "[PASSWORD-MARKER]";
    private final String STATE_DATABASE_TAG = "[DATABASE-MARKER]";
    private final String STATE_TABLE_TAG = "[TABLE-MARKER]";
    private final String SUCCESS_TAG = "SUCCESS";

    private final int AUTHENTICATE_TAG = 1;
    private final int DATABASE_TAG = 2;
    private final int TABLE_TAG = 3;
    private final int COLUMN_TAG = 4;

    private int currentStatusTag = -1;

    private ButtonAnimate connectButton;

    private LayoutLoader loader;

    JSONParser databaseNames;

    private View authenticateView;

    Bundle savedInstanceState = new Bundle();

    String selectedDatabase;
    String selectedTable;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        loader = new LayoutLoader(this);
        loader.loadMain();

        connectButton = new ButtonAnimate(this.findViewById(R.id.connectButton), Color.RED,500);

        setUserInputs("192.168.0.10", "80", "a", "localhost", "root", "pass1"); //--Used only for testing--
    }

    public void authenticate(View view)
    {
        String[] data = getUserInputs(false);

        LinkMaker authenticateLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5]);
        String link = authenticateLink.makeAuthenticate();
        Connector connector = new Connector(link);
        String response = connector.connect();

        currentStatusTag = AUTHENTICATE_TAG;

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
        String link = databaseRetrieveLink.makeDatabaseRetrieve();
        Connector connector = new Connector(link);
        String response = connector.connect();

        databaseNames = new JSONParser(response,JSONParser.DATABASE_PARSE_TAG);

        loader.loadList(databaseNames.data);
        loader.list.setOnItemClickListener(this);

        currentStatusTag = DATABASE_TAG;
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

    public void loadAuthenticateState()
    {
        Log.i("MyActivity", savedInstanceState.getString(STATE_WEB_TAG));
        String web = savedInstanceState.getString(STATE_WEB_TAG);
        String port =  savedInstanceState.getString(STATE_PORT_TAG);
        String folder = savedInstanceState.getString(STATE_FOLDER_TAG);
        String server = savedInstanceState.getString(STATE_SERVER_TAG);
        String username = savedInstanceState.getString(STATE_USERNAME_TAG);
        String password = savedInstanceState.getString(STATE_PASSWORD_TAG);

        setUserInputs(web, port, folder, server, username, password);
    }

    public void getTables(String database)
    {
        String[] data = getUserInputs(true);

        LinkMaker tableRetrieveLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],database);
        String link = tableRetrieveLink.makeTableRetrieve();
        Connector connector = new Connector(link);
        String response = connector.connect();
        Log.i("MyActivity",link);
        JSONParser tableNames = new JSONParser(response,JSONParser.TABLE_PARSE_TAG);

        loader.loadList(tableNames.data);

        currentStatusTag = TABLE_TAG;
    }

    public void getTables()
    {
        String[] data = getUserInputs(true);

        LinkMaker tableRetrieveLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],selectedDatabase);
        String link = tableRetrieveLink.makeTableRetrieve();
        Connector connector = new Connector(link);
        String response = connector.connect();
        JSONParser tableNames = new JSONParser(response,JSONParser.TABLE_PARSE_TAG);

        loader.loadList(tableNames.data);

        currentStatusTag = TABLE_TAG;
    }

    public void getColumns()
    {
        String[] data = getUserInputs(true);

        LinkMaker columnRetrieveLink = new LinkMaker(data[0],data[1],data[2],data[3],data[4],data[5],selectedDatabase,selectedTable);
        String link = columnRetrieveLink.makeColumnRetrieve();
        Connector connector = new Connector(link);
        String response = connector.connect();
        JSONParser tableNames = new JSONParser(response,JSONParser.COLUMN_PARSE_TAG);

        loader.loadList(tableNames.data);

        currentStatusTag = TABLE_TAG;
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

        if(currentStatusTag == DATABASE_TAG)
        {
            selectedDatabase = item;
            getTables();
        }

        if(currentStatusTag == TABLE_TAG)
        {
            selectedTable = item;
            getColumns();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            currentStatusTag --;

            if(currentStatusTag < AUTHENTICATE_TAG)
            {
                finish();
            }

            if(currentStatusTag == AUTHENTICATE_TAG)
            {
                loader.loadMain();
                loadAuthenticateState();
            }

            if(currentStatusTag == DATABASE_TAG)
            {
                loader.loadList(databaseNames.data);
                loader.list.setOnItemClickListener(this);
            }
        }
        return true;
    }
}

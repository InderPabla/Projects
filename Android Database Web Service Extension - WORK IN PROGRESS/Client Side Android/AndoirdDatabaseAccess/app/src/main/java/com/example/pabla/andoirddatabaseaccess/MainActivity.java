package com.example.pabla.andoirddatabaseaccess;
import java.net.URI;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import org.json.JSONObject;
import org.json.JSONArray;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.Color;
import android.widget.ArrayAdapter;


public class MainActivity extends Activity {
    private String authenticateLink = "http://[WEBMARKER][PORTMARKER]/[FOLDERMARKER]/authenticate.php?server=[SERVERMARKER]&username=[USERNAMEMARKER]&password=[PASSWORDMARKER]";
    private String databaseTableLink = "http://[WEBMARKER][PORTMARKER]/[FOLDERMARKER]/databaseTable.php?server=[SERVERMARKER]&username=[USERNAMEMARKER]&password=[PASSWORDMARKER]&database=[DATABASENAME]";
    private String tableColLink = "http://[WEBMARKER][PORTMARKER]/[FOLDERMARKER]/tableColumn.php?server=[SERVERMARKER]&username=[USERNAMEMARKER]&password=[PASSWORDMARKER]&database=[DATABASENAME]&table=[TABLENAME]";

    private boolean running = false;
    Thread thread;
    Drawable old;
    ColorDrawable colorError = new ColorDrawable(Color.RED);
    String web,portString,folder,server,name,password,database;
    int port = -1;

    JSONArray colsJSON;
    View listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        old = ((Button)findViewById(R.id.connectButton)).getBackground();

        EditText webText = (EditText)this.findViewById(R.id.webField);
        webText.setText("192.168.0.14");
        EditText portText = (EditText)this.findViewById(R.id.portField);
        portText.setText("80");
        EditText folderText = (EditText)this.findViewById(R.id.folderField);
        folderText.setText("a");

        EditText serverText = (EditText)this.findViewById(R.id.serverField);
        serverText.setText("localhost");
        EditText nameText = (EditText)this.findViewById(R.id.nameField);
        nameText.setText("root");
        EditText passwordText = (EditText)this.findViewById(R.id.passField);
        passwordText.setText("pass1");
        listView = this.findViewById(R.id.listView);
    }

    public void connectClick (View view){
        String link = authenticateLink;

        EditText webText = (EditText)this.findViewById(R.id.webField);
        EditText portText = (EditText)this.findViewById(R.id.portField);
        EditText folderText = (EditText)this.findViewById(R.id.folderField);

        EditText serverText = (EditText)this.findViewById(R.id.serverField);
        EditText nameText = (EditText)this.findViewById(R.id.nameField);
        EditText passwordText = (EditText)this.findViewById(R.id.passField);

        web = webText.getText().toString();
        portString = portText.getText().toString();
        if(portString.equals("") || !isInteger(portString)){
            port = -1;
        }
        else{
            port = Integer.parseInt(portString);
        }
        folder = folderText.getText().toString();

        server = serverText.getText().toString();
        name = nameText.getText().toString();
        password = passwordText.getText().toString();


        link = link.replace("[WEBMARKER]",web);
        if(port!=-1){
            link = link.replace("[PORTMARKER]",":"+port);
        }
        else{
            link = link.replace("[PORTMARKER]","");
        }
        link = link.replace("[FOLDERMARKER]",folder);

        link = link.replace("[SERVERMARKER]",server);
        link = link.replace("[USERNAMEMARKER]",name);
        link = link.replace("[PASSWORDMARKER]",password);
        connect (link);
    }

    public void connect(String link){
        try{
            HttpClient client = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            request.setHeader("Content-type=application/json", "charset=utf-8");
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String htmlResponse = EntityUtils.toString(entity);

            htmlResponse = Html.fromHtml(htmlResponse).toString();

            if(htmlResponse.equals("SUCCESS")){
                Log.i("MyActivity", "SUCCESS");
                authenticateLink = link;
                link = link.replace("authenticate.php","database.php");
                setContentView(R.layout.fragment_main);
                getDatabaseNames(link);
            }
            else{
                Log.i("MyActivity", "FAIL");
                buttonAnimate();
            }

        }
        catch(Exception e){}
    }

    public void getDatabaseNames(String link){
        try{
            HttpClient client = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            request.setHeader("Content-type=application/json", "charset=utf-8");
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String htmlResponse = EntityUtils.toString(entity);

            htmlResponse = Html.fromHtml(htmlResponse).toString();

            JSONArray jObject = new JSONArray(htmlResponse);
            ArrayList<String> databases = new ArrayList<String>();
            for(int i = 0; i<jObject.length();i++){
                databases.add(jObject.getJSONObject(i).getString("name"));
            }
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.fragment_list, databases);

            ListView lv = (ListView)this.findViewById(R.id.listView);
            lv.setAdapter( listAdapter );
            lv.setOnItemClickListener(new ListClickHandler(this.findViewById(R.id.listName),"DATABASE"));

        }
        catch(Exception e){}
    }

    public void getTableNames(String link){
        Log.i("MyActivity",link);
        try{
            HttpClient client = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            request.setHeader("Content-type=application/json", "charset=utf-8");
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String htmlResponse = EntityUtils.toString(entity);

            htmlResponse = Html.fromHtml(htmlResponse).toString();

            JSONArray jObject = new JSONArray(htmlResponse);
            ArrayList<String> databases = new ArrayList<String>();
            for(int i = 0; i<jObject.length();i++){
                databases.add(jObject.getJSONObject(i).getString("name"));
                Log.i("MyActivity", jObject.getJSONObject(i).getString("name"));
            }
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.fragment_list, databases);

            ListView lv = (ListView)this.findViewById(R.id.listView);
            lv.setAdapter( listAdapter );
            lv.setOnItemClickListener(new ListClickHandler(this.findViewById(R.id.listName),"TABLE"));

        }
        catch(Exception e){}
    }

    public void getColNames(String link){
        Log.i("MyActivity",link);
        try{
            HttpClient client = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            request.setHeader("Content-type=application/json", "charset=utf-8");
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String htmlResponse = EntityUtils.toString(entity);

            htmlResponse = Html.fromHtml(htmlResponse).toString();

            colsJSON = new JSONArray(htmlResponse);
            ArrayList<String> cols = new ArrayList<String>();
            for(int i = 0; i<colsJSON.length();i++){
                cols.add(colsJSON.getJSONObject(i).getString("name"));
                Log.i("MyActivity", colsJSON.getJSONObject(i).getString("name"));
            }

            ArrayList<String> actions = new ArrayList<String>();
            actions.add("Check Columns");
            actions.add("Insert");
            actions.add("Delete");
            actions.add("Search");
            actions.add("Update");
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.fragment_list, actions);
            ListView lv = (ListView)this.findViewById(R.id.listView);
            lv.setAdapter( listAdapter );
            lv.setOnItemClickListener(new ListClickHandler(this.findViewById(R.id.listName),"ACTION"));

            /*ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.fragment_list, cols);
            ListView lv = (ListView)this.findViewById(R.id.listView);
            lv.setAdapter( listAdapter );
            lv.setOnItemClickListener(new ListClickHandler(this.findViewById(R.id.listName),"TABLE"));*/

        }
        catch(Exception e){}
    }

    public void buttonAnimate(){
        if(running==false){
            running = true;

            thread = new Thread() {
                @Override
                public void run() {
                    boolean start = true;
                    float time = 0;
                    long startTime = System.nanoTime();
                    boolean latch = false;
                    Log.i("MyActivity", "RUN");
                    final Button btn = (Button) findViewById(R.id.connectButton);
                    while(start){
                        if(time == 0f){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ColorDrawable[] color = {(ColorDrawable)old, colorError};
                                    TransitionDrawable trans = new TransitionDrawable(color);
                                    btn.setBackgroundDrawable(trans);
                                    trans.startTransition(500);

                                }
                            });
                            Log.i("MyActivity", "0");

                        }
                        if(time >=0.6f && !latch){
                            latch = true;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ColorDrawable[] color = {colorError, (ColorDrawable)old};
                                    TransitionDrawable trans = new TransitionDrawable(color);
                                    btn.setBackgroundDrawable(trans);
                                    trans.startTransition(500);

                                }
                            });
                            Log.i("MyActivity", "1");
                        }
                        if(time >=1.2f){
                            running = false;
                            start = false;
                            Log.i("MyActivity", "2");
                            thread.interrupt();
                        }
                        time = System.nanoTime()/1000000000.0f-startTime/1000000000.0f;
                    }
                }
            };

            thread.start();
        }
    }


    public boolean isInteger(String check){
        try{
            int integer = Integer.parseInt(check);
            return true;
        }
        catch(Exception e){return false;}
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

        }

        return super.onKeyDown(keyCode, event);
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener{
        View viewAll = null;
        String type;
        public ListClickHandler(View viewAll, String type){
            this.viewAll = viewAll;
            this.type = type;
        }
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            if(type.equals("DATABASE")){
                //TextView listText = (TextView) view.findViewById(R.id.listView);
                String text = ((TextView) view).getText().toString();
                database = text;
                String link = databaseTableLink;
                link = link.replace("[WEBMARKER]",web);
                if(port!=-1){
                    link = link.replace("[PORTMARKER]",":"+port);
                }
                else{
                    link = link.replace("[PORTMARKER]","");
                }
                link = link.replace("[FOLDERMARKER]",folder);

                link = link.replace("[SERVERMARKER]",server);
                link = link.replace("[USERNAMEMARKER]",name);
                link = link.replace("[PASSWORDMARKER]",password);
                link = link.replace("[DATABASENAME]",text);

                getTableNames(link);

                TextView listNameText = (TextView) viewAll;
                listNameText.setText("Table Names");
            }
            if(type.equals("TABLE")){
                //TextView listText = (TextView) view.findViewById(R.id.listView);
                String text = ((TextView) view).getText().toString();

                String link = tableColLink;
                link = link.replace("[WEBMARKER]",web);
                if(port!=-1){
                    link = link.replace("[PORTMARKER]",":"+port);
                }
                else{
                    link = link.replace("[PORTMARKER]","");
                }
                link = link.replace("[FOLDERMARKER]",folder);

                link = link.replace("[SERVERMARKER]",server);
                link = link.replace("[USERNAMEMARKER]",name);
                link = link.replace("[PASSWORDMARKER]",password);
                link = link.replace("[DATABASENAME]",database);
                link = link.replace("[TABLENAME]",text);
                getColNames(link);

                TextView listNameText = (TextView) viewAll;
                listNameText.setText("Actions");
            }
            if(type.equals("ACTION")){
                //TextView listText = (TextView) view.findViewById(R.id.listView);
                String text = ((TextView) view).getText().toString();

                if(text.equals("Check Columns")){
                    changeFragmentToCol();

                }

                TextView listNameText = (TextView) viewAll;
                listNameText.setText("Column Names");
            }
        }
    }


    public void changeFragmentToCol(){
        try{
            ArrayList<String> cols = new ArrayList<String>();
            for(int i = 0; i<colsJSON.length();i++){
                cols.add(colsJSON.getJSONObject(i).getString("name"));
                Log.i("MyActivity", colsJSON.getJSONObject(i).getString("name"));
            }
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,R.layout.fragment_list, cols);
            ListView lv = (ListView)this.findViewById(R.id.listView);
            lv.setAdapter( listAdapter );
            lv.setOnItemClickListener(new ListClickHandler(listView,"COL"));
        }
        catch(Exception e){}
    }

}
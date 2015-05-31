package com.example.pabla.andoirddatabaseaccess2;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * LayoutLoader class handles all layout changes.
 * @author INDERPREET PABLA
 */
public class LayoutLoader
{
    private Activity activity;

    ListView list;
    MyListAdapter customList;

    /**
     * Constructor  requires activity to be able to access xml layouts.
     * @param activity Activity active at the moment in main class.
     */
    public LayoutLoader(Activity activity)
    {
        this.activity = activity;
    }

    /**
     * Load R.layout.activity_main
     */
    public void loadMain()
    {
        activity.setContentView(R.layout.activity_main);
    }

    /**
     * Load R.layout.list. Also sets up ArrayAdapter for individual component items of R.id.list.
     * @param data Data to put into ArrayAdapter which will an item of the list view R.id.list.
     * @param title Title set for the text view R.id.listName.
     */
    public void loadTextList(ArrayList<String> data, String title)
    {
        activity.setContentView(R.layout.list);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(activity, R.layout.list_item, data);
        list = (ListView)activity.findViewById(R.id.list);
        list.setAdapter(listAdapter);

        TextView titleText = (TextView) activity.findViewById(R.id.listName);
        titleText.setText(title);
    }

    /**
     * Load R.layout.list_input. Also sets up MyListAdapter for individual component items of R.id.list_input.
     * @param data Data to put into MyListAdapter which will an item of the list view R.id.list_input.
     * @param title Title set for the text view R.id.listName_input.
     */
    public void loadInputList(ArrayList<String> data, String title)
    {
        activity.setContentView(R.layout.list_input);

        customList = new MyListAdapter(activity,data);
        ListView listView = (ListView) (ListView)activity.findViewById(R.id.list_input);
        listView.setAdapter(customList);

        TextView titleText = (TextView) activity.findViewById(R.id.listName_input);
        titleText.setText(title);
    }

    /**
     * Load R.layout.list_search. Also sets up ArrayAdapter for individual component items of R.id.list_search.
     * @param tableData ArrayList of table data.
     * @param columnNames ArrayList of column names.
     * @param title title Title set for the text view R.id.listName_search.
     */
    public void loadDataList(ArrayList<String> tableData, ArrayList<String> columnNames, String title)
    {
        activity.setContentView(R.layout.list_search);

        ArrayList<String> combinedData = new ArrayList<String>();

        int count  = 0;
        for(int i = 0; i < tableData.size(); i++)
        {
            int ratio = columnNames.size();
            if(i%ratio == 0)
            {
                count++;
                combinedData.add("---- DATA: " + count + " ----");
            }
            combinedData.add(tableData.get(i)) ;
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(activity, R.layout.list_item, combinedData);
        list = (ListView)activity.findViewById(R.id.list_search);
        list.setAdapter(listAdapter);

        TextView titleText = (TextView) activity.findViewById(R.id.listName_search);
        titleText.setText(title);
    }

    /**
     * Gets string array arrTemp from customList.
     * @return Array of strings from custom lists input fields.
     */
    public String[] getInputTexts()
    {
        return customList.getTextList();
    }

}

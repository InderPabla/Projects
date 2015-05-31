package com.example.pabla.andoirddatabaseaccess2;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PABLA on 27/05/2015.
 */
public class LayoutLoader
{
    Activity activity;
    ListView list;

    MyListAdapter customList;
    public LayoutLoader(Activity activity)
    {
        this.activity = activity;
    }

    public void loadMain()
    {
        activity.setContentView(R.layout.activity_main);
    }

    public void loadTextList(ArrayList<String> data, String title)
    {
        activity.setContentView(R.layout.list);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(activity, R.layout.list_item, data);
        list = (ListView)activity.findViewById(R.id.list);
        list.setAdapter(listAdapter);

        TextView titleText = (TextView) activity.findViewById(R.id.listName);
        titleText.setText(title);
    }

    public void loadInputList(ArrayList<String> data, String title)
    {
        activity.setContentView(R.layout.list_input);

        customList = new MyListAdapter(activity,data);
        ListView listView = (ListView) (ListView)activity.findViewById(R.id.list_input);
        listView.setAdapter(customList);

        TextView titleText = (TextView) activity.findViewById(R.id.listName_input);
        titleText.setText(title);
    }

    public void loadDataList(ArrayList<String> tableData, ArrayList<String> columnNames, String title)
    {
        activity.setContentView(R.layout.list_search);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(activity, R.layout.list_item, tableData);
        list = (ListView)activity.findViewById(R.id.list_search);
        list.setAdapter(listAdapter);

        TextView titleText = (TextView) activity.findViewById(R.id.listName_search);
        titleText.setText(title);
    }

    public String[] getInputTexts()
    {
        return customList.getTextList();
    }
}

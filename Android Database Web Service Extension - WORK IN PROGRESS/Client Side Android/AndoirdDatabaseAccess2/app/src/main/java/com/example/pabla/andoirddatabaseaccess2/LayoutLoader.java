package com.example.pabla.andoirddatabaseaccess2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by PABLA on 27/05/2015.
 */
public class LayoutLoader
{
    Activity activity;
    ListView list;
    public LayoutLoader(Activity activity)
    {
        this.activity = activity;
    }

    public void loadMain()
    {
        activity.setContentView(R.layout.activity_main);
    }

    public void loadList(ArrayList<String> data)
    {
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(activity, R.layout.list_item, data);
        activity.setContentView(R.layout.list);
        list = (ListView)activity.findViewById(R.id.list);
        list.setAdapter(listAdapter);
    }
}

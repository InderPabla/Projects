package com.example.pabla.andoirddatabaseaccess2;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PABLA on 30/05/2015.
 */
public class MyListAdapter extends BaseAdapter
{
    Activity activity;
    private String[] arrText;
    private  String[] arrTemp;

    public MyListAdapter(Activity activity,ArrayList<String> data)
    {
        this.activity = activity;
        arrText = new String[data.size()];
        for(int i = 0;i<data.size();i++)
        {
            arrText[i] = data.get(i);
        }
        arrTemp = new String[arrText.length];
    }

    @Override
    public int getCount()
    {
        if(arrText != null && arrText.length != 0)
        {
            return arrText.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        return arrText[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        final ViewHolder holder;

        if (convertView == null)
        {

            holder = new ViewHolder();
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_input_item, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.editText1 = (EditText) convertView.findViewById(R.id.editText1);

            convertView.setTag(holder);

        }
        else
        {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.ref = position;

        holder.textView1.setText(arrText[position]);
        holder.editText1.setText(arrTemp[position]);
        holder.editText1.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

            @Override
            public void afterTextChanged(Editable arg0)
            {
                arrTemp[holder.ref] = arg0.toString();
            }
        });

        return convertView;
    }

    public String[] getTextList()
    {
        return arrTemp;
    }

    private class ViewHolder
    {
        TextView textView1;
        EditText editText1;
        int ref;
    }
}
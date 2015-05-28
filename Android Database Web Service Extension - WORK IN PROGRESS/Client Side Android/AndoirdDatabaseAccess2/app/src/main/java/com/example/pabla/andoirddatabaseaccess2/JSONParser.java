package com.example.pabla.andoirddatabaseaccess2;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by PABLA on 27/05/2015.
 */
public class JSONParser
{
    public static final int DATABASE_PARSE_TAG = 1;
    public static final int TABLE_PARSE_TAG = 2;
    public static final int COLUMN_PARSE_TAG = 3;

    private final String DATABASE_JSON_TAG = "DATABASE";
    private final String TABLE_JSON_TAG = "TABLE";
    private final String COLUMN_JSON_TAG = "COLUMN";

    ArrayList<String> data = new ArrayList<String>();

    public JSONParser(String JSONString, int tag)
    {
        String jsonTag = "";

        if(tag == DATABASE_PARSE_TAG)
        {
            jsonTag = DATABASE_JSON_TAG;
        }

        if(tag == TABLE_PARSE_TAG)
        {
            jsonTag = TABLE_JSON_TAG;
        }

        try {

            JSONArray jsonArray = new JSONArray(JSONString);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                data.add(jsonArray.getJSONObject(i).getString(jsonTag));
            }
        }
        catch(Exception error)
        {
            error.getMessage();
        }
    }

}

package com.example.pabla.andoirddatabaseaccess2;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * JSONParser class parses JSON data given by a string into its appropriate states.
 * @author INDERPREET PABLA
 */
public class JSONParser
{
    public static final int DATABASE_PARSE_TAG = 1;
    public static final int TABLE_PARSE_TAG = 2;
    public static final int COLUMN_PARSE_TAG = 3;
    public static final int DATA_PARSE_TAG = 4;

    private final String DATABASE_JSON_TAG = "DATABASE";
    private final String TABLE_JSON_TAG = "TABLE";
    private final String COLUMN_JSON_TAG = "COLUMN";
    private final String DATA_JSON_TAG = "DATA";

    private final String COLUMN_NAME_JSON_TAG = "COLUMN_NAME";
    private final String COLUMN_TYPE_JSON_TAG = "COLUMN_TYPE";

    private final String NAME_JSON_TAG = "NAME";
    private final String TYPE_JSON_TAG = "TYPE";

    ArrayList<String> data1 = new ArrayList<String>();
    ArrayList<String> data2 = new ArrayList<String>();
    ArrayList<String> combined = new ArrayList<String>();

    /**
     * Constructor parsers the JSON string given with the given tag. There are 4 ways data can be
     * parsed. With DATABASE_PARSE_TAG, TABLE_PARSE_TAG, COLUMN_PARSE_TAG or DATA_PARSE_TAG.
     * @param JSONString The JSON string to be parsed.
     * @param tag String will be parsed according to the given tag.
     */
    public JSONParser(String JSONString, int tag)
    {
        //Parse database names information from the JSON string and put them into data1.
        if(tag == DATABASE_PARSE_TAG)
        {
            try
            {
                JSONArray jsonArray = new JSONArray(JSONString);
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    data1.add(jsonArray.getJSONObject(i).getString(DATABASE_JSON_TAG));
                }
            }
            catch(Exception error)
            {
                error.getMessage();
            }
        }

        //Parse table names information from the JSON string and put them into data1.
        if(tag == TABLE_PARSE_TAG)
        {
            try
            {
                JSONArray jsonArray = new JSONArray(JSONString);
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    data1.add(jsonArray.getJSONObject(i).getString(TABLE_JSON_TAG));
                }
            }
            catch(Exception error)
            {
                error.getMessage();
            }
        }

        /**
         * Parse column information from the JSON string and put column names into data1, column
         * types into data2, and column names and types concatenated information into combined.
         */
        if(tag == COLUMN_PARSE_TAG)
        {
            try
            {
                JSONArray jsonArray = new JSONArray(JSONString);
                JSONObject jsonColumnNames = jsonArray.getJSONObject(0);
                JSONObject jsonColumnTypes = jsonArray.getJSONObject(1);

                JSONArray jsonColumnNamesArray = jsonColumnNames.getJSONArray(COLUMN_NAME_JSON_TAG);
                JSONArray jsonColumnTypesArray = jsonColumnTypes.getJSONArray(COLUMN_TYPE_JSON_TAG);

                for (int i = 0; i < jsonColumnNamesArray.length(); i++)
                {
                    String dataExtracted = jsonColumnNamesArray.getJSONObject(i).getString(NAME_JSON_TAG)+" - "+jsonColumnTypesArray.getJSONObject(i).getString(TYPE_JSON_TAG);
                    data1.add(jsonColumnNamesArray.getJSONObject(i).getString(NAME_JSON_TAG));
                    data2.add(jsonColumnTypesArray.getJSONObject(i).getString(TYPE_JSON_TAG));
                    combined.add(dataExtracted);
                }
            } catch(Exception error)
            {
                error.getMessage();
            }
        }

        //Parse data that was just searched into data1.
        if(tag == DATA_PARSE_TAG)
        {
            try
            {
                JSONArray jsonArray = new JSONArray(JSONString);

                for(int i = 0;i<jsonArray.length();i++)
                {
                    JSONObject column = jsonArray.getJSONObject(i);
                    JSONArray columnArray = column.getJSONArray(COLUMN_JSON_TAG);

                    for(int j = 0;j<columnArray.length();j++)
                    {
                        String data = columnArray.getJSONObject(j).getString(DATA_JSON_TAG);
                        data1.add(data);

                    }
                }
            } catch(Exception error)
            {
                error.getMessage();
            }
        }
    }

}

package com.example.pabla.andoirddatabaseaccess2;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

/**
 * Created by PABLA on 27/05/2015.
 */
public class Connector
{

    String connectorLink;
    String response = "";

    public Connector(String link)
    {
        connectorLink = link;
        //response = connect();
    }

    /**
     * @deprecated Connectivity components DefaultHttpClient and HttpEntity are deprecated.
     */
    public String connect()
    {
        String responseEntity;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(connectorLink));
            request.setHeader("Content-type=application/json", "charset=utf-8");
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseEntity = EntityUtils.toString(entity);
            return responseEntity;
        }
        catch(Exception error)
        {
            return error.getMessage();
        }

    }
}

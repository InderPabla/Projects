package com.example.pabla.andoirddatabaseaccess2;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

/**
 * Connector class can execute a link and get data echoed by the server.
 * @author INDERPREET PABLA
 */
public class Connector
{

    private String connectorLink;
    private String response = "";

    /**
     * Constructor initializes given link to connectorLink.
     * @param link The link to execute.
     */
    public Connector(String link)
    {
        connectorLink = link;
        //response = connect(); //commented out because the user should have the choice when to
                                //connect and when not to connect.
    }


    /**
     * Connects to the link and gets response returned by server.
     * @deprecated Connectivity components DefaultHttpClient and HttpEntity are deprecated.
     * @return Returns the text that was returned by the php files after their executions.
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

    /**
     * Returns the response string.
     * @return Returns the text that was returned by the php files after their executions.
     */
    public String getResponse()
    {
        return response;
    }
}

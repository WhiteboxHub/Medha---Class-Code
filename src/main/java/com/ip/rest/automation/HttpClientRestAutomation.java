package com.ip.rest.automation;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.junit.Assert;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class HttpClientRestAutomation 
{
	@Test
    public void testRest() throws ClientProtocolException, IOException
    {
		HttpClient http = HttpClientBuilder.create().build();		
		//HttpParams- build request params
    	HttpGet req= new HttpGet("http://maps.googleapis.com/maps/api/"
    			+ "geocode/json?address=Dublin&key=AIzaSyAjPkhQMPZs2G1Iwx5FAe69KVoDKpoURoQ");
    	req.addHeader("content-type", "application/json");
    	
    	
    	HttpResponse response = http.execute(req);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("status code is:"+statusCode);
        Assert.assertTrue(200==statusCode);
    }
}

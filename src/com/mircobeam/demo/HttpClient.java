package com.mircobeam.demo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class HttpClient 
{
	
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	static 
	{
		client.setTimeout(15000);
	}
	
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) 
	{
	    client.get(url, params, responseHandler);
	}
	
	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) 
    {
      client.post(url, params, responseHandler);
    }
	  
	public static AsyncHttpClient getClient() 
	{
	  return client;
	}
	  
}

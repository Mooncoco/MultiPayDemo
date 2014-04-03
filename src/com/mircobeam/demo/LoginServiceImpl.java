package com.mircobeam.demo;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class LoginServiceImpl
{
	public void login(RequestParams loginParams, final LoginResultListener listener)
	{	
		HttpClient.post("http://payment.hiigame.com:18000/new/gateway/visitor/login", 
				loginParams, new JsonHttpResponseHandler()
		{
			@Override
			public void onSuccess(int statusCode, Header[] headers, 
					JSONObject response)
			{
				super.onSuccess(statusCode, headers, response);
				listener.onLoginSuccess(response.toString());
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, 
					Throwable throwable, JSONObject errorResponse)
			{
				super.onFailure(statusCode, headers, throwable, errorResponse);
				listener.onLoginFailure("Error !!!");
			}

			@Override
			public void onFinish()
			{
				super.onFinish();
				listener.onLoginFinish();
			}

			@Override
			public void onStart()
			{
				super.onStart();
				listener.onLoginStart();
			}
		});
	}
}

package com.mircobeam.demo;

public interface LoginResultListener
{
	public void onLoginStart();
	public void onLoginFinish();
	public void onLoginSuccess(String result);
	public void onLoginFailure(String result);
}

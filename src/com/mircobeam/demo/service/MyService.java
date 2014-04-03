package com.mircobeam.demo.service;

import com.mircobeam.demo.MainActivity;
import com.mircobeam.demo.R;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service
{
	private String TAG = "MyService";
	
	
	private MyBinder mBinder = new MyBinder();
	
	@Override
	public IBinder onBind(Intent intent)
	{
		Log.d(TAG, "onBind() executed");  
		return mBinder;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
//		Notification notification = new Notification(R.drawable.ic_launcher, "这是一个通知", System.currentTimeMillis());
//		Intent notificationIntent = new Intent(this, MainActivity.class);
//		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//		notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容", pendingIntent);
//		startForeground(1, notification);
		
		Log.d(TAG, "onCreate() executed"); 
		
		try
		{
			Thread.sleep(100000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.d(TAG, "onStartCommand() executed");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG, "onDestroy() executed"); 
	}

	@Override
	public boolean onUnbind(Intent intent)
	{
		return super.onUnbind(intent);
	}
	
	public class MyBinder extends Binder
	{
		public void startDownload()
		{
			Log.d(TAG, "start download....");
		}
	}
}

package com.mircobeam.multipaydemo;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	TextView textView;
	Activity thisActivity;
	private static boolean isExit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		thisActivity = this;
		
		textView = (TextView) findViewById(R.id.text_multipay);
		textView.setOnClickListener(onClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	
	private OnClickListener onClickListener=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			if(v == textView)
			{
				startActivity(new Intent(thisActivity, PayCenterActivity.class));
			}
		}
		
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) 
		{
			onExit();
            return false;
        }
		return super.onKeyDown(keyCode, event);
	}
	
	Handler mHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			isExit = false;
		}
		
	};

    private void onExit() 
    {
        if (!isExit) 
        {
            isExit = true;
            Toast.makeText(getApplicationContext(), R.string.exit_toast, Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } 
        else 
        {
            finish();
            System.exit(0);
        }
    }

}

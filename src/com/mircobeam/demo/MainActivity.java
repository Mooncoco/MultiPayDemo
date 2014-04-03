package com.mircobeam.demo;


import com.loopj.android.http.RequestParams;
import com.mircobeam.demo.R;
import com.mircobeam.demo.db.DBManager;
import com.mircobeam.demo.service.MyService;
import com.mircobeam.demo.service.MyService.MyBinder;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	TextView textView;
	TextView textView2;
	
	TextView textViewInsert;
	TextView textViewUpdate;
	TextView textViewDelete;
	
	TextView textViewStartService;
	TextView textViewStopService;
	
	TextView textViewListview;
	
	TextView textViewSoundRecord;
	
	private MyService.MyBinder mBinder;
	
	private boolean isRegistered = false;
	
	private ServiceConnection connetcion = new ServiceConnection()
	{
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			mBinder = (MyBinder) service;
			mBinder.startDownload();
		}
	};
	
	DBManager dbManager = null;
	
	//TextView textOutput;
	
	//ScrollView mScrollView;
	
	int count = 0;
	
	Activity thisActivity;
	private ProgressDialog progressDialog;
	LoginServiceImpl loginService = null;
	private static boolean isExit = false;
	
	StringBuilder str = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
				
		thisActivity = this;
		
		textView = (TextView) findViewById(R.id.text_multipay);
		textView.setOnClickListener(onClickListener);
		
		textView2 = (TextView) findViewById(R.id.text_login);
		textView2.setOnClickListener(onClickListener);
		
		//textOutput = (TextView) findViewById(R.id.text_output);
		
		//mScrollView = (ScrollView) findViewById(R.id.sv_show);
		
		textViewInsert = (TextView) findViewById(R.id.text_insert);
		textViewInsert.setOnClickListener(onClickListener);
		
		textViewUpdate = (TextView) findViewById(R.id.text_update);
		textViewUpdate.setOnClickListener(onClickListener);
		
		textViewDelete = (TextView) findViewById(R.id.text_delete);
		textViewDelete.setOnClickListener(onClickListener);
		
		textViewStartService = (TextView) findViewById(R.id.text_start_service);
		textViewStartService.setOnClickListener(onClickListener);
		
		textViewStopService = (TextView) findViewById(R.id.text_stop_service);
		textViewStopService.setOnClickListener(onClickListener);
		
		textViewListview = (TextView) findViewById(R.id.text_open_listview);
		textViewListview.setOnClickListener(onClickListener);
		
		textViewSoundRecord = (TextView) findViewById(R.id.text_sound_record);
		textViewSoundRecord.setOnClickListener(onClickListener);
//		textOutput = (TextView) findViewById(R.id.text_output);
//		textOutput.setBackgroundColor(Color.BLACK);
//		textOutput.setTextColor(Color.parseColor("#FFFFFF"));
		//textOutput.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		dbManager = new DBManager(this);
		
		initProcessDialog(MainActivity.this);
	}

	private void initProcessDialog(Context ctx)
	{
		progressDialog = new ProgressDialog(ctx);
		progressDialog.setMessage(getResources().getString(R.string.login_process_dialog_msg));
		progressDialog.setProgress(100);
		progressDialog.setCancelable(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	
	/**
	 * 
	 */
	private OnClickListener onClickListener=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			if(v == textView)
			{
				startActivity(new Intent(thisActivity, PayCenterActivity.class));
			}
			else if(v == textView2)
			{
				loginService = new LoginServiceImpl();
				RequestParams loginParams = new RequestParams();
				loginParams.put("imei", "test");
				loginParams.put("name", "test");
				loginParams.put("pn", 	"com.izhangxin.zjh.android");
				
				loginService.login(loginParams, new LoginResultListener()
				{
					@Override
					public void onLoginSuccess(String result)
					{
						System.out.println(result);
					}

					@Override
					public void onLoginFailure(String result)
					{
						System.out.println(result);
					}

					@Override
					public void onLoginStart()
					{
						progressDialog.show();
						Message msg = new Message();
						msg.what = 10001;
						if(count>0)
						{
							msg.obj = "\n" + "start login ...." + String.valueOf(count);							
						}
						else
						{
							msg.obj = "start login ...." + String.valueOf(count);
						}
						count++;
						mHandler.sendMessage(msg);
					}
					
					@Override
					public void onLoginFinish()
					{
						progressDialog.dismiss();
						Toast.makeText(MainActivity.this, "Login success !!!", Toast.LENGTH_SHORT).show();
					}
				});
			}
			else if(v == textViewInsert)
			{
				dbManager.insert();
			}
			else if(v == textViewUpdate)
			{
				dbManager.update();
			}
			else if(v == textViewDelete)
			{
				dbManager.delete();
			}
			else if(v == textViewStartService)
			{
				isRegistered = true;
				
				Intent startIntent = new Intent(MainActivity.this, MyService.class);
				startService(startIntent);
				bindService(startIntent, connetcion, BIND_AUTO_CREATE);
				
			}
			else if(v == textViewStopService)
			{
				if(!isRegistered)
				{
					Toast.makeText(MainActivity.this, "服务未注册", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Intent stopIntent = new Intent(MainActivity.this, MyService.class);  
		            stopService(stopIntent);
					unbindService(connetcion);
					isRegistered = false;
				}
			}
			else if(v == textViewListview)
			{
				Intent intent = new Intent(MainActivity.this, MyListViewActivity.class);
				startActivity(intent);
			}
			else if(v == textViewSoundRecord)
			{
				Intent intent = new Intent(MainActivity.this, MySoundRecord.class);
				startActivity(intent);
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
			if(msg.what == 10001)
			{
				//scroll2Bottom(mScrollView, textOutput);
				str.append(msg.obj);
				//textOutput.setText(str);
			}
			else
			{
				isExit = false;
			}
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
    
    @Override
	protected void onDestroy()
	{
		super.onDestroy();
		dbManager.closeDB();
	}

	public static void scroll2Bottom(final ScrollView scroll, final View inner)
	{
		Handler handler = new Handler();
		handler.post(new Runnable()
		{
			@Override
			public void run()
			{
				if (scroll == null || inner == null)
				{
					return;
				}
				
				// 内层高度超过外层
				int offset = inner.getMeasuredHeight() - scroll.getMeasuredHeight();
				if (offset < 0)
				{
					System.out.println("定位...");
					offset = 0;
				}
				scroll.scrollTo(0, offset);
			}
		});
	}

}

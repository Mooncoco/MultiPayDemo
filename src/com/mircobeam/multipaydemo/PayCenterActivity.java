package com.mircobeam.multipaydemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class PayCenterActivity extends Activity
{
	private TextView textViewClose;
	private GridView gridViewChannel;
	private Button submitButton;
	private TextView textViewName;
	private TextView textViewPrice;
	
	private ProgressDialog progressDialog;
	
	private int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		
		setContentView(R.layout.pay_center_main_layout);
		
		/* 右上角关闭按钮 */
		textViewClose = (TextView) findViewById(getResources().getIdentifier("close", "id", getPackageName()));
		textViewClose.setOnClickListener(onClickListener);
		
		/* 支付渠道列表 */
		gridViewChannel = (GridView) findViewById(getResources().getIdentifier("pay_center_channel_gridview", "id", getPackageName()));
		gridViewChannel.setAdapter(new ChannelAdapter(this));
		gridViewChannel.setOnItemClickListener(onItemClickListener);
//		gridViewChannel.requestFocusFromTouch();  
//		gridViewChannel.setSelection(0);
		gridViewChannel.performClick();
		
		
		/* 确认按钮 */
		submitButton = (Button) findViewById(getResources().getIdentifier("two_btn", "id", getPackageName()));
		submitButton.setOnClickListener(onClickListener);
		
		/* 商品描述 */
		textViewName = (TextView) findViewById(getResources().getIdentifier("one_text2", "id", getPackageName()));
		textViewName.setText("100000金币");
		
		/* 商品价格 */
		textViewPrice = (TextView) findViewById(getResources().getIdentifier("one_text4", "id", getPackageName()));
		textViewPrice.setText("￥10");
		
		initProcessDialog(PayCenterActivity.this);
		
	}
	
	/**
	 * 初始化渠道列表
	 */
	private void initMultipayChanel()
	{
		
	}
	
	private OnClickListener onClickListener = new OnClickListener()
	{	
		@Override
		public void onClick(View v)
		{
			if(v == textViewClose)
			{
				finish();
				handler.sendEmptyMessage(-1);
			}
			else if(v == submitButton)
			{
				progressDialog.show();
				new Thread()
				{
					public void run() 
					{
						try
						{
							while (count <= 100)
							{
								progressDialog.setProgress(count++);
								Thread.sleep(100);
							}
							
							handler.sendEmptyMessage(0);
							
						} catch (InterruptedException e)
						{
							progressDialog.cancel();
						} 
					};
				}.start();
			}
		}
	};
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener()
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
		{
			System.out.println("!!!!!!!!!!!!!");
		}
	};
	
	private void initProcessDialog(Context context)
	{
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(getResources().getString(R.string.pay_center_process_dialog_msg));
		progressDialog.setProgress(100);
		progressDialog.setCancelable(false);
	}
	
	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			
			if(msg.what == 0)
			{
				finish();
				progressDialog.dismiss();
				count = 0;
			}
			else if(msg.what == -1)
			{
				System.out.println("pay cancel !!!");
			}
		};
	};
	
}

package com.mircobeam.multipaydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class PayCenterActivity extends Activity
{
	private TextView textViewClose;
	private GridView gridViewChannel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		
		setContentView(R.layout.pay_center_main_layout);
		
		textViewClose = (TextView) findViewById(R.id.close);
		textViewClose.setOnClickListener(onClickListener);
		
		gridViewChannel = (GridView) findViewById(R.id.pay_center_channel_gridview);
		gridViewChannel.setAdapter(new ChannelAdapter(this));
		gridViewChannel.setOnItemClickListener(onItemClickListener);
	}
	
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
				
			}
		}
	};
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
		{
			System.out.println("@@@@@@@");
		}
	};
	
}

package com.mircobeam.multipaydemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ChannelAdapter extends BaseAdapter
{
	private Context mContext;
	
	public ChannelAdapter(Context context)
	{
		mContext = context;
	}

	@Override
	public int getCount()
	{
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = View.inflate(mContext, R.layout.pay_ceter_channel_layout, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.itemImage);
		imageView.setImageResource(mThumbIds[position]);
		
		return view;
	}
	
    private Integer[] mThumbIds = {R.drawable.inc_start_001, R.drawable.inc_start_002,
    								R.drawable.inc_start_003, R.drawable.inc_start_004,
    								R.drawable.inc_start_005, R.drawable.inc_start_006};

}

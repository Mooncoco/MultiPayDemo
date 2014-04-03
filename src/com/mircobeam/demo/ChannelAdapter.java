package com.mircobeam.demo;

import java.util.ArrayList;
import java.util.List;

import com.mircobeam.demo.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ChannelAdapter extends BaseAdapter
{
	private Context mContext;
	private List<Integer> mList;
	
	public ChannelAdapter(Context context)
	{
		mContext = context;
	}

	@Override
	public int getCount()
	{
		parseArrayToList();
		return mList.size();
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
		imageView.setImageResource(mList.get(position));
		
		return view;
	}
	
	private void parseArrayToList()
	{
		String[] mChannels = mContext.getResources().getStringArray(R.array.channels);
		
		mList = new ArrayList<Integer>();
		
		for(int i = 0; i<mChannels.length; i++)
		{
			
			mList.add(mContext.getResources().getIdentifier(mChannels[i], "drawable", mContext.getPackageName()));
		}
	}
}

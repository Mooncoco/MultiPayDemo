package com.mircobeam.demo;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.id;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyListViewActivity extends Activity
{
	private ListView listView;
	
	private ArrayList<HashMap<String, String>> list;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		listView = new ListView(this);
		list = getData();
		//listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
		MyAdapter adapter = new MyAdapter(this);
		listView.setAdapter(adapter	);
		
		setContentView(listView);
	}
	
//	private List<String> getData()
//	{
//		List<String> data = new ArrayList<String>();
//		data.add("测试数据1");
//        data.add("测试数据2");
//        data.add("测试数据3");
//        data.add("测试数据4");
//        
//        return data;
//	}
	
	private ArrayList<HashMap<String, String>> getData()
	{
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		for(int i=0; i<=2; i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("url", "");
			map.put("title", "this is title");
			map.put("content", "this is content");
			list.add(map);
		}
		
		return list;
	}
	
	private class MyAdapter extends BaseAdapter
	{
		
		private LayoutInflater mInflater;
		
		public MyAdapter(Context context) 
		{
		    this.mInflater = LayoutInflater.from(context);
		}        

		@Override
		public int getCount()
		{
			return list.size();
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
			convertView = mInflater.inflate(R.layout.listview_layout, null);
			ImageView img = (ImageView) convertView.findViewById(R.id.img);
			TextView title = (TextView) convertView.findViewById(R.id.title);
			TextView content = (TextView) convertView.findViewById(R.id.info);
			
			img.setImageResource(R.drawable.ic_launcher);
			title.setText(list.get(position).get("title").toString());
			content.setText(list.get(position).get("content").toString());

			return convertView;
		}
		
	}
}

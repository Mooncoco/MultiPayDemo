package com.mircobeam.demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager
{
	private SQLiteDatabase mDataBase = null;
	private static final String USER_TABLE = "user"; 
	
	public DBManager(Context ctx)
	{
		DBHelper dbHelper = new DBHelper(ctx, USER_TABLE, null, 1);
		mDataBase = dbHelper.getWritableDatabase();
	}
	
	public void insert()
	{
		ContentValues cv = new ContentValues();
		cv.put("name", "xiaoming");
		cv.put("address", "shanghai");
		mDataBase.insert(USER_TABLE, null, cv);
	}
	
	public int update()
	{
		int ret;
		ContentValues cv = new ContentValues();
		cv.put("name", "xiaoli");
		ret = mDataBase.update(USER_TABLE, cv, "id=?", new String[]{"1"});
		return ret;
	}
	
	public int delete()
	{
		int ret = mDataBase.delete(USER_TABLE, "id=? and name=?", new String[]{"1", "xiaoli"});
		return ret;
	}
	
	public void closeDB()
	{
		mDataBase.close();
	}
}

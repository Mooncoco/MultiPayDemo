package com.mircobeam.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{

	public DBHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("create table user(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name varchar(20), address TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}

	@Override
	public void onOpen(SQLiteDatabase db)
	{
		super.onOpen(db);
	}

	@Override
	public synchronized void close()
	{
		super.close();
	}

}

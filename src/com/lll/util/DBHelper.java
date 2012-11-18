package com.lll.util;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "pay_sdk.db";
	private static final String TBL_NAME = "LoginHistory";
	private static final String CREATE_TBL = " create table "
			+ " LoginHistory(id integer primary key autoincrement,username text,password text, desc text) ";
	
	private SQLiteDatabase db;
	public DBHelper(Context c) {
		super(c, DB_NAME, null, 2);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		db.execSQL(CREATE_TBL);
	}
	public void insert(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TBL_NAME, null, values);
		db.close();
	}
	public void update(ContentValues values,String colum, String value) {
		SQLiteDatabase db = getWritableDatabase();
		db.update(TBL_NAME, values,  colum+"=?", new String[] { value });
		db.close();
	}
	public Cursor query() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);
		return c;
	}
	public Cursor queryBy(String colum, String value) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TBL_NAME, null, colum+"=?", new String[]{value}, null, null, null);
		return c;
	}
	public void del(int id) {
		if (db == null)
			db = getWritableDatabase();
		db.delete(TBL_NAME, "id=?", new String[] { String.valueOf(id) });
	}
	public void delAll() {
		if (db == null)
			db = getWritableDatabase();
		db.delete(TBL_NAME, null, null);
	}
	public void close() {
		if (db != null)
			db.close();
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
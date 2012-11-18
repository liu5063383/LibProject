package com.lll.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lll.entity.User;
import com.lll.util.DBHelper;

public class UserDao {
	private DBHelper dbHelper;
	private User user = null;
	public UserDao(Context c) {
		this.dbHelper = new DBHelper(c);
	}
	public void updateUserLogin(User user) {
		ContentValues values = new ContentValues();
		values.put("username", user.getUsername());
		values.put("password", user.getPassword());
		values.put("desc", "lastLoginAccount");
		ContentValues values1 = new ContentValues();
		values1.put("desc", "");
		dbHelper.update(values1, "desc", "lastLoginAccount");
		if (dbHelper.queryBy("username", user.getUsername())
				.getCount() == 0) {
			dbHelper.insert(values);
		} else {
			dbHelper.update(values, "username", user.getUsername());
		}
	}
	public User queryLastLogin() {
		Cursor c = dbHelper.queryBy("desc","lastLoginAccount");
		if(c.getCount() > 0) {
			c.moveToFirst();
			String username = c.getString(c.getColumnIndex("username"));
			String password = c.getString(c.getColumnIndex("password"));
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
		}
		return user;
	}
	
}

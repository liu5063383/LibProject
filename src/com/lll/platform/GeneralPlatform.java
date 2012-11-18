package com.lll.platform;

import com.lll.activity.BaseActivity;
import com.lll.activity.LoginActivity;
import com.lll.activity.RegisterActivity;
import com.lll.dao.UserDao;
import com.lll.entity.User;
import com.lll.service.LoginService;
import com.lll.util.DBHelper;

import android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class GeneralPlatform extends BasePlatform {
	private static GeneralPlatform generalPlatform = null;

	protected GeneralPlatform() {

	}

	public static GeneralPlatform getInstance() {
		if (generalPlatform == null) {
			synchronized (GeneralPlatform.class) {
				generalPlatform = new GeneralPlatform();
			}
		}
		return generalPlatform;
	}
	public void cleanLoginHistory(Activity activity) {
		DBHelper dbHelper = new DBHelper(activity);
		dbHelper.delAll();
	}
	public void login(Activity activity,String appid, String partner) {
		
		//二次登录
		UserDao userDao = new UserDao(activity);
		User u = userDao.queryLastLogin();
		LoginService loginService = new LoginService(appid, partner);
		if(u != null) {
			Boolean flag = loginService.login(u);
			if (flag) {
				Toast.makeText(activity, "登陆成功",
						Toast.LENGTH_SHORT).show();
				// 返回游戏
//				Intent it = new Intent();
//				Bundle bundle = new Bundle();
//				bundle.putString("username", username);
//				bundle.putString("password", password);
//				it.putExtras(bundle);
//				activity.onActivityResult( BaseActivity.LOGIN_CODE,Activity.RESULT_OK, it);
			}else {
				Intent in = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("appid", appid);
				bundle.putString("partner", partner);
				in.putExtras(bundle);
				in.setClass(activity, LoginActivity.class);
				activity.startActivityForResult(in, BaseActivity.LOGIN_CODE);
			}
		}else {
			Intent in = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("appid", appid);
			bundle.putString("partner", partner);
			in.putExtras(bundle);
			in.setClass(activity, LoginActivity.class);
			activity.startActivityForResult(in, BaseActivity.LOGIN_CODE);
		}
	}
	public void register(Activity activity, String appid, String partner) {
		Intent in = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("appid", appid);
		bundle.putString("partner", partner);
		in.putExtras(bundle);
		in.setClass(activity, RegisterActivity.class);
		activity.startActivityForResult(in, BaseActivity.REGISTER_CODE);
	}

}

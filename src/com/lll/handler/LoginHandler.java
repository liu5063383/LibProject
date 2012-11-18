package com.lll.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.libproject.R;
import com.lll.dao.UserDao;
import com.lll.entity.User;
import com.lll.service.LoginService;

public class LoginHandler extends Handler {
	private ProgressDialog progressdialog;
	private Activity c;
	private User user;
	private String appid;
	private String partner;
	private UserDao userDao;
	public LoginHandler (Activity c,User user,String appid,String partner) {
		this.c = c;
		this.user = user;
		this.partner = partner;
		this.appid = appid;
		userDao = new UserDao(c);
	}
	@Override
	public void handleMessage(Message msg) {
		try {
			progressdialog = ProgressDialog.show(c,
					"请等待...", "正在为您登陆...");
			// flag 是接收来自服务器端的数据包装，这里客户与服务器交互用的是json
			LoginService loginService = new LoginService(appid, partner);
			Boolean flag = loginService.login(user);
			if (flag) {
				// 登录记录插入数据库
				userDao.updateUserLogin(user);
				// 返回游戏
				Toast.makeText(c, "登陆成功",
						Toast.LENGTH_SHORT).show();
				Intent it = new Intent();
				Bundle extras = new Bundle();
				extras.putString("username", user.getUsername());
				it.putExtras(extras);
				c.setResult(Activity.RESULT_OK, it);
				c.finish();
			} else {
				Toast.makeText(c, "登陆失败",
						Toast.LENGTH_SHORT).show();
				c.findViewById(R.id.txt_loginerror).setVisibility(
						View.VISIBLE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			progressdialog.dismiss();// 解除进度条
		}
	}

	public void login(long delayMillis) {
		this.removeMessages(0);
		sendMessageDelayed(obtainMessage(0), delayMillis);
	}
}

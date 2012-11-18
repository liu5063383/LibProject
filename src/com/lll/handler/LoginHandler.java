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
					"��ȴ�...", "����Ϊ����½...");
			// flag �ǽ������Է������˵����ݰ�װ������ͻ�������������õ���json
			LoginService loginService = new LoginService(appid, partner);
			Boolean flag = loginService.login(user);
			if (flag) {
				// ��¼��¼�������ݿ�
				userDao.updateUserLogin(user);
				// ������Ϸ
				Toast.makeText(c, "��½�ɹ�",
						Toast.LENGTH_SHORT).show();
				Intent it = new Intent();
				Bundle extras = new Bundle();
				extras.putString("username", user.getUsername());
				it.putExtras(extras);
				c.setResult(Activity.RESULT_OK, it);
				c.finish();
			} else {
				Toast.makeText(c, "��½ʧ��",
						Toast.LENGTH_SHORT).show();
				c.findViewById(R.id.txt_loginerror).setVisibility(
						View.VISIBLE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			progressdialog.dismiss();// ���������
		}
	}

	public void login(long delayMillis) {
		this.removeMessages(0);
		sendMessageDelayed(obtainMessage(0), delayMillis);
	}
}

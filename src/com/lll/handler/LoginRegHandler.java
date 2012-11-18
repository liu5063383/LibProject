package com.lll.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libproject.R;
import com.lll.entity.User;
import com.lll.service.RegisterService;

public class LoginRegHandler extends Handler {
	private ProgressDialog progressdialog;
	private Activity c;
	private User user;
	private String appid;
	private String partner;
	public LoginRegHandler (Activity c,User user,String appid,String partner) {
		this.c = c;
		this.user = user;
		this.partner = partner;
		this.appid = appid;
	}
		@Override
		public void handleMessage(Message msg) {
			try {
				progressdialog = ProgressDialog.show(c,
						"请等待...", "正在为您注册...");
				// flag 是接收来自服务器端的数据包装，这里客户与服务器交互用的是json
				RegisterService registerService = new RegisterService(appid, partner);
				Boolean flag = registerService.register(user);
				if (flag) {
					Toast.makeText(c, "注册成功",
							Toast.LENGTH_SHORT).show();
					// 返回游戏
					Intent it = new Intent();
					Bundle extras = new Bundle();
					extras.putSerializable("user", user);
					extras.putString("appid", appid);
					extras.putString("partner", partner);
					it.putExtras(extras);
					c.setResult(Activity.RESULT_OK, it);
					c.finish();
				} else {
					Toast.makeText(c, "注册失败",
							Toast.LENGTH_SHORT).show();
					TextView errormsg = (TextView) c.findViewById(R.id.txt_regerror);
					errormsg.setText("该账号已被注册");
					errormsg.setVisibility(View.VISIBLE);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				progressdialog.dismiss();// 解除进度条
			}
		}

		public void register(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}
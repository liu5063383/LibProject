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
						"��ȴ�...", "����Ϊ��ע��...");
				// flag �ǽ������Է������˵����ݰ�װ������ͻ�������������õ���json
				RegisterService registerService = new RegisterService(appid, partner);
				Boolean flag = registerService.register(user);
				if (flag) {
					Toast.makeText(c, "ע��ɹ�",
							Toast.LENGTH_SHORT).show();
					// ������Ϸ
					Intent it = new Intent();
					Bundle extras = new Bundle();
					extras.putSerializable("user", user);
					extras.putString("appid", appid);
					extras.putString("partner", partner);
					it.putExtras(extras);
					c.setResult(Activity.RESULT_OK, it);
					c.finish();
				} else {
					Toast.makeText(c, "ע��ʧ��",
							Toast.LENGTH_SHORT).show();
					TextView errormsg = (TextView) c.findViewById(R.id.txt_regerror);
					errormsg.setText("���˺��ѱ�ע��");
					errormsg.setVisibility(View.VISIBLE);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				progressdialog.dismiss();// ���������
			}
		}

		public void register(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}
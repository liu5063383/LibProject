package com.lll.activity;

import com.example.libproject.R;
import com.lll.entity.User;
import com.lll.handler.LoginHandler;
import com.lll.handler.LoginRegHandler;
import com.lll.service.LoginService;
import com.lll.service.RegisterService;
import com.lll.util.DBHelper;
import com.lll.util.NetTransferUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity {
	private String usernamestr;
	private String passwordstr1;
	private String passwordstr2;
	private LoginRegHandler loginRegHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("注册窗口");
		setContentView(R.layout.pay_sdk_register);
		initview();
	}

	public void initview() {
		final EditText username = (EditText) findViewById(R.id.reg_txt_username);
		final EditText password1 = (EditText) findViewById(R.id.txt_password1);
		final EditText password2 = (EditText) findViewById(R.id.txt_password2);
		username.setText("paytest");
		password1.setText("123qwe");
		Button reg1 = (Button) findViewById(R.id.buttonReg1);
		Button reg2 = (Button) findViewById(R.id.buttonReg2);
		reg1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取输入框的用户名密码
				usernamestr = username.getText().toString();
				passwordstr1 = password1.getText().toString();
				passwordstr2 = password2.getText().toString();
				if (passwordstr1.equals(passwordstr2)) {
					// 获取传递过来的appid和partner
					Bundle bundle = getIntent().getExtras();
					appid = bundle.getString("appid");
					partner = bundle.getString("partner");
					findViewById(R.id.txt_regerror).setVisibility(
							View.INVISIBLE);
					loginRegHandler = new LoginRegHandler(
							RegisterActivity.this, new User(usernamestr, passwordstr1), appid, partner);
					loginRegHandler.register(100);
				} else {
					TextView errormsg = (TextView) findViewById(R.id.txt_regerror);
					errormsg.setText("两次输入的密码不相同");
					errormsg.setVisibility(View.VISIBLE);
				}
			}

		});
		reg2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(RegisterActivity.this, "注册取消",
						Toast.LENGTH_SHORT).show();
				Intent it = new Intent();
				it.putExtra("STEP1RESULT", "");
				setResult(Activity.RESULT_CANCELED, it);
				finish();
			}

		});
	}

	// 处理器
	// class RegisterHandler extends Handler {
	//
	// @Override
	// public void handleMessage(Message msg) {
	// try {
	// progressdialog = ProgressDialog.show(RegisterActivity.this,
	// "请等待...", "正在为您注册...");
	// // flag 是接收来自服务器端的数据包装，这里客户与服务器交互用的是json
	// RegisterService registerService = new RegisterService(appid, partner);
	// Boolean flag = registerService.register(user);
	// if (flag) {
	// Toast.makeText(RegisterActivity.this, "注册成功",
	// Toast.LENGTH_SHORT).show();
	// // 返回游戏
	// Intent it = new Intent();
	// Bundle extras = new Bundle();
	// extras.putString("username", usernamestr);
	// it.putExtras(extras);
	// setResult(RESULT_OK, it);
	// finish();
	// } else {
	// Toast.makeText(RegisterActivity.this, "注册失败",
	// Toast.LENGTH_SHORT).show();
	// TextView errormsg = (TextView) findViewById(R.id.txt_regerror);
	// errormsg.setText("该账号已被注册");
	// errormsg.setVisibility(View.VISIBLE);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// progressdialog.dismiss();// 解除进度条
	// }
	// }
	//
	// public void sleep(long delayMillis) {
	// this.removeMessages(0);
	// sendMessageDelayed(obtainMessage(0), delayMillis);
	// }
	// }
	// class LoginRegHandler extends Handler {
	//
	// @Override
	// public void handleMessage(Message msg) {
	// try {
	// progressdialog = ProgressDialog.show(RegisterActivity.this,
	// "请等待...", "正在为您注册...");
	// // flag 是接收来自服务器端的数据包装，这里客户与服务器交互用的是json
	// RegisterService registerService = new RegisterService(appid, partner);
	// Boolean flag = registerService.register(user);
	// if (flag) {
	// Toast.makeText(RegisterActivity.this, "注册成功",
	// Toast.LENGTH_SHORT).show();
	// // 返回游戏
	// Intent it = new Intent();
	// Bundle extras = new Bundle();
	// extras.putString("username", usernamestr);
	// it.putExtras(extras);
	// setResult(RESULT_OK, it);
	// finish();
	// } else {
	// Toast.makeText(RegisterActivity.this, "注册失败",
	// Toast.LENGTH_SHORT).show();
	// TextView errormsg = (TextView) findViewById(R.id.txt_regerror);
	// errormsg.setText("该账号已被注册");
	// errormsg.setVisibility(View.VISIBLE);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// progressdialog.dismiss();// 解除进度条
	// }
	// }
	//
	// public void sleep(long delayMillis) {
	// this.removeMessages(0);
	// sendMessageDelayed(obtainMessage(0), delayMillis);
	// }
	// }

}

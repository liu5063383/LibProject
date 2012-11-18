package com.lll.activity;

import com.example.libproject.R;
import com.lll.dao.UserDao;
import com.lll.entity.User;
import com.lll.handler.LoginHandler;
import com.lll.service.LoginService;
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
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
	private String usernamestr;
	private String passwordstr;
	private LoginHandler loginHandler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// 获取传递过来的appid和partner
		super.onCreate(savedInstanceState);
		setTitle("登陆窗口");
		setContentView(R.layout.pay_sdk_login);
		initview();
	}

	public void initview() {
		final EditText username = (EditText) findViewById(R.id.txt_username);
		final EditText password = (EditText) findViewById(R.id.txt_password);
		username.setText("paytest");
		password.setText("123qwe");
		Button login1 = (Button) findViewById(R.id.buttonLogin1);
		Button login2 = (Button) findViewById(R.id.buttonLogin2);
		Button login3 = (Button) findViewById(R.id.buttonLogin3);
		login1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取输入框的用户名密码
				usernamestr = username.getText().toString();
				passwordstr = password.getText().toString();
				findViewById(R.id.txt_loginerror).setVisibility(View.INVISIBLE);
				loginHandler = new LoginHandler(LoginActivity.this, new User(usernamestr,passwordstr), appid, partner);
				loginHandler.login(100);
			}

		});
		login2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(LoginActivity.this, "登陆取消", Toast.LENGTH_SHORT)
						.show();
				Intent it = new Intent();
				setResult(Activity.RESULT_OK, it);
				finish();
			}

		});
		login3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("appid", appid);
				bundle.putString("partner", partner);
				it.putExtras(bundle);
				it.setClass(LoginActivity.this, RegisterActivity.class);
				startActivityForResult(it, BaseActivity.REGISTER_CODE);
			}

		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if(requestCode == BaseActivity.REGISTER_CODE) {
			if (resultCode == RESULT_OK) {
				Bundle bundle = intent.getExtras();
				if (bundle != null) {
					// 获取注册的用户名密码
					User user = (User)bundle.getSerializable("user");
					// 获取传递过来的appid和partner
					appid = bundle.getString("appid");
					partner = bundle.getString("partner");
					findViewById(R.id.txt_loginerror).setVisibility(View.INVISIBLE);
					loginHandler = new LoginHandler(LoginActivity.this, user, appid, partner);
					loginHandler.login(100);
				}
			}
			if (resultCode == RESULT_CANCELED) {
			}
		}
	};

	// 处理器
//	class LoginHandler extends Handler {
//		
//		@Override
//		public void handleMessage(Message msg) {
//			try {
//				progressdialog = ProgressDialog.show(LoginActivity.this,
//						"请等待...", "正在为您登陆...");
//				// flag 是接收来自服务器端的数据包装，这里客户与服务器交互用的是json
//				LoginService loginService = new LoginService(appid, partner);
//				Boolean flag = loginService.login(usernamestr, passwordstr);
//				if (flag) {
//					Toast.makeText(LoginActivity.this, "登陆成功",
//							Toast.LENGTH_SHORT).show();
//					// 登录记录插入数据库
//					DBHelper dbHelper = new DBHelper(getApplicationContext());
//					ContentValues values = new ContentValues();
//					values.put("username", usernamestr);
//					values.put("password", passwordstr);
//					values.put("desc", "lastLoginAccount");
//					ContentValues values1 = new ContentValues();
//					values1.put("desc", "");
//					dbHelper.update(values1, "desc", "lastLoginAccount");
//					if (dbHelper.queryBy("username", usernamestr)
//							.getCount() == 0) {
//						dbHelper.insert(values);
//					} else {
//						dbHelper.update(values, "username", usernamestr);
//					}
//					// 返回游戏
//					Intent it = new Intent();
//					Bundle extras = new Bundle();
//					extras.putString("username", usernamestr);
//					it.putExtras(extras);
//					setResult(RESULT_OK, it);
//					finish();
//				} else {
//					Toast.makeText(LoginActivity.this, "登陆失败",
//							Toast.LENGTH_SHORT).show();
//					findViewById(R.id.txt_loginerror).setVisibility(
//							View.VISIBLE);
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				progressdialog.dismiss();// 解除进度条
//			}
//		}
//
//		public void sleep(long delayMillis) {
//			this.removeMessages(0);
//			sendMessageDelayed(obtainMessage(0), delayMillis);
//		}
//	}
}

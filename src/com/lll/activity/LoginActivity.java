package com.lll.activity;

import com.example.libproject.R;
import com.lll.service.LoginService;
import com.lll.util.NetTransferUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
	private ProgressDialog progressdialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("登陆窗口");
		setContentView(R.layout.login);
		initview();
	}
	
	public void initview() {
		final EditText username = (EditText) findViewById(R.id.txt_username);
		final EditText password = (EditText) findViewById(R.id.txt_password);
		username.setText("1234");
		password.setText("qwer");
		Button login1 = (Button) findViewById(R.id.buttonLogin1);
		Button login2 = (Button) findViewById(R.id.buttonLogin2);
		login1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取输入框的用户名密码
				usernamestr = username.getText().toString();
				passwordstr = password.getText().toString();
				progressdialog = ProgressDialog.show(LoginActivity.this,
						"请等待...", "正在为您登陆...");
				refreshHandler.sleep(100);
			}

		});
		login2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(LoginActivity.this, "登陆取消",
						Toast.LENGTH_SHORT).show();
				Intent it = new Intent();  
                it.putExtra("STEP1RESULT","");  
                setResult(Activity.RESULT_OK, it);  
                finish();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	private RefreshHandler refreshHandler = new RefreshHandler();

	// 处理器
	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			try {
				String url = "http://172.20.0.206:8082//TestServelt/login.do";
				/**
				 * flag 是接收来自服务器端的数据包装，这里客户与服务器交互用的是json
				 * 
				 * json解析出对象，将对象放入bundle,如：
				 * 
				 * Bundle bagent =new Bundle(); bagent.putSerializable("agent",
				 * agent);
				 */
				// Bundle flag = UserDataServiceHelper.login(uri, usernamestr,
				// passwordstr);
				LoginService loginService = new LoginService("", "");
				Boolean flag = loginService.login(usernamestr, passwordstr);
				
				if (flag) {
					Toast.makeText(LoginActivity.this, "登陆成功",
							Toast.LENGTH_SHORT).show();
					// Intent intent = new Intent();
					// intent.setClass(EHRActivity.this, FileManager.class);
					// intent.putExtras(flag);
					// LoginDemoActivity.this.finish();
				} else {
					Toast.makeText(LoginActivity.this, "登陆失败",
							Toast.LENGTH_SHORT).show();
					findViewById(R.id.txt_loginerror).setVisibility(
							View.VISIBLE);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				progressdialog.dismiss();// 解除进度条
			}
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}
}

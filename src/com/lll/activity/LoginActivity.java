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
		setTitle("��½����");
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
		login1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡ�������û�������
				usernamestr = username.getText().toString();
				passwordstr = password.getText().toString();
				progressdialog = ProgressDialog.show(LoginActivity.this,
						"��ȴ�...", "����Ϊ����½...");
				findViewById(R.id.txt_loginerror).setVisibility(
						View.INVISIBLE);
				refreshHandler.sleep(100);
			}

		});
		login2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(LoginActivity.this, "��½ȡ��",
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

	// ������
	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			try {
				/**
				 * flag �ǽ������Է������˵����ݰ�װ������ͻ�������������õ���json
				 * 
				 * json���������󣬽��������bundle,�磺
				 * 
				 * Bundle bagent =new Bundle(); bagent.putSerializable("agent",
				 * agent);
				 */
				LoginService loginService = new LoginService("", "");
				Boolean flag = loginService.login(usernamestr, passwordstr);
				if (true) {
					Toast.makeText(LoginActivity.this, "��½�ɹ�",
							Toast.LENGTH_SHORT).show();
					 Intent it = new Intent();
					 Bundle extras = new Bundle();
					 extras.putString("username", usernamestr);
					 it.putExtras(extras);
					setResult(RESULT_OK, it);
					finish();
					// Intent intent = new Intent();
					// intent.setClass(EHRActivity.this, FileManager.class);
					// intent.putExtras(flag);
					// LoginDemoActivity.this.finish();
				} else {
					Toast.makeText(LoginActivity.this, "��½ʧ��",
							Toast.LENGTH_SHORT).show();
					findViewById(R.id.txt_loginerror).setVisibility(
							View.VISIBLE);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				progressdialog.dismiss();// ���������
			}
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}
}

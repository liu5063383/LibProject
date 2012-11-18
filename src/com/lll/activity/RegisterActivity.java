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
		setTitle("ע�ᴰ��");
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
				// ��ȡ�������û�������
				usernamestr = username.getText().toString();
				passwordstr1 = password1.getText().toString();
				passwordstr2 = password2.getText().toString();
				if (passwordstr1.equals(passwordstr2)) {
					// ��ȡ���ݹ�����appid��partner
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
					errormsg.setText("������������벻��ͬ");
					errormsg.setVisibility(View.VISIBLE);
				}
			}

		});
		reg2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(RegisterActivity.this, "ע��ȡ��",
						Toast.LENGTH_SHORT).show();
				Intent it = new Intent();
				it.putExtra("STEP1RESULT", "");
				setResult(Activity.RESULT_CANCELED, it);
				finish();
			}

		});
	}

	// ������
	// class RegisterHandler extends Handler {
	//
	// @Override
	// public void handleMessage(Message msg) {
	// try {
	// progressdialog = ProgressDialog.show(RegisterActivity.this,
	// "��ȴ�...", "����Ϊ��ע��...");
	// // flag �ǽ������Է������˵����ݰ�װ������ͻ�������������õ���json
	// RegisterService registerService = new RegisterService(appid, partner);
	// Boolean flag = registerService.register(user);
	// if (flag) {
	// Toast.makeText(RegisterActivity.this, "ע��ɹ�",
	// Toast.LENGTH_SHORT).show();
	// // ������Ϸ
	// Intent it = new Intent();
	// Bundle extras = new Bundle();
	// extras.putString("username", usernamestr);
	// it.putExtras(extras);
	// setResult(RESULT_OK, it);
	// finish();
	// } else {
	// Toast.makeText(RegisterActivity.this, "ע��ʧ��",
	// Toast.LENGTH_SHORT).show();
	// TextView errormsg = (TextView) findViewById(R.id.txt_regerror);
	// errormsg.setText("���˺��ѱ�ע��");
	// errormsg.setVisibility(View.VISIBLE);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// progressdialog.dismiss();// ���������
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
	// "��ȴ�...", "����Ϊ��ע��...");
	// // flag �ǽ������Է������˵����ݰ�װ������ͻ�������������õ���json
	// RegisterService registerService = new RegisterService(appid, partner);
	// Boolean flag = registerService.register(user);
	// if (flag) {
	// Toast.makeText(RegisterActivity.this, "ע��ɹ�",
	// Toast.LENGTH_SHORT).show();
	// // ������Ϸ
	// Intent it = new Intent();
	// Bundle extras = new Bundle();
	// extras.putString("username", usernamestr);
	// it.putExtras(extras);
	// setResult(RESULT_OK, it);
	// finish();
	// } else {
	// Toast.makeText(RegisterActivity.this, "ע��ʧ��",
	// Toast.LENGTH_SHORT).show();
	// TextView errormsg = (TextView) findViewById(R.id.txt_regerror);
	// errormsg.setText("���˺��ѱ�ע��");
	// errormsg.setVisibility(View.VISIBLE);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// progressdialog.dismiss();// ���������
	// }
	// }
	//
	// public void sleep(long delayMillis) {
	// this.removeMessages(0);
	// sendMessageDelayed(obtainMessage(0), delayMillis);
	// }
	// }

}
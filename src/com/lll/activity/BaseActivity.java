package com.lll.activity;


import com.example.libproject.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;

public class BaseActivity extends Activity{
	protected String appid;
	protected String partner;
	private String title;
	private ProgressDialog progressDialog;
	public static final int LOGIN_CODE = 1;
	public static final int REGISTER_CODE = 2;
	
	public void onCreate(Bundle savedInstanceState) {
		// 获取传递过来的appid和partner
		Bundle bundle = getIntent().getExtras();
		appid = bundle.getString("appid");
		partner = bundle.getString("partner");
		super.onCreate(savedInstanceState);
	}
	protected void setTitle(String title){
		this.title = title;
	}
	protected void showLoading(){
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("正在加载...");
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}
	
	
	protected void showLoading(String tips){
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(tips);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}
	
	
	 
	
	protected void hideLoading(){ 
		if(progressDialog != null){
			progressDialog.cancel();
			progressDialog = null;
		} 
	}
	
	 
	
}

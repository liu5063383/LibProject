package com.lll.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Button;

public class BaseActivity extends Activity{

	private String title;
	private Button buttonLeft;
	private ProgressDialog progressDialog;
	public static final int LOGIN_CODE = 1;
	public static final int REGISTER_CODE = 2;
	protected void setTitle(String title){
		this.title = title;
	}
	
	
	protected void showLoading(){
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("ÕýÔÚ¼ÓÔØ...");
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

package com.lll.platform;

import com.lll.activity.BaseActivity;
import com.lll.activity.LoginActivity;
import android.app.Activity;
import android.content.Intent;

public class GeneralPlatform extends BasePlatform {
	private static GeneralPlatform generalPlatform = null;

	protected GeneralPlatform() {

	}

	public static GeneralPlatform getInstance() {
		if (generalPlatform == null) {
			synchronized (GeneralPlatform.class) {
				generalPlatform = new GeneralPlatform();
			}
		}
		return generalPlatform;
	}
	
	public void login(Activity activity) {
		Intent in = new Intent();
		in.setClass(activity, LoginActivity.class);
		activity.startActivityForResult(in, BaseActivity.LOGIN_CODE);
	}

}

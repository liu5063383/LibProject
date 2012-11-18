package com.lll.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.lll.entity.User;
import com.lll.util.EncryptionTools;
import com.lll.util.NetTransferUtil;

public class LoginService  extends BaseService {

	public LoginService(String appid, String partner) {
		super(appid, partner);
	}

	public Boolean login(User user) {
		String url = "http://api.aigame365.com/api/loginService.shtml";
		String encryPass = EncryptionTools.md5(user.getPassword() + PRIVATE_KEY);
		paramsMap.put("username", user.getUsername());
		paramsMap.put("password", encryPass);
		paramsMap.put("appid", appid);
		paramsMap.put("partner", partner);
		String macString = EncryptionTools.md5(appid + user.getUsername() + encryPass
				+ partner + PRIVATE_KEY);
		paramsMap.put("mac", macString);
		String resultString = NetTransferUtil.post(url, paramsMap);
		Log.i("debug_print", resultString);
		JSONObject resultObj = null;
		try {
			resultObj = new JSONObject(resultString);
			String result = (String) resultObj.get("result");
			if (result.equals("1")) {
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
}

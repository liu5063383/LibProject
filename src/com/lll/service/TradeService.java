package com.lll.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.lll.util.EncryptionTools;
import com.lll.util.NetTransferUtil;

public class TradeService  extends BaseService {

	public TradeService(String appid, String partner) {
		super(appid, partner);
	}

	public Boolean login(String username, String password) {
		String url = "http://api.aigame365.com/api/loginService.shtml";
		String encryPass = EncryptionTools.md5(password + PRIVATE_KEY);
		paramsMap.put("username", username);
		paramsMap.put("password", encryPass);
		paramsMap.put("appid", appid);
		paramsMap.put("partner", partner);
		String macString = EncryptionTools.md5(appid + username + encryPass
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

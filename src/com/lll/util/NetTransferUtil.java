package com.lll.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;


public class NetTransferUtil {
	public static String post(String url, Map<String, String> paramsMap) {
		/* 建立HTTP Post连线 */
		HttpPost httpRequest = new HttpPost(url);
		// Post运作传送变数必须用NameValuePair[]阵列储存
		// 传参数 服务端获取的方法为request.getParameter("name")
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Set<String> keys = paramsMap.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			params.add(new BasicNameValuePair(key, paramsMap.get(key)));
		}
		try {
			// 发出HTTP request
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 取得HTTP response
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);

			// 若状态码为200 ok
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 取出回应字串
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				return strResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

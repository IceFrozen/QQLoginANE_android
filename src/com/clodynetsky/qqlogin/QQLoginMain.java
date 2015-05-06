package com.clodynetsky.qqlogin;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.mtp.MtpConstants;
import android.util.Log;
import android.widget.EditText;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.clodynetsky.contentData.AppData;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.Tencent;

public class QQLoginMain  extends FREContext{

	@Override
	public void dispose() {

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String,FREFunction> functionMap=new java.util.HashMap<String,FREFunction>();
		functionMap.put("loginQQ", new LoginQQFunction());
		functionMap.put("getUserInfo", new UserInfoFunction());
		functionMap.put("shareToQQ", new ShareToQQ());
		functionMap.put("sendWeibo", new SendWeiBoContext());
		functionMap.put("loginOut", new LoginoutQQFunction());
		functionMap.put("getFansList", new FansList());
		return functionMap;
	}

}

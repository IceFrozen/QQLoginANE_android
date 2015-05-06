package com.clodynetsky.qqlogin;

import android.app.Activity;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.clodynetsky.contentData.AppData;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.Tencent;

public class QQLoginDomain extends Activity implements FREExtension {
	
	public static QQLoginMain context;

	@Override
	public void dispose() {
		
		context = null;
		AppData.appId = null;
		AppData.mQQAuth=null;
		AppData.mTencent=null;
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public FREContext createContext(String appId) {
		if(null == appId){
			AppData.appId = AppData.DEFAULT_APPID;
		}else{
			AppData.appId = appId;
		}
		context =  new QQLoginMain();
		return context;
	}

}

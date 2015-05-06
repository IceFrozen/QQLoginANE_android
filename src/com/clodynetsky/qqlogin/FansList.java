package com.clodynetsky.qqlogin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.clodynetsky.contentData.AppData;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.t.Weibo;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;

public class FansList implements FREFunction {

	private FREContext _context;
	private  String TAG;
	private Weibo mWeibo = null;

	public FansList() {
		this.TAG = AppData.FansList;
	}

	@Override
	public FREObject call(FREContext context, FREObject[] freArg) {
		
		_context = context;
		
		if(null == AppData.mTencent){
			_context.dispatchStatusEventAsync(AppData.Error, AppData.TENCENT_NULL);
			return null;
		}
		if(null == AppData.mTencent.getQQToken()){
			_context.dispatchStatusEventAsync(AppData.Error, AppData.TOKEN_NULL);
			return null;
		}
		
		if (AppData.mTencent.isSessionValid()) {
		    
			mWeibo = new Weibo(context.getActivity(), AppData.mTencent.getQQToken());
			
			int content;
				try {
					content = freArg[0].getAsInt();
					Log.i("Content!!!", content+"");
					
					Bundle params = new Bundle();
					params.putString("access_token", AppData.mTencent.getQQToken().getAccessToken());
					params.putString("openid", AppData.mTencent.getOpenId());
					params.putString("oauth_consumer_key", AppData.mTencent.getQQToken().getAppId());
					params.putString("format", "json");
					params.putString("reqnum", "3");
					params.putString("startindex", "0");
					params.putString("mode", "1");
					params.putString("status_os", Build.VERSION.RELEASE);
					params.putString("status_machine", Build.MODEL);
					params.putString("status_version", Build.VERSION.SDK);
					params.putString("sdkv", "2.2.1");
					params.putString("appid_for_getting_config", AppData.mTencent.getQQToken().getAppId());
					params.putString("sdkp", "a");
					params.putString("status_os", Build.VERSION.RELEASE);
					Log.i("Tag", AppData.mTencent.getOpenId());
					AppData.mTencent.requestAsync("relation/get_fanslist", params, Constants.HTTP_GET, new TQQApiListener("get_fanslist", false), null);
					
					//mWeibo.atFriends(content,new TQQApiListener("get_fanslist", false));
				} catch (Exception e) {
					_context.dispatchStatusEventAsync(AppData.Error, AppData.IllegalStateException+":"+e.getMessage());
				}
		} else {
			AppData.mTencent.logout(context.getActivity());
			_context.dispatchStatusEventAsync(AppData.Error, AppData.LOGIN_NOLOGIN);
		}
	
		return null;
		
	}
	private class TQQApiListener implements IRequestListener {
		
		private String mScope = "all";
        private Boolean mNeedReAuth = false;
		
        public TQQApiListener(String scope, boolean needReAuth) {
			this.mScope = scope;
			this.mNeedReAuth = needReAuth;
        }
        @Override
        public void onComplete(JSONObject response) {
            doComplete(response);
          }

        protected void doComplete(JSONObject values) {
            Log.i("Tag", values.toString());
            _context.dispatchStatusEventAsync(TAG, values.toString());
            _context = null;
          }

		@Override
		public void onConnectTimeoutException(ConnectTimeoutException arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onHttpStatusException(HttpStatusException arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onIOException(IOException arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onJSONException(JSONException arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMalformedURLException(MalformedURLException arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNetworkUnavailableException(
				NetworkUnavailableException arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSocketTimeoutException(SocketTimeoutException arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUnknowException(Exception arg0) {
			// TODO Auto-generated method stub
			
		}

		
		
		
	}	

}

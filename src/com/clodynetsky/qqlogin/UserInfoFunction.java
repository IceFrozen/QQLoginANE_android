package com.clodynetsky.qqlogin;

import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.clodynetsky.contentData.AppData;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class UserInfoFunction  implements FREFunction{
	
	private FREContext _context;
	private  String TAG;
	private UserInfo mInfo;

	public UserInfoFunction() {
		this.TAG = AppData.UserInfoFunction;
	}

	@Override
	public FREObject call(FREContext context, FREObject[] arg1) {
	
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
			IUiListener listener = new BaseUiListener() {
				@Override
				protected void doComplete(JSONObject values) {
					_context.dispatchStatusEventAsync(TAG, values.toString());
					_context = null;
				}
			};
			mInfo = new UserInfo(context.getActivity(),AppData.mTencent.getQQToken());
			mInfo.getUserInfo(listener);
		} else {
			AppData.mTencent.logout(context.getActivity());
			_context.dispatchStatusEventAsync(AppData.Error, AppData.LOGIN_NOLOGIN);
		}
	
		return null;
	}
	
	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			doComplete((JSONObject)response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
			if(_context!=null){
				_context.dispatchStatusEventAsync(AppData.Error,AppData.GETUSERINFO_REQUEST_ERROR+":"+e.errorCode+":"+e.errorMessage);
			}
		}

		@Override
		public void onCancel() {
		}
	}

}

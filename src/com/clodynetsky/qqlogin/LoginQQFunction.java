package com.clodynetsky.qqlogin;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.clodynetsky.contentData.AppData;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class LoginQQFunction implements FREFunction {
	
	
	private FREContext _context;
	private  String TAG;
	
	public LoginQQFunction() {
		this.TAG = AppData.LoginQQFunction;;	
	}

	@Override
	public FREObject call(FREContext context, FREObject[] arg1) {	
		_context = context;
		final Context FreContext = context.getActivity();
		final Context ctxContext = FreContext.getApplicationContext();
		AppData.mQQAuth = QQAuth.createInstance(AppData.appId, ctxContext);
		AppData.mTencent = Tencent.createInstance(AppData.appId, FreContext);;
		if (!AppData.mQQAuth.isSessionValid()) {
			IUiListener listener = new BaseUiListener() {
				@Override
				protected void doComplete(JSONObject values) {
					_context.dispatchStatusEventAsync(TAG, values.toString());
					_context = null;
				}
			};
			AppData.mTencent.login(context.getActivity(), "all", listener);
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
				_context.dispatchStatusEventAsync(AppData.Error,AppData.LOGIN_REQUEST_ERROR+":"+e.errorCode+":"+e.errorMessage);
			}
		}

		@Override
		public void onCancel() {
		}
	}

}

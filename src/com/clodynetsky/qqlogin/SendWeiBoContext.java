package com.clodynetsky.qqlogin;

import org.json.JSONObject;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.clodynetsky.contentData.AppData;
import com.tencent.t.Weibo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class SendWeiBoContext  implements FREFunction{

	private FREContext _context;
	private  String TAG;
	private Weibo mWeibo = null;

	public SendWeiBoContext() {
		this.TAG = AppData.SendWeiBoContext;
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
			
			String content;
				try {
					content = freArg[0].getAsString();
					Log.i("Content!!!", content);
					mWeibo.sendText(content,new TQQApiListener("add_t", false));
				} catch (Exception e) {
					_context.dispatchStatusEventAsync(AppData.Error, AppData.IllegalStateException+":"+e.getMessage());
				}
		} else {
			AppData.mTencent.logout(context.getActivity());
			_context.dispatchStatusEventAsync(AppData.Error, AppData.LOGIN_NOLOGIN);
		}
	
		return null;
	}
	
	

	private class TQQApiListener implements IUiListener {
		
		private String mScope = "all";
        private Boolean mNeedReAuth = false;
		
        public TQQApiListener(String scope, boolean needReAuth) {
			this.mScope = scope;
			this.mNeedReAuth = needReAuth;
        }
		
		
		@Override
		public void onComplete(Object response) {
			doComplete((JSONObject)response);
		}

		protected void doComplete(JSONObject values) {
			_context.dispatchStatusEventAsync(TAG, values.toString());
			_context = null;
		}
		@Override
		public void onError(UiError e) {
			if(_context!=null){
				_context.dispatchStatusEventAsync(AppData.Error,AppData.WEIBOSEND_REQUEST_ERROR+":"+e.errorCode+":"+e.errorMessage);
			}
		}
		@Override
		public void onCancel() {
		}
	}

}

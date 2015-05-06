package com.clodynetsky.qqlogin;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.clodynetsky.contentData.AppData;
import com.clodynetsky.contentData.QQLoginUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class ShareToQQ  implements FREFunction{
	
	private FREContext _context;
	private  String TAG;
	private QQShare mQQShare;
	private int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
	private int mExtarFlag = 0x00;

	public ShareToQQ() {
		this.TAG = AppData.ShareToQQ;
	}

	@Override
	public FREObject call(FREContext context, FREObject[] freArg) {

		
		_context = context;
		
		if(null == AppData.mTencent){
			_context.dispatchStatusEventAsync(AppData.Error, AppData.TENCENT_NULL);
			return null;
		}
			
		IUiListener listener = new BaseUiListener() {
				@Override
				protected void doComplete(JSONObject values) {
					_context.dispatchStatusEventAsync(TAG, values.toString());
					//TODO 
					_context = null;
				}
			};
		    
			final Bundle params =QQLoginUtils.getQQshareParams(freArg);
			
            doShareToQQ(context.getActivity(),params,listener);
		 
	
		return null;
	}
	
	
	
	private void doShareToQQ(final Activity activity, final Bundle params,
			final IUiListener listener) {
		
		 mQQShare = new QQShare(activity, AppData.mTencent.getQQToken());
		 new Thread(new Runnable() {
	            @Override
	            public void run() {
	                mQQShare.shareToQQ(activity, params,listener);
	            }
	        }).start();
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
				_context.dispatchStatusEventAsync(AppData.Error,AppData.SHARE_REQUEST_ERROR+":"+e.errorCode+":"+e.errorMessage);
			}
		}
		@Override
		public void onCancel() {
		}
	}
}

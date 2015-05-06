package com.clodynetsky.contentData;

import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.clodynetsky.qqlogin.ShareToQQ;
import com.tencent.connect.share.QQShare;

import android.os.Bundle;
import android.util.Log;

public class QQLoginUtils {

	
	/**
	 * 返回QQ分享返回的参数
	 * @param freArg
	 * @return
	 */
	public static  Bundle getQQshareParams(FREObject[] freArg){
		
		final Bundle params = new Bundle();
		
		try {
			int shareType = freArg[0].getAsInt();
			String appName = freArg[1].getAsString();
			int QQzone = freArg[2].getAsInt();
			String title = freArg[3].getAsString();
			String summary = freArg[4].getAsString();
			String target_Url = freArg[5].getAsString();
			String image_Url = freArg[6].getAsString();
			
			params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
			params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQzone);
            params.putString(QQShare.SHARE_TO_QQ_TITLE,title);
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, target_Url);
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, image_Url);
    
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return params;
	}
}

package com.clodynetsky.contentData;

import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.Tencent;

public class AppData {

	public static String appId;
	public static QQAuth mQQAuth;
	public static Tencent mTencent;
	
	
	//信息句柄
	public static final String LoginQQFunction = "loginInfo";
	
	public static final String UserInfoFunction = "userInfo";
	
	public static final String ShareToQQ = "shareToQQInfo";
	
	public static final String SendWeiBoContext = "sendWeiBoInfo";
	
	public static final String FansList = "fanList";
	
	public static final String DEFAULT_APPID = "123455";
	
	
	//错误句柄
	public static final String Error = "error";
	//sdk Tencent 为空！！
	public static final String TENCENT_NULL = "99";
	//qqToken 为空！
	public static final String TOKEN_NULL = "98";
	
	//错误
	//未登录
	public static final String IllegalStateException = "0";//内部异常
	
	//回调错误
	//返回错误 Uierror错误码：错误信息 {int:string} 例如：{1164:"no login please login first"}
	
	public static final String LOGIN_NOLOGIN = "1";
	
	public static final String LOGIN_REQUEST_ERROR = "2";//登陆请求错误
	
	public static final String GETUSERINFO_REQUEST_ERROR = "3";//获取用户请求中的错误
	
	public static final String SHARE_REQUEST_ERROR = "4";//分享QQ请求中的错误
	
	
	public static final String WEIBOSEND_REQUEST_ERROR = "5";//发送微博请求中的错误
	
	public static final String GETFANS_REQUEST_ERROR = "6"; 	// 获取微博粉丝过程中错误
	
	
	
	
	
	
	public static final String LOGIN_NOLOGIN2 = "1";
	public static final String LOGIN_NOLOGIN43 = "1";
	public static final String LOGIN_NOLOGIN5 = "1";
	public static final String LOGIN_NOLOGIN6 = "1";
	public static final String LOGIN_NOLOGIN7 = "1";
	public static final String LOGIN_NOLOGIN8= "1";
	
	

	
}

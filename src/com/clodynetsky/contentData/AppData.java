package com.clodynetsky.contentData;

import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.Tencent;

public class AppData {

	public static String appId;
	public static QQAuth mQQAuth;
	public static Tencent mTencent;
	
	
	//��Ϣ���
	public static final String LoginQQFunction = "loginInfo";
	
	public static final String UserInfoFunction = "userInfo";
	
	public static final String ShareToQQ = "shareToQQInfo";
	
	public static final String SendWeiBoContext = "sendWeiBoInfo";
	
	public static final String FansList = "fanList";
	
	public static final String DEFAULT_APPID = "123455";
	
	
	//������
	public static final String Error = "error";
	//sdk Tencent Ϊ�գ���
	public static final String TENCENT_NULL = "99";
	//qqToken Ϊ�գ�
	public static final String TOKEN_NULL = "98";
	
	//����
	//δ��¼
	public static final String IllegalStateException = "0";//�ڲ��쳣
	
	//�ص�����
	//���ش��� Uierror�����룺������Ϣ {int:string} ���磺{1164:"no login please login first"}
	
	public static final String LOGIN_NOLOGIN = "1";
	
	public static final String LOGIN_REQUEST_ERROR = "2";//��½�������
	
	public static final String GETUSERINFO_REQUEST_ERROR = "3";//��ȡ�û������еĴ���
	
	public static final String SHARE_REQUEST_ERROR = "4";//����QQ�����еĴ���
	
	
	public static final String WEIBOSEND_REQUEST_ERROR = "5";//����΢�������еĴ���
	
	public static final String GETFANS_REQUEST_ERROR = "6"; 	// ��ȡ΢����˿�����д���
	
	
	
	
	
	
	public static final String LOGIN_NOLOGIN2 = "1";
	public static final String LOGIN_NOLOGIN43 = "1";
	public static final String LOGIN_NOLOGIN5 = "1";
	public static final String LOGIN_NOLOGIN6 = "1";
	public static final String LOGIN_NOLOGIN7 = "1";
	public static final String LOGIN_NOLOGIN8= "1";
	
	

	
}

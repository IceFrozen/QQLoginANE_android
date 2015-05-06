package com.clodynetsky.qqlogin;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.clodynetsky.contentData.AppData;

public class LoginoutQQFunction  implements FREFunction{
	
	private FREContext _context;
	private FREObject  result;

	@Override
	public FREObject call(FREContext context, FREObject[] FreArg) {
	try {
		    _context = context;
		    result = FREObject.newObject(1);
		    if(AppData.mQQAuth != null && AppData.mTencent != null){
				if(AppData.mTencent.isSessionValid()){
					AppData.mTencent.logout(context.getActivity());
						result = FREObject.newObject(0);
				}
			}
		} catch (FREWrongThreadException e) {
			e.printStackTrace();
		}
		return result;
	}


}

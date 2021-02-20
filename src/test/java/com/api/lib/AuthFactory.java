package com.api.lib;

import com.api.base.DriverScript;
import com.api.utilities.CommonUtils;
import com.api.utilities.Constants;

public class AuthFactory extends DriverScript{
	//oAuth 1.0
	static String CONSUMER_API_KEY = CommonUtils.getProperty("Consumer_API_Key");
	static String CONSUMER_API_SECRET = CommonUtils.getProperty("Consumer_API_Secret");
	static String ACCESS_TOKEN = CommonUtils.getProperty("Access_Token");
	static String SECRET_TOKEN = CommonUtils.getProperty("Secret_Token");
	
	//oAuth 2.0
	static String BEARER_TOKEN = CommonUtils.getProperty("Bearer_Token");
}

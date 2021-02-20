package com.api.lib;

import io.restassured.response.Response;

public class API_Utils{
	public static long getResponseTime (Response response) {
		return response.getTime();
	}
}

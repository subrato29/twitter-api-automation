package com.api.lib;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class API_Utils{
	public static long getResponseTime (Response response) {
		return response.getTime();
	}
	
	public static String getResponseBodyInJson(Response response) {
		return response.jsonPath().prettify();
	}
	
	public static JsonPath getResponseJsonPath(Response response) {
		return response.jsonPath();
	}
	
	public static String getResponseAsString(Response response) {
		return response.asString();
	}
	
	public static int getStatusCode(Response response) {
		return response.getStatusCode();
	}
}

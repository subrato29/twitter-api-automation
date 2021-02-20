package com.api.lib;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import com.api.reports.ReportUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Http_Methods_oAuth1 extends AuthFactory{
	/**
	 * Keyword: post
	 * Author: Subrato Sarkar
	 * Date: 02/19/2020
	 * @return response
	 */
	public static Response post(String endpoint, String post) {
		try {
			Response response = RestAssured.given()
					.auth()
					.oauth(CONSUMER_API_KEY, 
						   CONSUMER_API_SECRET, 
						   ACCESS_TOKEN, 
						   SECRET_TOKEN)
					.post(endpoint + "?status=" + post);
			return response;
		} catch (Throwable t) {
			ReportUtil.markFailed("POST method is failed");
			t.printStackTrace();
			return null;
		}
	}
}
package testscripts;

import org.testng.annotations.Test;

import com.api.lib.API_Utils;
import com.api.lib.Http_Methods_oAuth1;
import com.api.reports.ReportUtil;
import com.api.utilities.CommonUtils;
import com.api.utilities.Constants;
import io.restassured.response.Response;

public class Tweets extends Http_Methods_oAuth1{
	static String TEST_DATA = Constants.TEST_DATA;
	static String tweet_id = null;
	
	@Test(priority = 1)
	public void post_tweet() {
		String tcId = "TC001";
		if (isTestCaseRunnable(tcId)) {
			String tweet = xls.getCellData(TEST_DATA, "RequestBody", rowNum) + " " +
					CommonUtils.getDateTime();
			endpoint = endpoint + "?status=" + tweet;
			Response response = post(endpoint);
			int actualResponseCode = API_Utils.getStatusCode(response);
			int expectedResponseCode = Integer.parseInt(xls.getCellData(TEST_DATA, "ResponseCode", rowNum));
			if (actualResponseCode == expectedResponseCode) {
				tweet_id = String.valueOf(API_Utils.getResponseJsonPath(response).get("id"));
				ReportUtil.markPassed("Tweet is successfully posted where the tweet is"
						+ ": " + tweet + " and the response time is: "
								+ "" + API_Utils.getResponseTime(response) + " milliseconds"
								+ " and tweet id is: " 
								+ tweet_id);
			} else {
				ReportUtil.markFailed("Tweet is not successful where actual response"
						+ " code is: " + actualResponseCode + " expected response"
								+ " code is: " + expectedResponseCode);
			}
		}
	}
	
	@Test(priority = 2)
	public void destroy_tweet() {
		String tcId = "TC002";
		if(isTestCaseRunnable(tcId)) {
			endpoint = endpoint + tweet_id + ".json";
			Response response = post(endpoint);
			int actualResponseCode = API_Utils.getStatusCode(response);
			int expectedResponseCode = Integer.parseInt(xls.getCellData(TEST_DATA, "ResponseCode", rowNum));
			if (tweet_id != null) {
				if (actualResponseCode == expectedResponseCode) {
					String expected_tweet_id = String.valueOf(API_Utils.getResponseJsonPath(response).get("id"));
					if (tweet_id.equals(expected_tweet_id)) {
						System.out.println(API_Utils.getResponseBodyInJson(response));
						ReportUtil.markPassed("Tweet is successfully destroyed"
										+ " where the response time is: "
										+ API_Utils.getResponseTime(response) + " milliseconds"
										+ " and tweet id is: " 
										+ expected_tweet_id);
					} else {
						ReportUtil.markFailed("Wrong tweet is destroyed where"
								+ " expected tweet id: " + expected_tweet_id
								+ " actual tweet id: " + tweet_id);
					}
				} else {
					ReportUtil.markFailed("Tweet is not successful where actual response"
							+ " code is: " + actualResponseCode + " expected response"
									+ " code is: " + expectedResponseCode);
				}
			}
		}
	}
}

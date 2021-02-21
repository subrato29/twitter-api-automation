package testscripts;

import org.testng.annotations.Test;
import com.api.lib.API_Utils;
import com.api.lib.Http_Methods_oAuth2;
import com.api.reports.ReportUtil;
import com.api.utilities.CommonUtils;
import com.api.utilities.Constants;
import io.restassured.response.Response;

public class Tweets_oAuth2 extends Http_Methods_oAuth2{
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
				tweet = String.valueOf(API_Utils.getResponseJsonPath(response).get("text"));
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
}

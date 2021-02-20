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
	
	@Test
	public void post_tweet() {
		String tcId = "TC001";
		if (isTestCaseRunnable(tcId)) {
			String post = xls.getCellData(TEST_DATA, "RequestBody", rowNum) + " " +
					CommonUtils.getDateTime();
			Response response = post(endpoint, post);
			int actualResponseCode = response.getStatusCode();
			int expectedResponseCode = Integer.parseInt(xls.getCellData(TEST_DATA, "ResponseCode", rowNum));
			if (actualResponseCode == expectedResponseCode) {
				ReportUtil.markPassed("Tweet is successful where the tweet is"
						+ ": " + post + " and the response time is: "
								+ "" + API_Utils.getResponseTime(response) + " milliseconds");
			} else {
				ReportUtil.markFailed("Tweet is not successful where actual response"
						+ " code is: " + actualResponseCode + " expected response"
								+ " code is: " + expectedResponseCode);
			}
		}
	}
	
}

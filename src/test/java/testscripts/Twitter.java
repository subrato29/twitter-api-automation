package testscripts;

import org.testng.annotations.Test;

import com.api.lib.Http_Methods_oAuth1;
import com.api.reports.ReportUtil;
import com.api.utilities.CommonUtils;
import com.api.utilities.Constants;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Twitter extends Http_Methods_oAuth1{
	static String TEST_DATA = Constants.TEST_DATA;
	
	@Test
	public void test() {
		String tcId = "TC001";
		if (isTestCaseRunnable(tcId)) {
			String post = xls.getCellData(TEST_DATA, "RequestBody", rowNum) + " " +
					CommonUtils.getDateTime();
			int actualResponseCode = post(endpoint, post).getStatusCode();
			int expectedResponseCode = Integer.parseInt(xls.getCellData(TEST_DATA, "ResponseCode", rowNum));
			if (actualResponseCode == expectedResponseCode) {
				ReportUtil.markPassed("Tweet is successful and the tweet is"
						+ ": " + post);
			} else {
				ReportUtil.markFailed("Tweet is not successful where actual response"
						+ " code is: " + actualResponseCode + " expected response"
								+ " code is: " + expectedResponseCode);
			}
		}
	}
	
}

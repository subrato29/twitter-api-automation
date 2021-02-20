package com.api.base;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.api.reports.ReportUtil;
import com.api.support.Xls_Reader;
import com.api.utilities.Constants;
import com.api.utilities.CommonUtils;
import com.api.utilities.Zip;

public class DriverScript{

	public static String TEST_DATA_PATH = System.getProperty("user.dir")+"/src/main/java/com/api/data";
	public static Xls_Reader xls = null, xlsController = new Xls_Reader(TEST_DATA_PATH + File.separator + "controller.xlsx");
	public static int rowNum = 2, rowNumController = 2;
	public static int rowNumExecutableTC = 2;
	public static int count= 0;
	public static String testCaseName;
	public static String testCaseId, testType;
	public static boolean continueRun = false;
	public static int maxTimeOut = 0;
	public static String endpoint = null, endpoint_POST = null;
	public static String baseUrl = CommonUtils.getProperty("baseURL");
	public static String scope = null;
	public static String isAccessTokenToGenerated = null;
	
	static TreeMap<Integer,String> executableTCIndex=new TreeMap<Integer,String>();
	public static int getRowNumForExecutableTestCases() {
			while(rowNumExecutableTC<=xlsController.getRowCount(Constants.TEST_DATA)) {
					if(xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNumExecutableTC).toUpperCase().equals(Constants.TEST_CASE_RUNMODE_YES)) {
						count++;
					}
					rowNumExecutableTC++;
			}
		rowNumExecutableTC=2;			
		return count;
	}
	
	public static boolean isTestCaseRunnable(String tcId){
		boolean isTestCaseRunnable = false;
		continueRun = false;
		rowNumController = xlsController.getCellRowNum(Constants.TEST_DATA, Constants.TEST_CASE_ID, tcId);
		rowNum = rowNumController;
		testCaseId = tcId;
		testCaseName = xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_NAME, rowNum);
		testType = xlsController.getCellData(Constants.TEST_DATA, "TestType", rowNum);
		if(xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNum).equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES)) {
			endpoint = null; 
			endpoint_POST = null;
			continueRun = true;
			xls = new Xls_Reader(TEST_DATA_PATH + File.separator + "api_data.xlsx");
			scope = xls.getCellData(Constants.TEST_DATA, "Scopes", rowNum);
			endpoint = baseUrl + xls.getCellData(Constants.TEST_DATA, "Endpoint", rowNum);
			isTestCaseRunnable = true;
			System.out.println("Test scenario started:==== " + testCaseId);
			isAccessTokenToGenerated = xls.getCellData(Constants.TEST_DATA, "Access_Token_To_Generate", rowNum).toUpperCase();
		}else{
			System.out.println("Please check the runmode of TestCaseID '" + testCaseId + "'");
			isTestCaseRunnable = false;
		}
		return isTestCaseRunnable;
	}
			
	public static final int countOfExecutableTestCases = getRowNumForExecutableTestCases();
		
	public static String getTestDataSheetName(){
		String testDataSheet=xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_DATA_SHEET_NAME, rowNum);
		return testDataSheet;
	}
		
		
	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterMethod
	public void afterMethod() { 
		ReportUtil.test = null;
	}
    
		
	@AfterSuite()
	public void afterClass() throws IOException{
		CommonUtils.openHTMLReport();
		Zip.zipFile();
	}
	
	
	@BeforeClass()
	public void init() throws IOException {
		
	}
    	
}

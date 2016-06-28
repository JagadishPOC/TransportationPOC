package com.poc.test.TestNG;


import java.io.FileInputStream;
import java.util.Properties;

/*import java.io.IOException;
import java.util.concurrent.TimeUnit;*/




import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
//import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.poc.constants.CommonConstants;
import com.poc.genericComponent.SeleniumComponents;
import com.poc.redBus.pageObjectpattern.PageObject_BusSearch;


public class Test_BusSearch extends SeleniumComponents{

	SoftAssert sAssert= new SoftAssert();
	Properties properties = new Properties();

	public static Logger log= Logger.getLogger(Test_BusSearch.class);
	PageObject_BusSearch poBSearch= new PageObject_BusSearch(webDriver);

	@Test(dataProvider="dp_getValidBusSearchData", dataProviderClass=com.poc.dataprovider.DataProvider_BusSearch.class,priority=1, groups={"smoke","Regression"})
	public void testvalidSearch(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Expected_Result)		
	{
		try
		{
			Reporter.log("Starting Test Case--------"+TC_ID+"--------Repetition Sequence--------" +Repetition_Order);
			//log.info("Starting Test Case--------"+TC_ID+"--------Repetition Sequence--------" +Repetition_Order);

			setWebDriver(setBrowser(Browser));
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---"+Browser+"---Browser launched");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---"+Browser+"---Browser launched");

			String path = System.getProperty("user.dir")+ CommonConstants.DATA_LOCATION_UI;
			properties.load(new FileInputStream(path));
			String URL  = properties.getProperty("URL");
			setURL(URL);
			Thread.sleep(2000);
			
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---URL launched");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---URL launched");

			PageObject_BusSearch poBSearch= new PageObject_BusSearch(webDriver);
			poBSearch.closeReferalPOPUP();
			poBSearch.setFromLocation(Search_From);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered From Location");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered From Location");

			poBSearch.setToLocation(Search_To);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Location");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Location");

			poBSearch.setJourneyDate(Search_Joureny);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Journey Date");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Journey Date");

			poBSearch.searchBusclick();
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Searching Bus");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Searching Bus");
			Thread.sleep(3000);

			int availableSeatCount = poBSearch.getAvailableSeatCount();

			int expectedSeatCount = Integer.parseInt(Expected_Result.replace(".0", ""));

			if(availableSeatCount>=expectedSeatCount){
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass -" +expectedSeatCount + "- Seats Available");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass -" +expectedSeatCount + "- Seats Available");
			}
			else{ 
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail -" +availableSeatCount + "- Actual seats less than Expected Seats"+expectedSeatCount);
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail -" +availableSeatCount + "- Actual seats less than Expected Seats"+expectedSeatCount);
				Snapshot(TC_ID, Repetition_Order);
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				sAssert.fail("Test Fail -" +availableSeatCount + "- Actual seats less than Expected Seats"+expectedSeatCount);
			}
			implicitlyWait(30);
		closeBrowser();
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			closeBrowser();
			sAssert.fail("Test Fail -" + "Test Fail - due to exception"+"---"+e);
		}
		sAssert.assertAll();
	}

	@Test(dataProvider="dp_getInvalidBusSearchData", dataProviderClass=com.poc.dataprovider.DataProvider_BusSearch.class,priority=2, groups={"Regression"})
	public void testInvalidSearch(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Expected_Result)		
	{		

		try
		{
			Reporter.log("Starting Test Case--------"+TC_ID+"--------Repetition Sequence--------" +Repetition_Order);
			//log.info("Starting Test Case--------"+TC_ID+"--------Repetition Sequence--------" +Repetition_Order);

			setWebDriver(setBrowser(Browser));
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---"+Browser+"---Browser launched");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---"+Browser+"---Browser launched");

			String path = System.getProperty("user.dir")+ CommonConstants.DATA_LOCATION_UI;
			properties.load(new FileInputStream(path));
			String URL  = properties.getProperty("URL");
			setURL(URL);
			Thread.sleep(2000);

			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---URL launched");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---URL launched");

			PageObject_BusSearch poBSearch= new PageObject_BusSearch(webDriver);
			poBSearch.closeReferalPOPUP();
			poBSearch.setFromLocation(Search_From);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered From Location");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered From Location");

			poBSearch.setToLocation(Search_To);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Location");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Location");

			poBSearch.setJourneyDate(Search_Joureny);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Journey Date");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Journey Date");

			poBSearch.searchBusclick();
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Searching Bus");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Searching Bus");
			Thread.sleep(3000);

			String notOpenTicketMsg = poBSearch.getNoOpenTicketMsg();

			if(notOpenTicketMsg.equalsIgnoreCase(Expected_Result)){
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Searching Bus");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass - Valid Error Message displayed");
			}
			else{ 
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail - No Valid Error Message displayed");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail - No Valid Error Message displayed");
				Snapshot(TC_ID, Repetition_Order);
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				sAssert.fail("Test Fail - No Valid Error Message displayed");
			}
			implicitlyWait(30);
			closeBrowser();
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			closeBrowser();
			sAssert.fail("Test Fail -" + "Test Fail - due to exception"+"---"+e);
		}
		sAssert.assertAll();
	}

	@Test(dataProvider="dp_getNotYetOpenBusSearchData", dataProviderClass=com.poc.dataprovider.DataProvider_BusSearch.class,priority=3, groups={"Regression"})
	public void testNotYetOpenSearch(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Expected_Result) throws InterruptedException		
	{		
		try
		{
			Reporter.log("Starting Test Case--------"+TC_ID+"--------Repetition Sequence--------" +Repetition_Order);
			//log.info("Starting Test Case--------"+TC_ID+"--------Repetition Sequence--------" +Repetition_Order);

			setWebDriver(setBrowser(Browser));
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---"+Browser+"---Browser launched");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---"+Browser+"---Browser launched");

			String path = System.getProperty("user.dir")+ CommonConstants.DATA_LOCATION_UI;
			properties.load(new FileInputStream(path));
			String URL  = properties.getProperty("URL");
			setURL(URL);
			Thread.sleep(2000);

			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---URL launched");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---URL launched");

			PageObject_BusSearch poBSearch= new PageObject_BusSearch(webDriver);
			poBSearch.closeReferalPOPUP();
			poBSearch.setFromLocation(Search_From);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered From Location");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered From Location");

			poBSearch.setToLocation(Search_To);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Location");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Location");

			poBSearch.setJourneyDate(Search_Joureny);
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Journey Date");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Entered To Journey Date");

			poBSearch.searchBusclick();
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Searching Bus");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Searching Bus");
			Thread.sleep(3000);

			String notYetOpenMsg = poBSearch.getNotYetOpenMsg();

			if(notYetOpenMsg.equalsIgnoreCase(Expected_Result)){
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass - Valid Error Message displayed");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass - Valid Error Message displayed");
			}
			else{ 
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail - No Valid Error Message displayed");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail - No Valid Error Message displayed");
				Snapshot(TC_ID, Repetition_Order);
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				sAssert.fail("Test Fail - No Valid Error Message displayed");

			}
			implicitlyWait(30);
			closeBrowser();
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			closeBrowser();
			sAssert.fail("Test Fail -" + "Test Fail - due to exception"+"---"+e);
		}
		sAssert.assertAll();
	}


	@AfterMethod
	public void closeActivities()
	{
	}

}



package com.poc.test.TestNG;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import java.util.concurrent.TimeUnit;
import java.util.Properties;

//import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.poc.constants.CommonConstants;
import com.poc.genericComponent.SeleniumComponents;
import com.poc.redBus.pageObjectpattern.PageObject_BusResult;
import com.poc.redBus.pageObjectpattern.PageObject_BusSearch;

public class Test_BusResult extends SeleniumComponents {

	SoftAssert sAssert= new SoftAssert();

	public static Logger log= Logger.getLogger(Test_BusSearch.class);
	Properties properties  =new Properties();

	//private String format;

	@Test(dataProvider="dp_getBusResult_Filter_Provider", dataProviderClass=com.poc.dataprovider.DataProvider_BusResult.class,priority=1, groups={"Regression"})
	public void testBusResultFilterProvider(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Travels, String Expected_Result)		
	{
		try {
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);

			BusSearch(TC_ID, Repetition_Order,Browser,Search_From, Search_To,Search_Joureny);
			PageObject_BusResult poBResult= new PageObject_BusResult(webDriver);
			poBResult.getProviderFilter(Travels);	
			//WebElement travelName = webDriver.findElements(By.xpath(".//*[@id='onwardTrip']/div[2]/ul/li/div/div[2]/h4[contains(text(),'"+Travels+"')]"));

			List<WebElement> travelName = webDriver.findElements(By.xpath(".//*[@id='onwardTrip']/div[2]/ul/li/div/div[2]/h4"));
			if(	travelName.size()==0){
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- No Travels found");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- No Travels found");	
				closeBrowser();
			}
			else{
				int sameTravels =0;
				int notSameTravels=0;
				for (WebElement temptravelName : travelName) {
					if(temptravelName.getText().equalsIgnoreCase(Expected_Result)){
						//	System.out.println(temptravelName.getText());
						sameTravels=sameTravels+1;
					}
					else{
						//	System.out.println(temptravelName.getText());
						notSameTravels=notSameTravels+1;
					}
				}	
				if(notSameTravels==0 && sameTravels!=0){
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass - Travels Sort was successful");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass - Travels Sort was successful");
				}
				else{
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail - Travels Sort was not successful");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail - Travels Sort was not successful");
					Snapshot(TC_ID, Repetition_Order);
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
					Assert.fail("Test Fail - Travels Sort was not successful");
				}
				closeBrowser();
			}
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			closeBrowser();
			sAssert.fail("Test Fail -" + "Test Fail - due to exception"+"---"+e);
		}
		sAssert.assertAll();
	}


	@Test(dataProvider="dp_getBusResult_Filter_Type", dataProviderClass=com.poc.dataprovider.DataProvider_BusResult.class,priority=2, groups={"Regression"})
	public void testBusResultFilterType(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Travels, String Expected_Result)		
	{
		try {
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);

			BusSearch(TC_ID, Repetition_Order,Browser,Search_From, Search_To,Search_Joureny);
			PageObject_BusResult poBResult= new PageObject_BusResult(webDriver);
			poBResult.getBusTypeFilter(Travels);	
			//WebElement travelName = webDriver.findElements(By.xpath(".//*[@id='onwardTrip']/div[2]/ul/li/div/div[2]/h4[contains(text(),'"+Travels+"')]"));

			List<WebElement> busType = webDriver.findElements(By.xpath(".//*[@id='onwardTrip']/div[2]/ul/li/div/div[2]/span[1]"));
			if(	busType.size()==0){
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- No Travels found");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- No Travels found");	
				closeBrowser();
			}
			else
			{
				int sameTravels =0;
				int notSameTravels=0;
				for (WebElement tempbusType : busType) {
					if(tempbusType.getText().contains(Expected_Result)){
						//	System.out.println("S"+tempbusType.getText());
						sameTravels=sameTravels+1;
					}
					else{
						//	System.out.println("F"+ tempbusType.getText());
						notSameTravels=notSameTravels+1;
					}
				}	
				if(notSameTravels==0 && sameTravels!=0){
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass - Travels Sort was successful");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass - Travels Sort was successful");
				}
				else{
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail - Travels Sort was not successful");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail - Travels Sort was not successful");
					Snapshot(TC_ID, Repetition_Order);
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
					Assert.fail("Test Fail - Travels Sort was not successful");
				}
				closeBrowser();
			}		
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			closeBrowser();
			sAssert.fail("Test Fail -" + "Test Fail - due to exception"+"---"+e);
		}
		sAssert.assertAll();
	}

	@Test(dataProvider="dp_getBusResult_Seat_View", dataProviderClass=com.poc.dataprovider.DataProvider_BusResult.class,priority=3, groups={"Regression"})
	public void testBusResulSeatView(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Travels, String Expected_Result)
	{
		try {
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);
			log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);

			BusSearch(TC_ID, Repetition_Order,Browser,Search_From, Search_To,Search_Joureny);
			PageObject_BusResult poBResult= new PageObject_BusResult(webDriver);
			poBResult.getProviderFilter(Travels);	
			//WebElement travelName = webDriver.findElements(By.xpath(".//*[@id='onwardTrip']/div[2]/ul/li/div/div[2]/h4[contains(text(),'"+Travels+"')]"));

			List<WebElement> travelName = webDriver.findElements(By.xpath(".//*[@id='onwardTrip']/div[2]/ul/li/div/div[2]/h4"));
			if(	travelName.size()==0){
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- No Travels found");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- No Travels found");	
				closeBrowser();
			}
			else{
				int sameTravels =0;
				int notSameTravels=0;
				for (WebElement temptravelName : travelName) {
					if(temptravelName.getText().equalsIgnoreCase(Expected_Result)){
						//	System.out.println(temptravelName.getText());
						sameTravels=sameTravels+1;
					}
					else{
						//	System.out.println(temptravelName.getText());
						notSameTravels=notSameTravels+1;
					}
				}	
				if(notSameTravels==0 && sameTravels!=0){
					Thread.sleep(2000);
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Travels Sort was successful");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Travels Sort was successful");
					poBResult.viewBusSeat();
					Thread.sleep(3000);
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- View Bus Seat");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- View Bus Seat");
					poBResult.selectSeat(1);
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Seat Selected");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Seat Selected");
					poBResult.selectBoardingPoint();
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Boarding Point Selected");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Boarding Point Selected");
					poBResult.contNxtButton();
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Continue Next Page");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Continue Next Page");
					poBResult.NotNowforReturnJourney();
				}
				else{
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Travels Sort was not successful");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Travels Sort was not successful");
					Snapshot(TC_ID, Repetition_Order);
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
					Assert.fail("Test Fail - Travels Sort was not successful");
				}
				implicitlyWait(30);
				closeBrowser();
			}
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			closeBrowser();
			sAssert.fail("Test Fail -" + "Test Fail - due to exception"+"---"+e);
		}	
		sAssert.assertAll();
	}
	
	@Test(dataProvider="dp_getBusResult_Next_Day", dataProviderClass=com.poc.dataprovider.DataProvider_BusResult.class,priority=4, groups={"smoke"})
	public void testBusResulNextDay(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Travels, String Expected_Result)
	{
		try{
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);

			BusSearch(TC_ID, Repetition_Order,Browser,Search_From, Search_To,Search_Joureny);
			PageObject_BusResult poBResult= new PageObject_BusResult(webDriver);
			implicitlyWait(20);
			poBResult.nextDay();
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "Next Day clicked");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "Next Day clicked");
			implicitlyWait(20);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Date parseExpDate = sdf.parse(Search_Joureny);

			Calendar cal = Calendar.getInstance();
			cal.setTime(parseExpDate);
			cal.add(Calendar.DATE, 1); //minus number would decrement the days
			Date Exptime = cal.getTime();

			String getdateDoubleOnward = poBResult.getdateDoubleOnward();
			System.out.println(getdateDoubleOnward);

			Date parseActDate = sdf.parse(getdateDoubleOnward);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(parseActDate);
			Date Acttime = cal1.getTime();
			System.out.println(Acttime);

			int compareTo = Exptime.compareTo(Acttime);
			if(compareTo==0){
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass--- Date is matching");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass--- Date is matching");
			}
			else{
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail--- Date is not matching");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail--- Date is not matching");
				Snapshot(TC_ID, Repetition_Order);
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				Assert.fail("Test Fail--- Date is not matching");
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



	@Test(dataProvider="dp_getBusResult_Previous_Day", dataProviderClass=com.poc.dataprovider.DataProvider_BusResult.class,priority=5, groups={"smoke"})
	public void testBusResulPreviousDay(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Travels, String Expected_Result)
	{
		try{
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels+"-Expected_Result-"+Expected_Result);

			BusSearch(TC_ID, Repetition_Order,Browser,Search_From, Search_To,Search_Joureny);
			PageObject_BusResult poBResult= new PageObject_BusResult(webDriver);
			implicitlyWait(20);
			poBResult.previousDay();
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "Previous Day clicked");
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "Previous Day clicked");
			implicitlyWait(20);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Date parseExpDate = sdf.parse(Search_Joureny);


			Calendar cal = Calendar.getInstance();
			cal.setTime(parseExpDate);
			cal.add(Calendar.DATE, -1); //minus number would decrement the days
			Date Exptime = cal.getTime();

			String getdateDoubleOnward = poBResult.getdateDoubleOnward();
			System.out.println(getdateDoubleOnward);

			Date parseActDate = sdf.parse(getdateDoubleOnward);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(parseActDate);
			Date Acttime = cal1.getTime();
			System.out.println(Acttime);

			int compareTo = Exptime.compareTo(Acttime);
			if(compareTo==0){
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass--- Date is matching");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Pass--- Date is matching");
			}
			else{
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail--- Date is not matching");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail--- Date is not matching");
				Snapshot(TC_ID, Repetition_Order);
				Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Test Fail Snapshot captured.");
				Assert.fail("Test Fail--- Date is not matching");
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



	public void BusSearch(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny)		
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
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			Snapshot(TC_ID, Repetition_Order+"-"+e);
		}
	}
}

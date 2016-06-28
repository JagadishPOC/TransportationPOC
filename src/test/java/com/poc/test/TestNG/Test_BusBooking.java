package com.poc.test.TestNG;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.poc.constants.CommonConstants;
import com.poc.genericComponent.SeleniumComponents;
import com.poc.redBus.pageObjectpattern.PageObject_BusBooking;
import com.poc.redBus.pageObjectpattern.PageObject_BusResult;
import com.poc.redBus.pageObjectpattern.PageObject_BusSearch;

public class Test_BusBooking extends SeleniumComponents  {

	public static Logger log= Logger.getLogger(Test_BusBooking.class);
	SoftAssert sAssert= new SoftAssert();
	Properties properties = new Properties();

	@Test(dataProvider="dp_BusBooking_CustomerData", dataProviderClass=com.poc.dataprovider.DataProvider_BusBooking.class,priority=1, groups={"smoke", "Regression"})
	public void testBusBooking_CustomerData(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Travels, String Customer_Name,String Customer_emailid,String Customer_phone,String Customer_Gender)		
	{
		try{
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels);
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"-Browser-"+Browser+"-Search_Jourenye-"+Search_Joureny+"-Travels-"+Travels);

			testBusBooking(TC_ID,Repetition_Order,Browser,Search_From, Search_To,Search_Joureny, Travels);
			implicitlyWait(50);
			PageObject_BusBooking poBBooking= new PageObject_BusBooking(webDriver);
			poBBooking.setCustomerName(Customer_Name);

			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"--Customer Name Entered");
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"--Customer Name Entered");

			poBBooking.setCustomerEmailId(Customer_emailid);
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"--Customer email id Entered");
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"--Customer email id Entered");

			poBBooking.setCustomerGender(Customer_Gender);
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"--Customer Gender Entered");
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"--Customer Gender Entered");

			poBBooking.setCustomerPhone(Customer_phone);
			Reporter.log("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"--Customer Phone# Entered");
			//log.info("Starting Test Case-"+TC_ID+"-Repetition Sequence-" +Repetition_Order+"--Customer Phone# Entered");
			implicitlyWait(30);
			closeBrowser();
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			closeBrowser();
			Assert.fail("Test Fail - Customer information is not entered");
		}		
		sAssert.assertAll();

	}



	public void testBusBooking(String TC_ID, String Repetition_Order,String Browser,String Search_From, String Search_To,String Search_Joureny, String Travels)		
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

			PageObject_BusResult poBResult= new PageObject_BusResult(webDriver);
			poBResult.getProviderFilter(Travels);
			Thread.sleep(3000);
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
					if(temptravelName.getText().equalsIgnoreCase(Travels)){
						//	System.out.println(temptravelName.getText());
						sameTravels=sameTravels+1;
					}
					else{
						//	System.out.println(temptravelName.getText());
						notSameTravels=notSameTravels+1;
					}

				}	
				if(notSameTravels==0 && sameTravels!=0){
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Travels Sort was successful");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---Travels Sort was successful");
					Thread.sleep(5000);
					poBResult.viewBusSeat();
					Thread.sleep(5000);
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- View Bus Seat");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- View Bus Seat");
					poBResult.selectSeat(1);
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Seat Selected");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Seat Selected");
					Thread.sleep(5000);
					poBResult.selectBoardingPoint();
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Boarding Point Selected");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Boarding Point Selected");
					Thread.sleep(5000);
					poBResult.contNxtButton();
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Continue Next Page");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "---  Continue Next Page");
					Thread.sleep(5000);
					poBResult.NotNowforReturnJourney();
				}
				else{
					Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Travels Sort was not successful");
					//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Travels Sort was not successful");
					Snapshot(TC_ID, Repetition_Order);
				}
				implicitlyWait(30);
			}
		}
		catch(Exception e){
			Reporter.log("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			//log.info("Starting Test Case---"+TC_ID+"---Repetition Sequence---" +Repetition_Order+ "--- Exception -" +e);
			Snapshot(TC_ID, Repetition_Order+"-"+e); 
		}
	}
}

package com.poc.test.TestNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.naming.AuthenticationException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.poc.constants.CommonConstants;
import com.poc.helpers.RedBusDTO;
import com.poc.helpers.RootObject;
import com.poc.redBus.clients.AbstractSeattleTest;
import com.poc.redBus.utils.RedBusSuiteUtils;
import com.poc.redBus.utils.RestTestClient;

public class RedBus_SeatLayoutAPI_Tests extends AbstractSeattleTest {

	protected RestTestClient restClient;
	private String seat_available_api_payload;
	Properties properties;
	RootObject rebBusAPI;
	RedBusDTO redbusResultAPI;
	//RedBusDTO.Data dataList;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws IOException, AuthenticationException {
		restClient = new RestTestClient();
		RedBusSuiteUtils.configSuiteUtils(getScheme(), getHost(), getPort(), getBaseVersion(), 
				getMessageApiNewVersion(), getTenantId());
		properties = new Properties();
		rebBusAPI = new RootObject();
		redbusResultAPI = new RedBusDTO();
		
		InputStream inStream = null;
		String activitydataPath = System.getProperty("user.dir") + org.apache.commons.io.FilenameUtils.separatorsToSystem(CommonConstants.DATA_LOCATION_SEAT_PALYLOAD);
		if (System.getProperty(CommonConstants.KEY_AR_SEAT_AVAILABLE_JSON_FILE) == null){
			String filepath = activitydataPath + CommonConstants.KEY_AR_SEAT_AVAILABLE_JSON_FILE;
			inStream = new FileInputStream(filepath);
			seat_available_api_payload = IOUtils.toString(inStream);
		}
	}
	
	public HashMap<String, String> getRedBusCodesForLcoations(String searchFrom, String searchTo) throws Exception {

		String search_From_Name = null;
		String search_To_Name = null;
		HashMap<String, String> locationsMap = new HashMap<String, String>();

		String path = System.getProperty("user.dir")+ CommonConstants.DATA_LOCATION;
		properties.load(new FileInputStream(path));
		search_From_Name = properties.getProperty(searchFrom);
		search_To_Name   = properties.getProperty(searchTo);

		locationsMap.put("Search_From", search_From_Name);
		locationsMap.put("Search_To", search_To_Name);

		System.out.println("From location name..: " +searchFrom);
		System.out.println("To   location name..: " +searchTo);
		return locationsMap;
	}

	public String getRedBusRouteCode(String searchFrom, String searchTo) throws Exception {

		String routeId = null;

		String rt = searchFrom+ "_"+searchTo.trim();

		String path = System.getProperty("user.dir")+ CommonConstants.DATA_LOCATION_ROUTE_CODES;
		properties.load(new FileInputStream(path));
		routeId = properties.getProperty(rt);

		System.out.println("Route ID is..: " +routeId);

		return routeId;
	}


	@Test(dataProvider="dp_BusBooking_CustomerData", dataProviderClass=com.poc.dataprovider.DataProvider_BusBooking.class,priority=1, groups={"smoke", "Regression"})
	public void testBusSeat_Layout(String TC_ID, String Repetition_Order,String Browser, String 
			Search_From, String Search_To,String Search_Joureny,String Travels, String Customer_Name,String Customer_emailid,String Customer_phone,String Customer_Gender) throws Exception {

		String sourceId = Search_From;
		String destinationId = Search_To;
		String doj = Search_Joureny;
		
		String routeId=  getRedBusRouteCode(sourceId, destinationId);
		
		HashMap<String, String> locationsNamesMap = getRedBusCodesForLcoations(sourceId, destinationId);	

		String search_From = locationsNamesMap.get("Search_From");
		String search_To = locationsNamesMap.get("Search_To");

		HttpResponse searchResult = getBusSeatLayout(restClient, routeId, search_From, search_To, doj);
		Assert.assertTrue(searchResult.getStatusLine().getStatusCode()==200, "No Seats available");

		String busSearchResult = getResponseBody(searchResult);
		System.out.println("Bus seat layout result.."+ busSearchResult);
	}
	
	@Test(/*dataProvider="dp_BusBooking_CustomerData", dataProviderClass=com.poc.dataprovider.DataProvider_BusBooking.class,priority=1, groups={"smoke", "Regression"}*/)
	public void testBusSeat_Available(/*String TC_ID, String Repetition_Order,String Browser, String 
		Search_From, String Search_To,String Search_Joureny,String Travels, String Expected_Result*/) throws Exception {

		HttpResponse searchResult = getBusSeatAvailable(restClient, seat_available_api_payload);
		Assert.assertTrue(searchResult.getStatusLine().getStatusCode()==200, "No Seats available");

		String busSearchResult = getResponseBody(searchResult);
		System.out.println("Bus seat layout result.."+ busSearchResult);
	}
}

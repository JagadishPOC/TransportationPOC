package com.poc.test.TestNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.AuthenticationException;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.poc.constants.CommonConstants;
import com.poc.helpers.RedBusDTO;
import com.poc.helpers.RootObject;
import com.poc.helpers.RootObject.Datum;
import com.poc.redBus.clients.AbstractSeattleTest;
import com.poc.redBus.utils.RedBusSuiteUtils;
import com.poc.redBus.utils.RestTestClient;

public class RedBus_ResultAPI_Tests extends AbstractSeattleTest {

	protected RestTestClient restClient;
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

	public String getRedBusServiceNames(String TC_ID, String expectedServiceName) throws Exception {
		String serviceNameSelected = null;
		if (expectedServiceName.equals(CommonConstants.SERVICE_NAME_TRAVEL_NAME_001)) {	
			serviceNameSelected = CommonConstants.SERVICE_NAME_TC_BRV_001;	
		}
		else if(expectedServiceName.equals(CommonConstants.SERVICE_NAME_TRAVEL_NAME_002)){
			serviceNameSelected = CommonConstants.SERVICE_NAME_TC_BRV_002;
		}
		else if(expectedServiceName.equals(CommonConstants.SERVICE_NAME_TRAVEL_NAME_003)){
			serviceNameSelected = CommonConstants.SERVICE_NAME_TC_BRV_003;
		}
		return serviceNameSelected;
	}


	@Test(dataProvider="dp_getBusResult_Filter_Provider", dataProviderClass=com.poc.dataprovider.DataProvider_BusResult.class,priority=1, groups={"Regression"})
	public void bus_Result_Filter_Provider_tests(String TC_ID, String Repetition_Order,String Browser, String 
			Search_From, String Search_To,String Search_Joureny,String Travels, String Expected_Result) throws Exception {

		HashMap<String, String> locationsNamesMap = getRedBusCodesForLcoations(Search_From, Search_To);	

		String search_From = locationsNamesMap.get("Search_From");
		String search_To = locationsNamesMap.get("Search_To");
		String expectedServiceName = Expected_Result;

		HttpResponse searchResult = getBusSearch(restClient, search_From, search_To, Search_Joureny);
		Assert.assertTrue(searchResult.getStatusLine().getStatusCode()==200, "No Buses found in search");
		
		String busSearchResult = getResponseBody(searchResult);
		System.out.println("Bus search result.."+ busSearchResult);
		busSearchResult = busSearchResult.replace("﻿", "").trim();

		rebBusAPI = unmarshall(busSearchResult, RootObject.class);
		System.out.println(rebBusAPI.getData().get(0).getServiceName());
		
		HashSet<String> serviceNameSet = new HashSet<String>();
		String serviceName = null;
		String serviceNameFromResponse = getRedBusServiceNames(TC_ID, expectedServiceName);
		Iterator<Datum> resultLt = rebBusAPI.getData().iterator();
		while(resultLt.hasNext()){
			RootObject.Datum datum = (RootObject.Datum) resultLt.next();
			serviceName = datum.getServiceName();
			serviceNameSet.add(serviceName);
			/*if(serviceName.equals(expectedServiceName)){
				serviceNameSet.add(serviceName);
				break;
			}*/
		}
		System.out.println(serviceNameSet);
		Assert.assertTrue(serviceNameSet.contains(serviceNameFromResponse), "Service name not found..");
		//Assert.assertEquals(serviceNameFromResponse, expectedServiceName); 
	}
}

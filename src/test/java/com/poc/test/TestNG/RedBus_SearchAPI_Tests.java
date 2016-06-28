package com.poc.test.TestNG;

import java.io.FileInputStream;
//import java.io.FileWriter;
import java.io.IOException;
/*import java.net.URI;
import java.net.URISyntaxException;*/
import java.util.HashMap;
/*import java.util.List;
import java.util.Map;*/
import java.util.Properties;

import javax.naming.AuthenticationException;

//import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.ITestResult;
/*import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;*/
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.poc.constants.CommonConstants;
import com.poc.helpers.RootObject;
import com.poc.helpers.TestCaseResult;
import com.poc.redBus.clients.AbstractSeattleTest;
//import com.poc.redBus.clients.SeattleTestBase;
import com.poc.redBus.utils.RedBusSuiteUtils;
import com.poc.redBus.utils.RestTestClient;
import com.poc.redBus.utils.TestUtils;

/*import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody*/;


public class RedBus_SearchAPI_Tests extends AbstractSeattleTest {

	protected RestTestClient restClient;
	Properties properties;
	RootObject rebBusAPI;
	private HashMap<String, TestCaseResult> resultSummary;
	//List<String> scenariosList =null;


	@BeforeClass/*(alwaysRun = true)*/
	public void setUp() throws IOException, AuthenticationException {
		restClient = new RestTestClient();
		properties = new Properties(); 
		RedBusSuiteUtils.configSuiteUtils(getScheme(), getHost(), getPort(), getBaseVersion(), 
				getMessageApiNewVersion(), getTenantId());
		rebBusAPI = new RootObject();
		resultSummary = new HashMap<String ,TestCaseResult>();
	}

	/*@AfterMethod*/
	public void result(ITestResult result) {

		TestCaseResult testCaseResult = new TestCaseResult();						
		if(/*!scenariosList.contains(result.getMethod().getMethodName().trim()) &&*/ TestUtils.ifNotNull(result.getAttribute("ResponseBody"),result.getAttribute("ResponseCode"), result.getAttribute("Url"),result.getAttribute("ResponseTime"),result.getAttribute("requestMethod"))){
			testCaseResult.setStatus(String.valueOf(result.getStatus()));
			testCaseResult.setTestCase(result.getMethod().getDescription());
			testCaseResult.setResponseBody(String.valueOf(result.getAttribute("ResponseBody")));
			testCaseResult.setResponseCode(String.valueOf(result.getAttribute("ResponseCode")));		
			testCaseResult.setUrl(String.valueOf(result.getAttribute("Url")));					
			testCaseResult.setRequestMethod(String.valueOf(result.getAttribute("requestMethod")));

			testCaseResult.setRequestBody("");
			resultSummary.put(testCaseResult.getTestCase(),testCaseResult);
		}
	}
	public synchronized void differentScenarioReporter(ITestResult result, String version){
		TestCaseResult testCaseResult = new TestCaseResult();
		if(TestUtils.ifNotNull(result.getAttribute("ResponseBody"),result.getAttribute("ResponseCode"), result.getAttribute("Url"),result.getAttribute("ResponseTime"),result.getAttribute("requestMethod"))){
			testCaseResult.setStatus(String.valueOf(result.getStatus()));
			testCaseResult.setTestCase(result.getMethod().getDescription()+" For Version "+ version);
			testCaseResult.setResponseBody(String.valueOf(result.getAttribute("ResponseBody")));
			testCaseResult.setResponseCode(String.valueOf(result.getAttribute("ResponseCode")));		
			testCaseResult.setUrl(String.valueOf(result.getAttribute("Url")));		
			testCaseResult.setResponseTime(String.valueOf(result.getAttribute("ResponseTime"))+" Milliseconds");
			testCaseResult.setRequestMethod(String.valueOf(result.getAttribute("requestMethod")));
			testCaseResult.setRequestBody("");
			//testCaseResult.setHeaders(getHeaderFromCall(result.getAttribute("Header")));
			resultSummary.put(testCaseResult.getTestCase(),testCaseResult);
		}
	}



	public HashMap<String, String> getRedBusCodesForLcoations(String searchFrom, String searchTo) throws Exception {

		String search_From_Name = null;
		String search_To_Name = null;
		HashMap<String, String> locationsMap = new HashMap<String, String>();
		
		if(searchTo.equals("Bangalore Intl Airport")){
			
			searchTo = searchTo.replaceAll(" ", "_");
		}

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


	@Test(description="Bus  Valid Search", dataProvider="dp_getValidBusSearchData", dataProviderClass=com.poc.dataprovider.DataProvider_BusSearch.class,priority=1)
	public void busSearch_Valid_tests(String TC_ID, String Repetition_Order,String Browser, String 
			Search_From, String Search_To,String Search_Joureny, String Expected_Result) throws Exception {

		HashMap<String, String> locationsNamesMap = getRedBusCodesForLcoations(Search_From, Search_To);	

		String search_From = locationsNamesMap.get("Search_From");
		String search_To = locationsNamesMap.get("Search_To");

		//ITestResult result = Reporter.getCurrentTestResult();

		//long startTime = System.currentTimeMillis();
		HttpResponse searchResult = getBusSearch(restClient, search_From, search_To, Search_Joureny);
		/*long endTime = System.currentTimeMillis();
		result.setAttribute("Url", RedBusSuiteUtils.generateBusSearchUrl(search_From, search_To, Search_Joureny));
		result.setAttribute("ResponseBody", getResponseBody(searchResult));
		result.setAttribute("ResponseCode", String.valueOf(searchResult.getStatusLine().getStatusCode()));
		result.setAttribute("ResponseTime", String.valueOf(endTime-startTime));
		result.setAttribute("Header", SeattleTestBase
				.getHeader(new BasicNameValuePair("content-type",
						"application/json")));
		result.setAttribute("requestMethod", "GET");*/

		Assert.assertTrue(searchResult.getStatusLine().getStatusCode()==200, "No Buses found in search"); 
		String busSearchResult = getResponseBody(searchResult);
		System.out.println("Bus search result.."+ busSearchResult);
	}

	@Test(description="Bus  In valid Search", dataProvider="dp_getInvalidBusSearchData", dataProviderClass=com.poc.dataprovider.DataProvider_BusSearch.class,priority=1)
	public void busSearch_Invalid_tests(String TC_ID, String Repetition_Order,String Browser, String 
			Search_From, String Search_To,String Search_Joureny, String Expected_Result) throws Exception {

		HashMap<String, String> locationsNamesMap = getRedBusCodesForLcoations(Search_From, Search_To);	
		String search_From = locationsNamesMap.get("Search_From");
		String search_To = locationsNamesMap.get("Search_To");

		HttpResponse searchResult = getBusSearch(restClient, search_From, search_To, Search_Joureny); 
		String busSearchResult = getResponseBody(searchResult);
		System.out.println("Bus search result.."+ busSearchResult);
		busSearchResult = busSearchResult.replace("﻿", "").trim();

		rebBusAPI = unmarshall(busSearchResult, RootObject.class);
		int apiSTATUS = rebBusAPI.getStatus();
		Assert.assertTrue(apiSTATUS == 400, "No Buses found in search");
	}

	@Test(description="Not yet open Bus Search data", dataProvider="dp_getNotYetOpenBusSearchData", dataProviderClass=com.poc.dataprovider.DataProvider_BusSearch.class,priority=1)
	public void busSearch_NotYetOpenSearch_tests(String TC_ID, String Repetition_Order,String Browser, String 
			Search_From, String Search_To,String Search_Joureny, String Expected_Result) throws Exception {

		HashMap<String, String> locationsNamesMap = getRedBusCodesForLcoations(Search_From, Search_To);	
		String search_From = locationsNamesMap.get("Search_From");
		String search_To = locationsNamesMap.get("Search_To");

		HttpResponse searchResult = getBusSearch(restClient, search_From, search_To, Search_Joureny); 
		String busSearchResult = getResponseBody(searchResult);
		System.out.println("Bus search result.."+ busSearchResult);
		busSearchResult = busSearchResult.replace("﻿", "").trim();

		rebBusAPI = unmarshall(busSearchResult, RootObject.class);
		int apiSTATUS = rebBusAPI.getStatus();
		String apiMESSAGE = "Search made before booking opened";
		Assert.assertTrue(apiSTATUS == 400, "No Buses found in search");
		Assert.assertTrue(rebBusAPI.getMessage().equals(apiMESSAGE),
				"Oops! bookings have not yet opened for this route");
	}

	/*@AfterClass(alwaysRun = true)*/
	/*public synchronized void generateReport() throws Exception {
		
		System.out.println("Start of report generating..");

		FileWriter writer = new FileWriter("SanityReport.html");
		String htmlReport = "";
		//boolean anyFailure = false;
		int serialNo	=	1;

		htmlReport = "<html>" +"<head><style type=\"text/css\"> table { margin: 1em; border-collapse: collapse; } .BreakWord { word-break: break-all; } .instruction {border-width:2px; border-style:solid; padding: 10px} tr.fail{background: #FF0000} tr.pass{background: #FFFFFF} td {word-wrap:break-word; padding: .3em; border: 1px #ccc solid;overflow: hidden;} th { padding: .3em; border: 1px #ccc solid; } thead { background: #fc9; } </style></head>" +"<body>";
		htmlReport = htmlReport + "<table> " +"<thead> " +"<tr>" +"<th> S.No </th>" +"<th>Service Call</th> " +"<th>Url</th>" +"<th>ResponseTime</th>" +"<th>Response Code</th>" +"<th>Request Body"+"<th>Response Body" +"</tr>" +"</thead>" +"<tbody>";

		for(Map.Entry<String, TestCaseResult> entry : resultSummary.entrySet()){			
			TestCaseResult result = entry.getValue();
			if(result.getStatus().equals("2")) {
				anyFailure = true;
			htmlReport = htmlReport + "<tr class=\"fail\"><td>"+ serialNo++ +" </td>" +"<td>"+result.getTestCase();						
			htmlReport = htmlReport +"</td><td class=\"BreakWord\">"+result.getRequestMethod()+" "+result.getUrl()+"<br><br>"+result.getHeaders()+"</td>" +"<td>"+result.getResponseTime()+"</td>" +"<td>"+result.getResponseCode()+"</td>" +"<td>"+result.getResponseBody()+"</td>" +"</tr>";
			}			
		}

		htmlReport += "</tbody>" +"</table><br/>";
		htmlReport += "<div class=\"instruction\"><h3>Instructions to run backend sanity</h3>" +"<ul><li>Go to ";

		if(getTestEnv().equals("redBus.aptimus"))
			htmlReport+="<a href=\"E:/JAVA/Apollo/TechnothonPOC/SanityReport.html/\">";		
		else 
			htmlReport+="No reports";

		htmlReport += "</body> </html>";
		writer.write(htmlReport);
		writer.flush();
		writer.close();
		
		sendEmail(htmlReport);
	}*/

	/*public void  sendEmail(String body) {
		
		System.out.println("Start of sending email.."); 

		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials credentials = new WebCredentials("jagadish.mrb@gmail.com", "ganesha@29");
		service.setCredentials(credentials);
		try {
			service.setUrl(new URI("https://outlook.office365.com/EWS/exchange.asmx"));
			EmailMessage msg = new EmailMessage(service);
			msg.setSubject("BE Sanity Report");
			msg.setBody(MessageBody.getMessageBodyFromText(body));
			msg.getToRecipients().add("jagadish.mrb@gmail.com");
			msg.send();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Report sent sucessfully.."); 
	}*/
}

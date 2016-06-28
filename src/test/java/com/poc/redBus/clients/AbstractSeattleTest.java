package com.poc.redBus.clients;

import java.net.URI;
import java.util.ArrayList;






import org.apache.http.HttpEntity;
/*import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.DefaultHttpParams;*/
//import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.message.BasicNameValuePair;
/*import org.joda.time.DateTime;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;*/


import org.testng.Reporter;

import com.poc.redBus.clients.CommonTest;
import com.poc.redBus.utils.RestTestClient;
import com.poc.redBus.utils.RedBusSuiteUtils;
//import com.aptimus.careers.dto.jobservice.JobSearchPayLoad;


public class AbstractSeattleTest extends CommonTest {

	//public static SeattleAuthClient authClient = new SeattleAuthClient();
	public HttpResponse getBusSearch(RestTestClient restClient, String fromCityId, String toCityId, String doj) throws Exception {

		ArrayList<NameValuePair> headers = SeattleTestBase
				.getHeader(new BasicNameValuePair("content-type",
						"application/json"));
		URI uri = new URI(RedBusSuiteUtils.generateBusSearchUrl(fromCityId, toCityId, doj)); 
		String msg = "API to get buses search";
		System.out.println(msg); 
		System.out.println("GET Request: "+uri.toString());
		//Reporter.log(msg);
		Reporter.log("GET Request: "+uri.toString());
		return restClient.executeRequest(RestTestClient.HttpMethodType.GET, uri, headers, null, headers, 
				null, null, null, null, true);

	}

	public HttpResponse getBusSeatLayout(RestTestClient restClient, String routeId, String sourceId, String destinationId, String doj) throws Exception {

		ArrayList<NameValuePair> headers = SeattleTestBase
				.getHeader(new BasicNameValuePair("content-type",
						"application/json"));
		URI uri = new URI(RedBusSuiteUtils.generateBusSeatLayoutUrl(routeId, sourceId, destinationId, doj)); 
		String msg = "API to get buses seat layout";
		System.out.println(msg); 
		System.out.println("GET Request: "+uri.toString());
		//Reporter.log(msg);
		Reporter.log("GET Request: "+uri.toString());
		return restClient.executeRequest(RestTestClient.HttpMethodType.GET, uri, headers, null, headers, 
				null, null, null, null, true);
	}
	
	public HttpResponse getBusSeatAvailable(RestTestClient restClient, String requestPayload) throws Exception {

		ArrayList<NameValuePair> headers = SeattleTestBase
				.getHeader(new BasicNameValuePair("content-type",
						"application/json"));
		URI uri = new URI(RedBusSuiteUtils.generateBusSeatAvailableUrl()); 
		String msg = "API to get buses seat avaiable";
		System.out.println(msg); 
		HttpEntity requestBody = new StringEntity(requestPayload);
		System.out.println("POST Request: "+uri.toString());
		//Reporter.log(msg);
		Reporter.log("POST Request: "+uri.toString());
		return restClient.executeRequest(RestTestClient.HttpMethodType.POST, uri, headers, requestBody, null, 
				null, null, null, null, true);
	}
}

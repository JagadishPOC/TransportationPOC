package com.poc.redBus.clients;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;

public class PlatformTestClient extends CommonClient{

	
	public PlatformTestClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public PlatformTestClient(String abcd) {
		super(abcd);
		// TODO Auto-generated constructor stub
	}*/

	public HttpResponse getRequest(URI uri, List<NameValuePair> headers, List<NameValuePair> matrixParams, String message) throws Exception {
		return executeRequest(GET_METHOD, uri, headers, new StringEntity("", null, null), matrixParams);
	}
	
	public HttpResponse postRequest(URI uri, List<NameValuePair> headers, List<NameValuePair> matrixParams, HttpEntity entity, String message) throws Exception {
		return executeRequest(POST_METHOD, uri, headers, entity, matrixParams);	
	}
	
	public HttpResponse putRequest(URI uri, List<NameValuePair> headers, List<NameValuePair> matrixParams, HttpEntity entity, String message) throws Exception {
		return executeRequest(PUT_METHOD, uri, headers, entity, matrixParams);	
	}
}
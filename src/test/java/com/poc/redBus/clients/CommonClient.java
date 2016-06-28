package com.poc.redBus.clients;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.testng.Reporter;



import com.poc.dataprovider.TestEnvironment;
//import com.aptimus.altcloud.platform.gateway.PlatformSSLSocketFactory;
import com.poc.reporting.PerformanceData;

public abstract class CommonClient {
	

		public static int GET_METHOD = 1;

		public static int POST_METHOD = 2;

		public static int PUT_METHOD = 3;

		public static int DELETE_METHOD = 4;

		HttpUriRequest request;
		TestEnvironment testEnvironment = TestEnvironment
				.getTestEnvironmentObject();

		@Deprecated
		public static String host;
		@Deprecated
		public int port;
		@Deprecated
		public static String protocol;
		// public static final String cookie;
		public static HttpClient httpClient;

		public CommonClient() {
			protocol = System.getProperty("networkprotocol");
			System.out.println("");
			if (protocol == null)
				protocol = "https";

			host = System.getProperty("host");
			if (host == null)
				host = "beta-classroom.qaols.phoenix.edu";

			String stringPort = System.getProperty("port");
			if (stringPort == null)
				stringPort = "80";
			port = Integer.parseInt(stringPort);
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
					.getSocketFactory()));
			schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory
					.getSocketFactory()));
			ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(
					schemeRegistry);
			cm.setMaxTotal(1000);
			cm.setDefaultMaxPerRoute(1000);

			httpClient = new DefaultHttpClient(cm);
			httpClient = WebClientWrapper.wrapClient(httpClient);

		}

		/*public CommonClient(String platform) {
			protocol = System.getProperty("networkprotocol");
			if (protocol == null)
				protocol = "https";

			host = System.getProperty("host");
			if (host == null)
				host = "beta-qa.aptimus.phoenix.edu";

			String stringPort = System.getProperty("port");
			if (stringPort == null)
				stringPort = "80";
			port = Integer.parseInt(stringPort);
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
					.getSocketFactory()));

			PlatformSSLSocketFactory platformSSLContextFactory = new PlatformSSLSocketFactory();
			schemeRegistry.register(new Scheme("https", 443,
					platformSSLContextFactory.getSSLSocketFactory()));
			ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(
					schemeRegistry);
			cm.setMaxTotal(1000);
			cm.setDefaultMaxPerRoute(1000);

			httpClient = new DefaultHttpClient(cm);

		}*/

		/*
		 * private HttpClient _getClient(){
		 * 
		 * SchemeRegistry schemeRegistry = new SchemeRegistry();
		 * schemeRegistry.register(new Scheme("http", 80,
		 * PlainSocketFactory.getSocketFactory())); schemeRegistry.register(new
		 * Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		 * ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager
		 * (schemeRegistry); cm.setMaxTotal(1000); cm.setDefaultMaxPerRoute(1000);
		 * return new DefaultHttpClient(cm);
		 * 
		 * }
		 */

		public void releaseConnection() {
			httpClient.getConnectionManager().shutdown();
		}

		/**
		 * 
		 * @param requestType
		 * @param uri
		 * @param headers
		 * @param entity
		 * @param matrixParams
		 * @return
		 * @throws ClientProtocolException
		 */
		protected HttpResponse executeRequest(int requestType, java.net.URI uri,
				List<NameValuePair> headers, HttpEntity entity,
				List<NameValuePair> matrixParams) throws ClientProtocolException {

			HttpResponse response = null;
			try {

				// sb = new StringBuffer(currentDateTime() + ", curl -v ");

				if (matrixParams == null) {
					matrixParams = new ArrayList<NameValuePair>();
				}

				Iterator<NameValuePair> iterator = matrixParams.iterator();
				while (iterator.hasNext()) {
					NameValuePair par = iterator.next();
					// sb.append(" --data \"").append(par.getName()).append(":").append(par.getValue()).append("\"");
				}

				switch (requestType) {
				case 1:
					HttpGet httpGet = new HttpGet(uri);
					request = httpGet;
					Reporter.log("GET Request:", true);
					break;
				case 2:
					HttpPost httpPost = new HttpPost(uri);
					if (entity != null) {
						httpPost.setEntity(entity);
					}
					if (matrixParams != null && matrixParams.size() > 0) {
						httpPost.setEntity(new UrlEncodedFormEntity(matrixParams,
								"UTF-8"));
					}
					request = httpPost;
					Reporter.log("POST Request:", true);
					break;
				case 3:
					HttpPut httpPut = new HttpPut(uri);
					if (entity != null)
						httpPut.setEntity(entity);
					if (matrixParams != null && !matrixParams.isEmpty())
						httpPut.setEntity(new UrlEncodedFormEntity(matrixParams,
								"UTF-8"));
					request = httpPut;
					Reporter.log("PUT Request:", true);
					break;
				case 4:
					HttpDelete httpDelete = new HttpDelete(uri);
					request = httpDelete;
					Reporter.log("DELETE Request:", true);
					break;
				}

				if (headers != null) {
					setHeaders(headers);
				}
				Timestamp requestSentTime = null;
				Timestamp responseReceived = null;
				if (testEnvironment.isPerformanceLoggingOn()) {
					requestSentTime = new Timestamp(new Date().getTime());
					Reporter.log(
							request.getURI() + "Sent Request: [" + request.getURI()
									+ "] at [" + requestSentTime + "]", true);
				}

				response = httpClient.execute(request);
				if (testEnvironment.isPerformanceLoggingOn()
						&& _isValidResponseCode(response)) {
					responseReceived = new Timestamp(new Date().getTime());
					Long responseTime = Math.abs(responseReceived.getTime()
							- requestSentTime.getTime());
					Reporter.log("Response received for request: [" + uri
							+ "] at [" + responseReceived.getTime() + "] after ["
							+ responseTime + " ms]", true);
					String uriString = request.getURI().getPath();
					PerformanceData perfData = new PerformanceData();
					perfData.setComponentName(uriString.split("/")[1]);
					perfData.setMethodType(request.getMethod());
					perfData.setResponseTime(responseTime.toString());
					perfData.setMethod(uri.toString());
					TestEnvironment.setPerfData(perfData);
				}
			} catch (Exception ioex) {
				ioex.printStackTrace();
				request.abort();
			} finally {
				httpClient.getConnectionManager().closeExpiredConnections();

			}

			return response;
		}

		private boolean _isValidResponseCode(HttpResponse respone) {
			boolean isValidResponse = false;
			switch (respone.getStatusLine().getStatusCode()) {
			case HttpStatus.SC_ACCEPTED:
			case HttpStatus.SC_CREATED:
			case HttpStatus.SC_OK:
			case HttpStatus.SC_NO_CONTENT:
				isValidResponse = true;
			}
			return isValidResponse;
		}

		/**
		 * To DO for security
		 * 
		 * @param requestType
		 * @param uri
		 * @param headers
		 * @param entity
		 * @param matrixParams
		 * @return
		 * @throws ClientProtocolException
		 */
		public HttpResponse executeRestRequest(int requestType, java.net.URI uri,
				List<NameValuePair> headers, HttpEntity entity,
				List<NameValuePair> matrixParams) throws ClientProtocolException {

			HttpResponse response = null;
			/*
			 * if (true){ response = injectXssDataAndExecuteRequest(requestType,
			 * uri, headers, entity, matrixParams); //return the response from the
			 * request without injecting any XSS
			 * 
			 * } else {
			 */

			/*
			 * System.out.println("Entty value is: "+entity);
			 */response = executeRequest(requestType, uri, headers, entity,
					matrixParams);

			// }
			return response;
		}

		/**
		 * To DO for security
		 * 
		 * @param requestType
		 * @param uri
		 * @param headers
		 * @param entity
		 * @param matrixParams
		 */
		protected void injectXssDataAndExecuteRequest(int requestType,
				java.net.URI uri, List<NameValuePair> headers, HttpEntity entity,
				List<NameValuePair> matrixParams) {

			HttpResponse response = null;
			response = injectIntoUriAndValidate(requestType, uri, headers, entity,
					matrixParams);
			response = injectIntoHeadersAndValidate(requestType, uri, headers,
					entity, matrixParams);
			response = injectIntoEntiryAndValidate(requestType, uri, headers,
					entity, matrixParams);

		}

		/**
		 * To DO for security
		 * 
		 * @param response
		 */
		private HttpResponse validateResponse(HttpResponse response) {
			HttpResponse response1 = null;

			return response1;
		}

		/**
		 * To DO for security
		 * 
		 * @param requestType
		 * @param uri
		 * @param headers
		 * @param entity
		 * @param matrixParams
		 * @return
		 */
		private HttpResponse injectIntoUriAndValidate(int requestType,
				java.net.URI uri, List<NameValuePair> headers, HttpEntity entity,
				List<NameValuePair> matrixParams) {
			HttpResponse response = null;
			/*
			 * //this would be in a loop, read from the file and inject into uri
			 * return the response from the valid arguements without injecting XSS
			 * try { response = executeRequest(requestType, uri, headers, entity,
			 * matrixParams); response = validateResponse(response); } catch
			 * (ClientProtocolException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			return response;
		}

		/**
		 * To DO for security
		 * 
		 * @param requestType
		 * @param uri
		 * @param headers
		 * @param entity
		 * @param matrixParams
		 * @return
		 */
		private HttpResponse injectIntoHeadersAndValidate(int requestType,
				java.net.URI uri, List<NameValuePair> headers, HttpEntity entity,
				List<NameValuePair> matrixParams) {
			HttpResponse response = null;
			/*
			 * try { response = executeRequest(requestType, uri, headers, entity,
			 * matrixParams); response = validateResponse(response); } catch
			 * (ClientProtocolException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			return response;

		}

		/**
		 * To DO for security
		 * 
		 * @param requestType
		 * @param uri
		 * @param headers
		 * @param entity
		 * @param matrixParams
		 * @return
		 */
		private HttpResponse injectIntoEntiryAndValidate(int requestType,
				java.net.URI uri, List<NameValuePair> headers, HttpEntity entity,
				List<NameValuePair> matrixParams) {
			HttpResponse response = null;

			// create a for loop
			// to read from the file and update the entity object and validate the
			// response with each input from the xss file
			/*
			 * try {
			 * 
			 * response = executeRequest(requestType, uri, headers, entity,
			 * matrixParams); response = validateResponse(response); } catch
			 * (ClientProtocolException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			return response;
		}

		/**
		 * 
		 * @param headers
		 */
		private void setHeaders(List<NameValuePair> headers) {
			Iterator<NameValuePair> iterator = headers.iterator();

			while (iterator.hasNext()) {
				NameValuePair header = iterator.next();
				request.setHeader(header.getName(), header.getValue());
			}
		}

		/**
		 * 
		 * @param is
		 * @return
		 */
		public byte[] toByteArray(InputStream is) {

			try {
				ByteArrayOutputStream data = new ByteArrayOutputStream();
				byte buffer[] = new byte[4096]; // 4kB
				for (int bytes = is.read(buffer, 0, buffer.length); bytes != -1; bytes = is
						.read(buffer, 0, buffer.length)) {
					data.write(buffer, 0, bytes);
				}
				return data.toByteArray();
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
			return null;
		}

		/**
		 * 
		 * @return
		 */
		public String currentDateTime() {
			Calendar cal = new GregorianCalendar();

			int year = cal.get(Calendar.YEAR); // 2002
			int month = cal.get(Calendar.MONTH); // 0=Jan, 1=Feb, ...
			int day = cal.get(Calendar.DAY_OF_MONTH); // 1...

			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			StringBuffer dateTime = new StringBuffer();
			dateTime.append(month + 1).append("/").append(day).append("/")
					.append(year).append(" ").append(hour).append(":")
					.append(minute).append(":").append(second);
			return dateTime.toString();
		}
}


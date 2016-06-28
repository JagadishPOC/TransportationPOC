package com.poc.redBus.utils;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.NameValuePair;
/*import org.joda.time.DateTime;*/

public class RedBusSuiteUtils {

	public static Random r = new Random(System.currentTimeMillis());

	//RedBus Search API:
	//https://mobapi.redbus.in/wdsvc/v1_1/bussearch?fromCityId=122&toCityId=390&doj=11-Jun-2016
	public static final String getbusSearchURL = "/wdsvc/v1_1/bussearch?fromCityId=%s&toCityId=%s&doj=%s";
	
	//Seat layout view API
	//https://mobapi.redbus.in/wdsvc/v1_1/seatlayout?rt=4299698&sourceId=122&destinationId=129&doj=18-Jun-2016
	public static final String getbusSeatLayout = "/wdsvc/v1_1/seatlayout?rt=%s&sourceId=%s&destinationId=%s&doj=%s";
	
	//Seat Select API:
	//POST
	//https://www.redbus.in/SeatStatusAPI/
	//Request Pay load:{"routeId":"8681284","dateOfJourney":"2016-06-30","selectedSeats":"L3"}
	public static final String getSeatAvailableStatus = "https://www.redbus.in/SeatStatusAPI/";

	
	private static String scheme;
	private static String host;
	private static int port;
	private static String baseVersion;
	private static String messageApiNewVersion;
	private static String tenantId;
	
		
	
	public static void configSuiteUtils(String scheme, String host, int port,
			String baseVersion, String messageApiNewVersion,String tenantId) {
		RedBusSuiteUtils.scheme = scheme;
		RedBusSuiteUtils.host = host;
		RedBusSuiteUtils.port = port;
		RedBusSuiteUtils.baseVersion = baseVersion;
		RedBusSuiteUtils.messageApiNewVersion = messageApiNewVersion;
		RedBusSuiteUtils.tenantId = tenantId;
	}
	
	public static void configSuiteUtils(String scheme, String host, int port,
			String baseVersion,String messageApiNewVersion, String rosterServiceBaseVersion, String tenantId) {
		RedBusSuiteUtils.scheme = scheme;
		RedBusSuiteUtils.host = host;
		RedBusSuiteUtils.port = port;
		RedBusSuiteUtils.baseVersion = baseVersion;
		RedBusSuiteUtils.messageApiNewVersion = messageApiNewVersion;
		RedBusSuiteUtils.tenantId = tenantId;
	}

	public static String getHost() {
		return host;
	}

	public static String getScheme() {
		return scheme;
	}

	public static void setScheme(String scheme) {
		RedBusSuiteUtils.scheme = scheme;
	}

	public static void setHost(String host) {
		RedBusSuiteUtils.host = host;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		RedBusSuiteUtils.port = port;
	}

	public static String getBaseVersion() {
		return baseVersion;
	}

	public static void setBaseVersion(String baseVersion) {
		RedBusSuiteUtils.baseVersion = baseVersion;
	}
	public static String getMessageApiNewVersion() {
		return messageApiNewVersion;
	}

	public static void setMessageApiNewVersion(String messageApiNewVersion) {
		RedBusSuiteUtils.messageApiNewVersion = messageApiNewVersion;
	}
	

	public static String getTenantId() {
		return tenantId;
	}

	public static void setTenantId(String tenantId) {
		RedBusSuiteUtils.tenantId = tenantId;
	}
	
	public static String generateBusSearchUrl(String fromCityId, String toCityId, String doj) {
		return generateHostString()
				+ String.format(getbusSearchURL,fromCityId, toCityId, doj);
	}
	
	public static String generateBusSeatLayoutUrl(String routeId, String sourceId, String destinationId, String doj) {
		return generateHostString()
				+ String.format(getbusSeatLayout,routeId, sourceId, destinationId, doj);
	}
	
	public static String generateBusSeatAvailableUrl() {
		return /*generateHostString()
				+ */String.format(getSeatAvailableStatus);
	}
	
	public static String generateHostString() {
		return getScheme() + "://" + getHost()
				+ (getPort() != 0 ? ":" + getPort() : "");
	}

	public static String generateQueryParameters(
			HashMap<String, String> queryParams) {
		if (queryParams == null)
			return "";
		if (queryParams.size() == 0)
			return "";
		String retval = new String();

		for (String key : queryParams.keySet()) {
			retval += key + "=" + queryParams.get(key) + "&";
		}
		return retval.substring(0, retval.length() - 1);
	}
	
	public static String generateQueryParameters(
			ArrayList<NameValuePair> queryParams) {
		if (queryParams == null)
			return "";
		if (queryParams.size() == 0)
			return "";
		String retval = new String();

		for (NameValuePair pair : queryParams) {
			retval += pair.getName() + "=" + pair.getValue() + "&";
		}
		return retval.substring(0, retval.length() - 1);
	}

	public static String generateQueryParameters(String paramName,
			ArrayList<String> params) {
		if (params.size() == 0)
			return "";
		String retval = new String();
		for (String param : params) {
			retval += paramName + "=" + param + "&";
		}

		return retval.substring(0, retval.length() - 1);
	}

	public static String encodeString(String stringToEncode) {
		return URLEncoder.encode(stringToEncode);
	}

	public static String getCurrentTimestamp() {
		Date now = new Date();
		return getTimestamp(now);

	}

	public static String getTimestamp(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return sdf.format(d);
	}
	

	public String generateRandomPost() {
		return "AUTOMATION_" + System.currentTimeMillis();
	}

	public char[] getRandomNonAsciiString(int length) {

		char[] retval = new char[length];

		for (int i = 0; i < length; i++) {
			retval[i] = (char) (r.nextInt(128) + 128);
		}
		return retval;
	}
	
	

	
	
	public static String getRandomalphanumericNumber(int numOfCharacters) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4',
                '5', '6', '7', '8', '9', '0' };
        for (int i = 0; i < numOfCharacters; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }
}

package com.poc.constants;

public class CommonConstants {


	//use this to turn on or off debug 
	
	public static final String PERF_LOGGING_ON = "false";
	public static final String DEBUG_ON = "false";
	
	//selenium server info for apolloasertions
	public static final String KEY_SELENIUM_SERVER = "seleniumServer";
	public static final String KEY_SELENIUM_PORT = "seleniumPort";
    
   //Default configuration File to provide setup  information 
    public static final String CONFIG_LOCATION = "\\src\\test\\java\\com\\poc\\redBus\\configuration\\";
    public static final String DATA_LOCATION = "\\src\\test\\java\\com\\poc\\redBus\\configuration\\redBusNumber_codes.properties";
    public static final String DATA_LOCATION_UI = "\\src\\test\\resources\\config.properties";
    public static final String DATA_LOCATION_UI_XLS = "\\src\\test\\resources\\TestData\\redBus_BusSearch.xls";
    public static final String DATA_LOCATION_SEAT_PALYLOAD = "\\src\\test\\resources\\TestData\\Seat_API\\";
    public static final String DATA_LOCATION_ROUTE_CODES = "\\src\\test\\java\\com\\poc\\redBus\\configuration\\redBusRoute_codes.properties";
	public static final String DATA_COMMON = "common";
	
	public static final String SSO_PROP_FILENAME = "sso.properties";
	
	//system property
	public static final String USER_DIR = "user.dir";
    public static final String CONFIGURATION_FILE_DEFAULT = "configuration.xml";
    
    //Testing Environment - 
    public static final String KEY_TEST_ENVIRONMENT = "testEnv";
    public static final String KEY_HEADER_CONNECTION = "connectionHeader";

    
    public static final String HEADER_ACCEPT = "Accept" ;
    public static final String HEADER_COOKIE = "Cookie" ;
    public static final String HEADER_CONNECTION = "Connection";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CONTENT_TYPE = "Content-type";
    public static final String HEADER_X_REQUESTED_WITH = "X-Requested-With";
    public static final String HEADER_X_APOLLOGROUP_EDU_VLP_CONTEXT = "x-apollogroup-edu-vlp-context";
    public static final String HEADER_SECURITY_XTEST = "x-test";
	public static final String HEADER_ACCEPT_CHARSET = "Accept-Charset";
	public static final String HEADER_APPLICATION_OCTET_STREAM = "application/octet-stream";
	public static final String HEADER_ETAG = "Etag";
	public static final String HEADER_IF_NONE_MATCH = "If-None-Match";
	public static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
	public static final String HEADER_ACCEPT_AAPLICATION_JSON = "Application/json";
	public static final String HEADER_ACCEPT_AAPLICATION_XML = "Application/xml";
    public static final String CHAR_SET = "UTF-8";
    
    //REDBUS Configurations
    public static final String TEST_ENVIRONMENT_REDBUS_APTIMUS = "redBus.aptimus";
    public static final String KEY_NETWORK_PROTOCOL_REDBUS_APTIMUS = "redBus.networkprotocol";
    public static final String KEY_HOST_REDBUS_APTIMUS = "redBus.host";
    public static final String KEY_PORT_REDBUS_APTIMUS = "redBus.port";

    public static final int REQUEST_GET = 1;
    public static final int REQUEST_POST = 2;
    public static final int REQUEST_PUT = 3;
    public static final int REQUEST_DELETE = 4;
    
    //Authentication and Authorization
    public static final String KEY_AUTH_TYPE = "authType";
    public static final String AUTH_OAUTH = "Oauth";
    public static final String AUTH_APOLLO_ASSERTIONS = "apolloassertions";
    public static final String AUTH_OAUTH_APOLLO_ASSERTIONS = "oauthAndApolloAsserions";
    
	public static final String BASE_VERSION_PLACEHOLDER = "#version#";
	public static final String TENANTID_PLACEHOLDER = "#tenantId#";
	public static final String HOST_PLACEHOLDER = "#host#";
	public static final String PORT_PLACEHOLDER = "#port#";
	public static final String QUERY_PARAM_DELIMETER_PLACEHOLDER = "#?#";
	public static final String URL_PLACEHOLDER = "#url#";
	public static final String QUERY_PARAM_DELIMETER = "&";
	public static final String BASE_CONTEXT_NAME_PLACEHOLDER = "#contextName#";
	public static final String X_APOLLOGROUP_EDU_VLP_CONTEXT_PLACEHOLDER = "vlpContext.header";
	public static final String PERMANENT_SENT_FAILURE_PLACEHOLDER = "permanent.sent.failure.url";
	
	public static final String DB_HOST = "dbhost";
	public static final String DB_PORT = "dbport";
	public static final String DB_USERNAME = "dbusername";
	public static final String DB_PASSWORD = "dbpassword";
	
	//Test data:
	public static final String SERVICE_NAME_TC_BRV_001 = "Hyderabad To Mysore, Calicut";
	public static final String SERVICE_NAME_TRAVEL_NAME_001 = "Kallada Travels Suresh Kallada";
	public static final String SERVICE_NAME_TC_BRV_002 = "CHN-BNG-MYS-MADIKERI";
	public static final String SERVICE_NAME_TRAVEL_NAME_002 = "Seabird Torists Center";
	public static final String SERVICE_NAME_TC_BRV_003 = "Bangalore-Madikeri";
	public static final String SERVICE_NAME_TRAVEL_NAME_003 = "Ashwini Tours And Travels";
	
	public static final String KEY_AR_SEAT_AVAILABLE_JSON_FILE = "seat_available_api_payload";
  }

package com.poc.dataprovider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.poc.constants.CommonConstants;
import com.poc.reporting.PerformanceData;

public class TestEnvironment {
	private String testEnv;
	private static TestEnvironment env;
	private com.poc.reporting.Reporter reporter;
    private static List<com.poc.reporting.PerformanceData> perfData;
    private boolean performanceLoggingOn;
    private boolean debugLoggingOn;
    
	/**
	 * 
	 */
	 public static synchronized TestEnvironment getTestEnvironmentObject (String theBrowserType,String theTestEnv, String theTenantId, String theUrl){
		 if (env == null)
			 // it's ok, we can call this constructor
			 env = new TestEnvironment();
		 env.setTestEnv(theTestEnv);
		 return env;
	 }

	 /**
	  * @throws IOException 
	  * 
	  */
	 public static synchronized TestEnvironment getTestEnvironmentObject (Map<String, String> theTestEnvData) throws IOException{
		 if (env == null)
			 env = new TestEnvironment();
		 env.setTestEnv(theTestEnvData.get(CommonConstants.KEY_TEST_ENVIRONMENT));
		 return env;
	 }

	 /**
	  * 
	  * @return
	  */
	 public static synchronized TestEnvironment getTestEnvironmentObject(){
		 if (env == null)
			 // it's ok, we can call this constructor
			 env = new TestEnvironment();
		 return env;
	 }

	 /**
	  * 
	  */
	 private TestEnvironment(){

	 }

	 /**
	  * 
	  * @return
	  */
	 public String getTestEnv() {
		 return testEnv;
	 }

	 /**
	  * 
	  * @param theTestEnv
	  */
	 public void setTestEnv(String theTestEnv) {
		 this.testEnv = theTestEnv;
	 }

	public void setReporter(com.poc.reporting.Reporter reporter) {
		this.reporter = reporter;
	}

	public com.poc.reporting.Reporter getReporter() {
		return reporter;
	}

	public static void setPerfData(PerformanceData thePerfData) {
		if (perfData == null){
			perfData = new ArrayList<PerformanceData>();
		}  
		perfData.add(thePerfData);
	}

	public List<PerformanceData> getPerfData() {
		return perfData;
	}

	public boolean isDebugLoggingOn() {
		return debugLoggingOn;
	}

	public void setDebugLoggingOn(boolean debugLoggingOn) {
		this.debugLoggingOn = debugLoggingOn;
	}

	public boolean isPerformanceLoggingOn() {
		return performanceLoggingOn;
	}

	public void setPerformanceLoggingOn(boolean performanceLoggingOn) {
		this.performanceLoggingOn = performanceLoggingOn;
	}

	

}

package com.poc.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.poc.constants.CommonConstants;

public class propertiesRead {

	
	public String propertiesrRetrieve(String Skey) 
	{
		
		FileInputStream fis = null;
		try {
			String path = System.getProperty("user.dir")+ CommonConstants.DATA_LOCATION_UI;
			fis = new FileInputStream(path/*ClassLoader.getSystemResource("config.properties").getPath()*/);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Properties prop= new Properties();
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return System.getProperty("user.dir")+prop.getProperty(Skey);
		
		
	}



}

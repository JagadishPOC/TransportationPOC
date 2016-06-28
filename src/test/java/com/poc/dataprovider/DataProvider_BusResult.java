package com.poc.dataprovider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import com.poc.utility.ExcelRWComponent;
import com.poc.utility.propertiesRead;

public class DataProvider_BusResult {

	public static propertiesRead pRead = new propertiesRead();

	@DataProvider(name="dp_getBusResult_Filter_Provider")
	public static Iterator<String[]> getgetBusResult_Filter_Provider() {
	
		List<String[]> resultObj = busResultData("BUS_Result","BusResult_Filter_Provider");
		return resultObj.iterator();
	}
	

	@DataProvider(name="dp_getBusResult_Filter_Type")
	public static Iterator<String[]> getBusResult_Filter_Type() {
	
		List<String[]> resultObj = busResultData("BUS_Result","BusResult_Filter_Type");
		return resultObj.iterator();
	}
	
	@DataProvider(name="dp_getBusResult_Seat_View")
	public static Iterator<String[]> getBusResult_Seat_View() {
	
		List<String[]> resultObj = busResultData("BUS_Result","BusResult_Seat_View");
		return resultObj.iterator();
	}
	
	@DataProvider(name="dp_getBusResult_Next_Day")
	public static Iterator<String[]> getBusResult_Next_Day() {
	
		List<String[]> resultObj = busResultData("BUS_Result","BusResult_NextDay");
		return resultObj.iterator();
	}
	
	@DataProvider(name="dp_getBusResult_Previous_Day")
	public static Iterator<String[]> getBusResult_previous_Day() {
	
		List<String[]> resultObj = busResultData("BUS_Result","BusResult_previous_View");
		return resultObj.iterator();
	}
	
	public static List<String[]> busResultData(String sheetName, String scenario){
		List<String []> busSearchList = null;
		try {
			ExcelRWComponent excel = new ExcelRWComponent(pRead.propertiesrRetrieve("testDataXls"));
			HSSFSheet busSearchSheet = excel.Setsheet(sheetName);
			int rowCount = excel.getrowcount(busSearchSheet);
			busSearchList= new ArrayList<String []>();
			for(int xlrow =1; xlrow <=rowCount;xlrow++)
			{
				
				String Execute_Flag = excel.Readvalue(busSearchSheet, xlrow, "Execution_flag");
				String Scenario_name = excel.Readvalue(busSearchSheet, xlrow, "Scenario");
				if((Execute_Flag.equalsIgnoreCase("Y")) && (Scenario_name.equals(scenario)))
				{ 
					String [] busSearchData= new String [8];
					busSearchData[0] = excel.Readvalue(busSearchSheet, xlrow, "TC_ID");
					busSearchData[1] = excel.Readvalue(busSearchSheet, xlrow, "Repetition_Order");
					busSearchData[2] = excel.Readvalue(busSearchSheet, xlrow, "Browser");
					busSearchData[3] = excel.Readvalue(busSearchSheet, xlrow, "Search_From");
					busSearchData[4] = excel.Readvalue(busSearchSheet, xlrow, "Search_To");
					busSearchData[5] = excel.ReadDateFormat(busSearchSheet, xlrow, "Search_Joureny");
					busSearchData[6] = excel.Readvalue(busSearchSheet, xlrow, "Travel_Name");
					busSearchData[7] = excel.Readvalue(busSearchSheet, xlrow, "Expected_Result");
					busSearchList.add(busSearchData);
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return busSearchList;

	}

}

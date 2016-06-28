package com.poc.dataprovider;

public class sampleTest {
public static void main(String[] args) {
	String Search_Joureny="30-May-2016";
	String finalsplitDate;
	String[] splitDate = Search_Joureny.split("-",-1);
	int date = Integer.parseInt(splitDate[0]);
	date=date+1;
	finalsplitDate=date+splitDate[1]+splitDate[2];
	System.out.println(finalsplitDate);
	System.out.println(date);
	System.out.println(splitDate[1]);
	System.out.println(splitDate[2]);

}
}

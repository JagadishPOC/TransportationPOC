package com.poc.redBus.pageObjectpattern;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.poc.genericComponent.SeleniumComponents;

public class PageObject_BusSearch extends SeleniumComponents{
	
	//public WebDriver webDriver;
	@FindBy(xpath =  ".//*[@id='txtSource']")
//	@FindBy(id="txtSource")

	public WebElement searchFrom;
	
	@FindBy(id="txtDestination")
	public WebElement searchTo; 
	
	@FindBy(id="txtOnwardCalendar")
	public WebElement searchJourneyDate; 
	
	@FindBy(id="txtReturnCalendar")
	public WebElement searchRtnjourneyDate; 
	
	@FindBy(id="searchBtn")
	public WebElement searchBus; 

	@FindBy(id="rbcal_txtOnwardCalendar")
	public WebElement SearchCalendar; 
	
	
	@FindBy(xpath=".//*[@id='rbcal_txtOnwardCalendar']/table[2]/tbody/tr[1]/td[3]/button")
	public WebElement sCalNxtButton; 

	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/ul/li[1]/div/div[6]/div[1]/span")
	public WebElement AvailableSeatsCount;
	
	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/div/div[1]/div[1]/div/span[2] ")
	public WebElement NotYetOpenMsg;
	
	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/div/div/h7")
	public WebElement NoTicketAva;
		
	@FindBy(xpath="//div[@class='modalCloseSmall'][@title='close']")
	public WebElement closeRefer;
	
	String jCalenHeaderpath =  ".//*[@id='rbcal_txtOnwardCalendar']/table[1]/tbody/tr[1]/td[2]";	  	   
	String jCalenDatepath =  ".//*[@id='rbcal_txtOnwardCalendar']/table[1]/tbody/tr[1]/td";
	
	public PageObject_BusSearch(WebDriver thewebDriver)
	{
		PageFactory.initElements(thewebDriver,this);
	}
	
	public void setFromLocation(String valuetobeEnter){
		waitforElement(searchFrom);
		elementSendkey(searchFrom, valuetobeEnter);
	}
	
	public void setToLocation(String valuetobeEnter){
		elementSendkey(searchTo,valuetobeEnter);
	}
	
	public void setJourneyDate(String valuetobeEnter){
		elementClick(searchJourneyDate);
		implicitlyWait(20);
		String[] jSelSplit= valuetobeEnter.split("-");
        String jSDate = jSelSplit[0];
        String jSMonth = jSelSplit[1];
        String jSYear = jSelSplit[2];
        
        By jCalHlocatorBy = getLocatorBy("XPATH",jCalenHeaderpath);
   //     By jCalDlocatorBy = getLocatorBy("XPATH",jCalenDatepath);
        
        
     //   selectGivenDate( jSDate,jSMonth,jSYear);
        //sCalNxtButton.click();
        while(true){
        	try{
        	String	xpaathjcDate= ".//*[@id='rbcal_txtOnwardCalendar']/table[1]/tbody/tr/td[text()="+jSDate+"]";
        		waitforElementBy(jCalHlocatorBy,jSYear);
            		waitforElementBy(jCalHlocatorBy,jSMonth);
            			WebElement jCaldate = webDriver.findElement(By.xpath(xpaathjcDate));
	            		waitforElement(jCaldate);
	            		jCaldate.click();
	            		break;
        	}catch(Exception e){
			sCalNxtButton.click();
			}
        }
	}
	
	public void setRtnJourneyDate(String valuetobeEnter){
	
		
	}
	
	public void searchBusclick(){
		elementClick(searchBus);
	}
	
	public int getAvailableSeatCount(){
		
		waitforElement(AvailableSeatsCount);
		String AvailableSeattext = AvailableSeatsCount.getText();
		return Integer.parseInt(AvailableSeattext);
	}
	
	public String getNotYetOpenMsg(){
		String notYetOpenmsg = NotYetOpenMsg.getText();
		System.out.println(notYetOpenmsg);
		return notYetOpenmsg;
	}
	
		
	public String getNoOpenTicketMsg(){
		String noTicketMsg = NoTicketAva.getText();
	//	System.out.println(noTicketMsg);
		return noTicketMsg;

	}
	
	public void closeReferalPOPUP(){
		if(elementIsDisplayed(closeRefer)){
			closeRefer.click();
		}

	}
	
	
}

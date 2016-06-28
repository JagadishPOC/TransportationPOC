package com.poc.redBus.pageObjectpattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObject_BusBooking {
	@FindBy(xpath=".//*[@id='NAME1']")
	public WebElement customerName; 
	
	@FindBy(xpath=".//*[@id='EMAILID']")
	public WebElement customerEmailId; 
	
	@FindBy(xpath=".//*[@id='male1']")
	public WebElement customerGenderMale; 
	
	@FindBy(xpath=".//*[@id='female1']")
	public WebElement customerGenderFeMale; 
	
	
	@FindBy(xpath=".//*[@id='PHONE']")
	public WebElement customerPhone; 
	
	
	@FindBy(xpath=".//*[@id='onwdets']/dd[1]")
	public WebElement journeyLocation; 
	
	
	@FindBy(xpath=".//*[@id='doj']")
	public WebElement dojJourney; 
	

	public PageObject_BusBooking(WebDriver thewebDriver)
	{
		PageFactory.initElements(thewebDriver,this);
	}

	public void setCustomerName(String valueToBe){
		customerName.sendKeys(valueToBe);
	}
	
	public void setCustomerEmailId(String valueToBe){
		customerEmailId.sendKeys(valueToBe);
	}
	
	public void setCustomerGender(String valueToBe){
		
		if(valueToBe=="M"){
		customerGenderMale.click();
		}
		else if(valueToBe=="F"){
		customerGenderFeMale.click();
		}
	}
	
	public void setCustomerPhone(String valueToBe){
		customerPhone.sendKeys(valueToBe);
	}

}

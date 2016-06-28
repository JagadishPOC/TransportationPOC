package com.poc.redBus.pageObjectpattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.poc.genericComponent.SeleniumComponents;

public class PageObject_BusResult extends SeleniumComponents {


	@FindBy(xpath=".//*[@id='OnwardDataDiv']/div[1]/span[1]")
	public WebElement fromLocation; 

	@FindBy(xpath=".//*[@id='OnwardDataDiv']/div[1]/span[3]")
	public WebElement toLocation; 

	@FindBy(xpath=".//*[@id='onwardDates']/a[1]")
	public WebElement prevDate; 

	@FindBy(xpath=".//*[@id='onwardDates']/a[2]")
	public WebElement nxtDate; 

	@FindBy(xpath=".//*[@id='mod_search']/a")
	public WebElement modifyDate; 

	@FindBy(xpath=".//*[@id='onwardSortAndFilter']/div[1]/div[2]/div[2]/a/span")
	public WebElement travelFilter; 

	@FindBy(xpath=".//*[@id='onwardSortAndFilter']/div[1]/div[2]/div[3]/a/span")
	public WebElement busTypeFilter; 

	/*	
	Travels Check Box

	SRS Travels - .//*[@id='Travels_SRS_Travels']
	Sri Durgamba Travels - .//*[@id='Travels_Sri_Durgamba_Travels']

	Bus Type AC
	.//*[@id='BusType_AC']
	.//*[@id='BusType_Non_AC']
	.//*[@id='BusType_Sleeper']
	.//*[@id='BusType_Cab']
	 */

	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/ul/li[1]/div/div[8]/button")
	public WebElement viewSeat; 

	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/ul/li[7]/div/div[2]/div/div[3]/a")
	public WebElement mticketIcon; 

	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/ul/li[46]/div/div[9]/div/div/div[3]/div/div[1]/div[2]/div/div[2]")
	public WebElement busSeatLayout; 

	/*
	1st Seat-   .//*[@id='onwardTrip']/div[2]/ul/li[46]/div/div[9]/div/div/div[3]/div/div[1]/div[2]/div/div[2]/ul/li[1]/a
	2nd Seat-  .//*[@id='onwardTrip']/div[2]/ul/li[46]/div/div[9]/div/div/div[3]/div/div[1]/div[2]/div/div[2]/ul/li[2]/a
	 	<a class="available seat" href="javascript:void(0)" title="Seat No: 2 | Fare: Rs.582" style="height: 20px; width: 20px;"/>
		Class - booked seat
		ladiesbooked seat
		available seat
		available seat selected

	 */
	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/ul/li[1]/div/div[9]/div/div/div[4]/div/div[2]/div[1]/div[1]/select")
	public WebElement selectBoardingPoint; 

	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/ul/li[1]/div/div[9]/div/div/div[4]/div/div[2]/div[1]/button")
	public WebElement contNxtButton;

	@FindBy(xpath=".//*[@id='onwardTrip']/div[2]/ul/li[1]/div/div[9]/div/div/div[4]/div/div[1]/div[1]")
	public WebElement legentFareLayout; 

	@FindBy(xpath=".//*[@id='ctl00_body']/div[10]/div/div[2]/div[1]/div/div[3]/div/div/div[2]/a[2]")
	public WebElement NotNowforReturnJourney; 

	@FindBy(xpath=".//*[@id='dateDoubleOnward']")
	public WebElement dateDoubleOnward; 




	public PageObject_BusResult(WebDriver thewebDriver)
	{
		PageFactory.initElements(thewebDriver,this);
	}

	public void getProviderFilter(String TravelName){
		waitforElement(travelFilter);
		elementClick(travelFilter);
		//.//*[@id='Travels_SRS_Travels']
		String[] TravNameSplit = TravelName.split(" ",-1);
		String TravelXpath = ".//*[@id='Travels";
		String finalTravelXpath;
		for (String TNSplit : TravNameSplit) {
			TravelXpath=TravelXpath+"_"+TNSplit;
		}
		finalTravelXpath=TravelXpath+"']";
		//System.out.println(finalTravelXpath);
		webDriver.findElement(By.xpath(finalTravelXpath)).click();
	}

	public void getBusTypeFilter(String BusType){
		waitforElement(busTypeFilter);
		elementClick(busTypeFilter);

		/*switch (BusType) {
		case "A/C":			
			webDriver.findElement(By.xpath(".//*[@id='BusType_AC']")).click();
			break;
		case "Non A/C":	
			webDriver.findElement(By.xpath(".//*[@id='BusType_Non_AC']")).click();
			break;
		case "Sleeper":	
			webDriver.findElement(By.xpath(".//*[@id='BusType_Sleeper']")).click();
			break;
		case "Cab":	
			webDriver.findElement(By.xpath(".//*[@id='BusType_Cab']")).click();
			break;
		}*/

		if(BusType.equals("A/C")){
			webDriver.findElement(By.xpath(".//*[@id='BusType_AC']")).click();	
		}
		else if(BusType.equals("Non A/C")){
			webDriver.findElement(By.xpath(".//*[@id='BusType_Non_AC']")).click();
		}
		else if(BusType.equals("Sleeper")){
			webDriver.findElement(By.xpath(".//*[@id='BusType_Sleeper']")).click();
		}
		else if(BusType.equals("Cab")){
			webDriver.findElement(By.xpath(".//*[@id='BusType_Cab']")).click();
		}
	}

	public void viewBusSeat(){
		elementClick(viewSeat);
	}
	public void selectSeat(int seatNumber){
		webDriver.findElement(By.xpath(".//*[@id='onwardTrip']/div[2]/ul/li[1]/div/div[9]/div/div/div[4]/div/div[1]/div[2]/div/div[2]/ul/li["+seatNumber+"]/a")).click();

	}

	public void selectBoardingPoint(){
		Select selBoardingPoint=  new Select(selectBoardingPoint);
		selBoardingPoint.selectByIndex(1);
	}

	public void contNxtButton(){
		elementClick(contNxtButton);
	}

	public void NotNowforReturnJourney(){
		elementClick(NotNowforReturnJourney);
	}

	public void previousDay(){
		elementClick(prevDate);
	}

	public void nextDay(){
		elementClick(nxtDate);
	}

	public String getdateDoubleOnward(){
		return dateDoubleOnward.getText();
	}
}

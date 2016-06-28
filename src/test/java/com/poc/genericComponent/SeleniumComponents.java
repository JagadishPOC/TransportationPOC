package com.poc.genericComponent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.poc.utility.propertiesRead;

public class SeleniumComponents {


	public enum BrowserType{
		IE, 
		GOOGLECHROME
	}
	protected static BrowserType browserType;
	protected static WebDriver webDriver;
	protected static WebDriverWait webDriverWait;
	public static propertiesRead pRead = new propertiesRead();

	/*
	 * Set web driver method used set the web driver
	 * @param - need to pass the web driver
	 */

	public static void setWebDriver(WebDriver theWebDriver) {
		SeleniumComponents.webDriver = theWebDriver;
	}

	/*
	 * Get web driver method used to get the current web driver
	 * no @parm
	 */

	public static WebDriver getWebDriver() {
		return webDriver;
	}


	/*
	 * setBrowser method used to set browser (IE,fireFox, Google Chrome)
	 * 
	 * @param browserType
	 * @return web driver
	 */
	public static WebDriver setBrowser(String browserType){
		/*switch (browserType) {
		case "IE":			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("nativeEvents", true);
			cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "Accept");
			cap.setCapability("igoneProtectedModeSettings", true);
			cap.setCapability("ignoreZoomSetting",true);
			cap.setCapability("disable-popup-blocking", true);
			cap.setCapability("enablePersistentHover", true);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true); 
			System.setProperty("webdriver.ie.driver", pRead.propertiesrRetrieve("ieDriver"));
			webDriver = new InternetExplorerDriver();
			webDriver.manage().window().maximize();
			return webDriver;
		case "FIREFOX":
			webDriver = new FirefoxDriver();
			webDriver.manage().window().maximize();
			return webDriver;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", pRead.propertiesrRetrieve("ChromeDriver"));  
			webDriver = new ChromeDriver();
			webDriver.manage().window().maximize();
			return webDriver;		
		default:
			throw new RuntimeException("Browser type unsupported");
		}*/

		if(browserType.equals("IE")){
			//DesiredCapabilities cap = new DesiredCapabilities();
			System.setProperty("webdriver.ie.driver", pRead.propertiesrRetrieve("ieDriver"));
			webDriver = new InternetExplorerDriver();
			webDriver.manage().window().maximize();
			return webDriver;

		}
		else if(browserType.equals("FIREFOX")){
			webDriver = new FirefoxDriver();
			return webDriver;

		}
		else if(browserType.equals("CHROME")){
			System.setProperty("webdriver.chrome.driver", pRead.propertiesrRetrieve("ChromeDriver"));  
			webDriver = new ChromeDriver();
			return webDriver;	
		}
		else{
			throw new RuntimeException("Browser type unsupported");
		}
	}

	/*
	 * Set URL method used to set /Open the passed URL  
	 * 	@parm - URL needs to passed
	 */
	public static void setURL(String url)
	{
		implicitlyWait(30);
		webDriver.get(url);
		implicitlyWait(20);
		webDriver.manage().window().maximize();

	}

	/**
	 * Navigate to  of the URL
	 */
	public static void navigatetoURL(String url) {
		webDriver.navigate().to(url);
	}

	/**
	 * Refresh the page
	 */
	public static void refreshPage() {
		webDriver.navigate().refresh();
	}

	/*
	 * Get Current URL is used to get the current URL 	
	 * @return - string - Current URL
	 */
	public static String getCurrentURL(){
		return webDriver.getCurrentUrl();
	}


	/*
	 * close the browser	
	 */
	public static void closeBrowser() {
		webDriver.close();
	}


	/*
	 * 	quit the browser
	 */

	public static void quiteBrowser(){
		webDriver.quit();
	}

	/*
	 * Get the title of the page
	 * @return - string page title 
	 */
	public static String getTitle(){
		return webDriver.getTitle();
	}

	/* Need to set web driver wait before using this method
	 * this Method used to fetch the given URL is present in the current opened page
	 * @param expTitle - pass the expected title
	 * @return - boolean - TRUE means expected title present & FALSEA means expected tile is not present.
	 */

	public static Boolean isTitle(String expTitle){
		webDriverWait.until(ExpectedConditions.titleContains(expTitle));
		if(expTitle.equals(getTitle())){
			Reporter.log(expTitle+"---Expected Title Present");
			return true;
		}else{
			Reporter.log(expTitle+"---Expected Title Not Present");
			return false;
		}

	}

	/***
	 * Locator object identification method
	 * @param identifierType - (ID,XPATH,...........)
	 * @param identifierValue - (type corresponding value) 
	 * @return - web element
	 */
	public static WebElement getLocatorObject(String identifierType, String identifierValue){

		// Identifier & locator (Xpath - //*[@id='Txt1432] 		 		 
		WebElement locatorToBeIdentifiedBy = null;
		//String identifier = "ID";
		//String locator= "txtSource";

		/*switch (identifierType) {
		case "XPATH":			
			locatorToBeIdentifiedBy = webDriver.findElement(By.xpath(identifierValue));
			break;
		case "CSS":
			locatorToBeIdentifiedBy = webDriver.findElement(By.cssSelector(identifierValue));
			break;
		case "ID":
			locatorToBeIdentifiedBy = webDriver.findElement(By.id(identifierValue));
			break;
		case "NAME":
			locatorToBeIdentifiedBy = webDriver.findElement(By.name(identifierValue));
			break;
		case "CLASS":
			locatorToBeIdentifiedBy = webDriver.findElement(By.className(identifierValue));
			break;
		case "LINK":
			locatorToBeIdentifiedBy = webDriver.findElement(By.linkText(identifierValue));
			break;
		case "LINKP":
			locatorToBeIdentifiedBy = webDriver.findElement(By.partialLinkText(identifierValue));
			break;
		default:
			locatorToBeIdentifiedBy = webDriver.findElement(By.cssSelector(identifierValue));
			break;
		}
		return locatorToBeIdentifiedBy;*/

		if(identifierType.equals("XPATH")){
			locatorToBeIdentifiedBy = webDriver.findElement(By.xpath(identifierValue));	
		}
		else if(identifierType.equals("CSS")){
			locatorToBeIdentifiedBy = webDriver.findElement(By.cssSelector(identifierValue));
		}
		else if(identifierType.equals("ID")){
			locatorToBeIdentifiedBy = webDriver.findElement(By.id(identifierValue));
		}
		else if(identifierType.equals("NAME")){
			locatorToBeIdentifiedBy = webDriver.findElement(By.name(identifierValue));	
		}
		else if(identifierType.equals("CLASS")){
			locatorToBeIdentifiedBy = webDriver.findElement(By.className(identifierValue));	
		}
		else if(identifierType.equals("LINK")){
			locatorToBeIdentifiedBy = webDriver.findElement(By.linkText(identifierValue));
		}
		else if(identifierType.equals("LINKP")){
			locatorToBeIdentifiedBy = webDriver.findElement(By.partialLinkText(identifierValue));
		}
		else {
			locatorToBeIdentifiedBy = webDriver.findElement(By.cssSelector(identifierValue));
		}
		return locatorToBeIdentifiedBy;
	}
	/*****
	 * Locator identification Method
	 * @param identifierType
	 * @param identifierValue
	 * @return - by
	 */
	public static By getLocatorBy(String identifierType, String identifierValue){

		// Identifier & locator (Xpath - //*[@id='Txt1432] 		 		 
		By locatorToBeIdentifiedBy = null;
		//String identifier = "ID";
		//String locator= "txtSource";

		/*switch (identifierType) {
		case "XPATH":
			locatorToBeIdentifiedBy = By.xpath(identifierValue);
			break;
		case "CSS":
			locatorToBeIdentifiedBy = By.cssSelector(identifierValue);
			break;
		case "ID":
			locatorToBeIdentifiedBy = By.id(identifierValue);
			break;
		case "NAME":
			locatorToBeIdentifiedBy = By.name(identifierValue);
			break;
		case "CLASS":
			locatorToBeIdentifiedBy =By.className(identifierValue);
			break;
		case "LINK":
			locatorToBeIdentifiedBy = By.linkText(identifierValue);
			break;

		}
		return locatorToBeIdentifiedBy;*/

		if(identifierType.equals("XPATH")){
			locatorToBeIdentifiedBy = By.xpath(identifierValue);	
		}
		else if(identifierType.equals("CSS")){
			locatorToBeIdentifiedBy = By.cssSelector(identifierValue);
		}
		else if(identifierType.equals("ID")){
			locatorToBeIdentifiedBy = By.id(identifierValue);
		}
		else if(identifierType.equals("NAME")){
			locatorToBeIdentifiedBy = By.name(identifierValue);
		}
		else if(identifierType.equals("CLASS")){
			locatorToBeIdentifiedBy =By.className(identifierValue);
		}
		else if(identifierType.equals("LINK")){
			locatorToBeIdentifiedBy =By.className(identifierValue);
		}
		return locatorToBeIdentifiedBy;

	}



	public void Snapshot(String TC_ID, String Order)
	{

		Date date= new Date();
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		File file= new File(dateformat.format(date)+".png");
		TakesScreenshot screenshot= (TakesScreenshot) webDriver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotAs, new File(pRead.propertiesrRetrieve("SnapshotPath")+TC_ID+"-"+Order+"-"+file ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/***** Common UI Action Using Web Element *************/
	public static void elementClick(WebElement webElement){
		webElement.click();
	}
	public static void elementSendkey(WebElement webElement, String valuetobeEnter){
		webElement.sendKeys(valuetobeEnter);
	}

	public static void elementClear(WebElement webElement){
		webElement.clear();
	}		  

	public static boolean elementIsDisplayed(WebElement webElement){
		if(webElement.isDisplayed()){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean elementIsEnabled(WebElement webElement){
		if(webElement.isEnabled()){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean elementIsSelected(WebElement webElement){
		if(webElement.isSelected()){
			return true;
		}
		else{
			return false;
		}			
	}

	public static String elementGetAttribute(WebElement webElement,String attributeName){
		return webElement.getAttribute(attributeName);
	}

	public static void waitforElement(WebElement webElement){
		webDriverWait=new WebDriverWait(webDriver,10);
		webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
	}


	public static void waitforElementBy(By locator, String text){
		webDriverWait=new WebDriverWait(webDriver,7);
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}


	/** Common UI Action using Identifier properties ********/

	public static void elementSendkey(String identifierType, String identifierValue,String valuetobeEnter){
		getLocatorObject(identifierType,identifierValue).sendKeys(valuetobeEnter);
	}

	public static void elementClear(String identifierType, String identifierValue){
		getLocatorObject(identifierType,identifierValue).clear();
	}

	public static void elementClick(String identifierType, String identifierValue){
		getLocatorObject(identifierType,identifierValue).click();
	}


	public static boolean elementIsDisplayed(String identifierType, String identifierValue){
		if(getLocatorObject(identifierType,identifierValue).isDisplayed()){
			return true;
		}
		else{
			return false;
		}
	}


	public static boolean elementIsEnabled(String identifierType, String identifierValue){
		if(getLocatorObject(identifierType,identifierValue).isEnabled()){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean elementIsSelected(String identifierType, String identifierValue){
		if(getLocatorObject(identifierType,identifierValue).isSelected()){
			return true;
		}
		else{
			return false;
		}			
	}


	public static String elementGetAttribute(String identifierType, String identifierValue,String attributeName){
		return getLocatorObject(identifierType,identifierValue).getAttribute(attributeName);
	}

	public static void waitforElement(String identifierType, String identifierValue){
		By locatorBy = getLocatorBy(identifierType,identifierValue);
		webDriverWait=new WebDriverWait(webDriver,30);
		webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorBy));
	}

	public static void implicitlyWait(int timeUnit){
		webDriver.manage().timeouts().implicitlyWait(timeUnit, TimeUnit.SECONDS);
	}

}
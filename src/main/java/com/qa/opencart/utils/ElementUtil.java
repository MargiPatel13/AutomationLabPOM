package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);

	}

	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight.trim())) {
			jsUtil.flash(element);
		}
		return element;
	}
	
	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}
	
	public static By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType) {
		case "id":
			locator = By.id(locatorValue);
			break;
		
		case "name":
			locator = By.name(locatorValue);
			break;
			
		case "className":
			locator = By.className(locatorValue);
			break;
			
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
			
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
			
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
			
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
			
		case "tagName":
			locator = By.tagName(locatorValue);
			break;

		default:
			System.out.println("Please pass the correct locator " + locatorType);
			break;
		}	
		return locator;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public void doSendKeys(By locator, String value) {
		WebElement ele  = getElement(locator);
		ele.clear();	
		ele.sendKeys(value);
	} 
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	/**** for String locator getBy, getElement()****/
	public void doSendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}
	public void doClick(WebElement element) {
		element.click();
	}
	/*****end of string locator******/
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public void ClickOnElement(By locator, String value) {
		List<WebElement> elementList = getElements(locator);

		for (WebElement e : elementList) {
			if (e.getText().equals(value)) {
				System.out.println(e.getText());
				e.click();
				break;
			}
		}
	}

	public List<String> getLinksTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();

		List<WebElement> elementlist = getElements(locator);
		System.out.println("Element count: " + elementlist.size());

		for (WebElement e : elementlist) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	/**********
	 * Verify Element is present without isDisplayed
	 **************/
	public boolean isElementExist(By locator) {
		List<WebElement> listElement = getElements(locator);

		if (listElement.size() > 0) {
			System.out.println("element is present");
			return true;
		}
		return false;
	}

	/************************** Drop Down Utils (Select) ****************************/

	public void doSelectByVisibleText(By locator, String text) {
		Select selectCountry = new Select(getElement(locator));
		selectCountry.selectByVisibleText(text);
	}

	public void doSelectByValue(By locator, String text) {
		Select selectCountry = new Select(getElement(locator));
		selectCountry.selectByValue(text);
	}

	public void doSelectByIndex(By locator, int index) {
		Select selectCountry = new Select(getElement(locator));
		selectCountry.selectByIndex(index);
	}

	public void getDropDownOptionsList(By locator) {
		List<String> optionsValList = new ArrayList<String>();

		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsValList.add(text);
		}
	}
	
	/************** Selecting drop down value using Select and getOptions() method *****************/
	public void selectDropDownValue(By locator, String value) {		
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println(optionsList.size());

		for(WebElement e : optionsList) {
			String text = e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	/**************** Selecting drop down value without using any inbuilt method ******************/
	public void clickDropDownValue(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		System.out.println(optionsList.size());
		
		for(WebElement e: optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	/******	user actions utils ******/
	
	public void doTwoLevelMenuHandle(By superMenu, By subMenu) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(superMenu)).perform();
		Thread.sleep(2000);
		getElement(subMenu).click();
	}
	
	public void doThreeLevelMenuHandle(By superMenu1, By superMenu2, By subMenu) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(superMenu1)).perform();
		Thread.sleep(2000);
		action.moveToElement(getElement(superMenu2)).perform();
		Thread.sleep(2000);
		getElement(subMenu).click();
	}
	
	public void doActionsSendKeys(By locator, String value) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionsClick(By locator) {
		Actions actions = new Actions(driver);
		actions.click(getElement(locator)).perform();
	}
	

	public void doContextClick(By locator) {
		Actions actions = new Actions(driver);
		actions.contextClick(getElement(locator)).perform();
	}
	
//	public void getContextClickMenuList(By locator, String value) {
//		List<WebElement> menuList = getElements(locator);
//		System.out.println(menuList.size());
//		
//		for(WebElement e: menuList) {
//			String text = e.getText();
//			System.out.println(text);
//			
//			if(text.equals(value)) {
//				e.click();
//				break;
//			}
//		}
//	}
	
//	public void doContextClickGetMenu(By clickLocator, By listLocator,String value) {
//		Actions actions = new Actions(driver);
//		actions.contextClick(getElement(clickLocator)).perform();
//		
//		List<WebElement> menuList = getElements(listLocator);
//		System.out.println(menuList.size());
//		
//		for(WebElement e: menuList) {
//			String text = e.getText();
//			System.out.println(text);
//			
//			if(text.equals(value)) {
//				e.click();
//				break;
//			}
//		}
//	}
	
	/******************   wait utility ********************/
	
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementPresence(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut, intervalTime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public String waitForTitleContains(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if(wait.until(ExpectedConditions.titleContains(titleValue))) {
			return driver.getTitle();
		}
		return null;
	}
	
	public String waitForTitleIs(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if(wait.until(ExpectedConditions.titleIs(titleValue))) {
			return driver.getTitle();
		}
		return null;
	}
	
	public String waitForUrlContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	
	public String waitForFullUrl(String urlValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	
	/****Alert Wait ****/
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);	
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}
	
	public void acceptAlert(int timeOut) {
		 waitForAlert(timeOut).accept();
	}
	
	public void dismissAlert(int timeOut) {
		 waitForAlert(timeOut).accept();
	}
	
	public void sendkeysOnAlert(int timeOut, String value) {
		 waitForAlert(timeOut).sendKeys(value);
	}
	
	/**** Frame Wait ****/
	public void waitForFrameAndSwitchToIt(String frameName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}
	
	public void waitForFrameAndSwitchToIt(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public void waitForFrameAndSwitchToIt(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public void waitForFrameAndSwitchToIt(WebElement webElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
	}
	
	/*** using FluentWait ***/
	public WebElement waitForElementWithFluentWait(By locator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebDriver waitForFrameWithFluentWait(By locator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	/****  Custom wait using sleep() ****/
	public WebElement retryingElement(By locator, int maxAttempts) {
		WebElement element = null;
		int attempts = 0;
		
		while(attempts < maxAttempts) {
			try {
				element = driver.findElement(locator);
				break;
			} 
			catch(Exception e) {
				try {
					Thread.sleep(1000);
				} 
				catch(InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("element is not found in attempt : " + (attempts+1));
			}
			attempts++;
		}
		return element;
	}
	
	
}

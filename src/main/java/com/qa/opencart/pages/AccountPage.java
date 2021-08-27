package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By accountSections  = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo h1 a");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	@Step("getAccPageTitle")
	public String getAccPageTitle() {
		return elementUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, 5);
	}
	
	@Step("getAccountPageURL")
	public String getAccountPageURL() {
		return elementUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, 5);
	}
	
	@Step("getAccountPageHeader")
	public String getAccountPageHeader() {
		return elementUtil.doGetText(header);
	}
	
	@Step("getAccountSectionsList")
	public List<String> getAccountSectionsList() {
		List<String> accSecValList = new ArrayList<String>();
		List<WebElement> accountSecList = elementUtil.getElements(accountSections);
		
		for(WebElement e : accountSecList) {
			accSecValList.add(e.getText());
		}
		return accSecValList;
	}
	
	@Step("verifyLogoutLink")
	public boolean verifyLogoutLink() {
		return elementUtil.doIsDisplayed(logoutLink);
	}
	
	@Step("doSearch")
	public ResultsPage doSearch(String productName) {
		System.out.println("searching the product: " + productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);	
		return new ResultsPage(driver);
	}

}

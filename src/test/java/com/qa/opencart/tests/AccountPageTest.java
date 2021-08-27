package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 101: Design Accounts page for open cart application....")
@Story("US 102: Accounts page with differnet features.....")
@Listeners(TestAllureListener.class)
public class AccountPageTest extends BaseTest{

	@BeforeClass
	public void accountPageSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@Description("accPageTitleTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void accountPageTitleTest() {
		String title = accountPage.getAccPageTitle();
		System.out.println("Title is : " + title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Description("accPageHeaderTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accountPageHeaderTest() {
		String header = accountPage.getAccountPageHeader();
		Assert.assertEquals(header, Constants.PAGE_HEADER);
	}
	
	@Description("accountSectionsListTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void accountSectionsListTest() {
		List<String> accountSecList = accountPage.getAccountSectionsList();
		System.out.println(accountSecList);
		Assert.assertEquals(accountSecList, Constants.EXPECTED_ACCOUNT_SECTION_LIST);
	}
	
	@Description("logoutLinkExistTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accountPage.verifyLogoutLink());
	}
	
//	@DataProvider
//	public Object[][] getSearchData() {
//		return new Object[][]{
//					{"Macbook Pro"}, 
//					{"Macbook Air"},
//					{"Apple"}
//		};
//	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return ExcelUtil.getTestData(Constants.SEARCHDATA_SHEET_NAME);
	}
	
	@Description("searchTest with {0}")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5, dataProvider = "getSearchData")
	public void searchTest(String productName) {
		 resultsPage = accountPage.doSearch(productName);
		 String resultHeader = resultsPage.getSearchPageHeader();
		 System.out.println("Search Header is : "+ resultHeader);
		 Assert.assertTrue(resultHeader.contains(productName));
	}
	
//	@DataProvider
//	public Object[][] getProductAndSelectData(){
//		return new Object[][] {
//			{"MacBook", "MacBook Air"},
//			{"MacBook", "MacBook Pro"},
//			{"Apple", "Apple Cinema 30\""}
//		};
//	}
	
	@DataProvider
	public Object[][] getProductAndSelectData(){
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}
	
	@Description("selectProductTest with product name: {0} and mainProductName: {1}")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 6, dataProvider = "getProductAndSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultsPage = accountPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		String header = productInfoPage.getProductHeaderText();
		System.out.println("Product Header : " + header);
		Assert.assertEquals(header, mainProductName);
	}
	
}

package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}

	@Test
	public void productImgsTest() {
		resultsPage = accountPage.doSearch("iMac");
		productInfoPage = resultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImgsCount(), 3);
	}
	@DataProvider
	public Object[][] getProductInfoData() {
		return ExcelUtil.getTestData(Constants.PRODUCTINFO_SHEET_NAME);
	}
	@Test(dataProvider = "getProductInfoData")
	public void productInfoList(String productName, String mainProductName, String brand, String productCode, String price, String taxPrice ) {
		resultsPage = accountPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
	
		softAssert.assertEquals(actProductInfoMap.get("Name"), mainProductName);
		softAssert.assertEquals(actProductInfoMap.get("Brand"), brand);
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), productCode);
		softAssert.assertEquals(actProductInfoMap.get("Price"), price);
		softAssert.assertEquals(actProductInfoMap.get("TaxPrice"), taxPrice);
		softAssert.assertAll();
	}
}

package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registationSetUp() {
		registrationPage = loginPage.clickOnRegisteration();
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "testautomation"+random.nextInt(5000)+"@testing.com";
		return email;
	}
	
//	@DataProvider
//	public Object[][] getRegTestData() {
//		return new Object[][] {
//			{"mary", "pel", "98792626857", "12345@myp", "Yes"},
//			{"mury", "patel", "4534534544", "12345@myp", "Yes"}
//		};
//	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegTestData")
	public void registrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
}

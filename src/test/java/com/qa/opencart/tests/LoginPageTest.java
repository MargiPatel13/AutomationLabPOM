package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 100: Design Login page for open cart application....")
@Story("US 101: Login page with differnet features.....")
public class LoginPageTest extends BaseTest {
	
	@Description("......Login Page Title Test......")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is : "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, Errors.TITLE_ERROR_MESSAGE);
	}
	@Description("......Login Page Header Test......")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageHeaderTest() {
		String header = loginPage.getPageHeaderText();
		System.out.println("login page header is : "+ header);
		Assert.assertEquals(header, Constants.PAGE_HEADER, Errors.TITLE_ERROR_MESSAGE);
	}
	@Description("......Forgot Pwd Link Test......")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	@Description("......Login Page Do Login Test......")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginPageDoLoginTest() {
		AccountPage acPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(acPage.verifyLogoutLink());
	}
}

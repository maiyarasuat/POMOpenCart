package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100: Design of the login page for opencart application")
@Story("US: - 200: implement login page feature for opencart application")
public class LoginPageTest extends BaseTest {

	
	@Description("login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Description("login page url test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("login page forgot password exist test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 3)
	public void isForgotPwdLinkExist() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Description("login page login test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.logoutLinkExist(), true);

	}

}

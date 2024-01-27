package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtilNew;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtilNew eleUtil;

	By emailId = By.id("input-email");
	By password = By.id("input-password");
	By loginBtn = By.xpath("//input[@value='Login']");
	By forgotPwdLink = By.linkText("Forgotten Password");
	By registerLink = By.linkText("Register");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtilNew(driver);
	}

	@Step(".....getting loginpage title....")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page Title is:" + title);
		return title;
	}
	
	@Step("....getting login page url....")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page Url is:" + url);
		return url;
	}
	
	@Step("....getting forgot password link exist or not....")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	@Step("login to app with username: {0} password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("App credentials are :" + username + ":" + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
//		return eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_TIME_OUT );
		return new AccountsPage(driver);
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, AppConstants.MEDIUM_TIME_OUT).click();
		return new RegisterPage(driver);
	}
		
}

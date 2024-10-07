package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtilNew;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtilNew eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtilNew(driver);

	}

	public String getAccountPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
	}

	public boolean logoutLinkExist() {
		return eleUtil.waitForElementPresence(logoutLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountsPageHeader() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(accHeaders, AppConstants.MEDIUM_TIME_OUT);
		List<String> headersValueList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String header = e.getText();
			headersValueList.add(header);
		}
		System.out.println("Actual headers are:" + headersValueList);
		return headersValueList;
	}

	public int getAccountsPageHeaderCount() {
		return eleUtil.waitForElementsVisible(accHeaders, AppConstants.MEDIUM_TIME_OUT).size();
	}

	public SearchResultsPage doSearch(String searchkey) {
		WebElement searchField = eleUtil.waitForElementPresence(search, AppConstants.MEDIUM_TIME_OUT);
		searchField.clear();
		searchField.sendKeys(searchkey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);

	}

}

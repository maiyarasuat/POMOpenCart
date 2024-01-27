package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("EPIC - 101: Design of the Accounts page for opencart application")
@Story("US: - 201: implement account page feature for opencart application")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accpagesetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String actAccPageTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actAccPageTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.logoutLinkExist());
	}
	
	@Test
	public void accPageHeadersCountTest() {
		int actAccPageHeadersCount = accPage.getAccountsPageHeaderCount();
		System.out.println("Actual Account page headers count:" + actAccPageHeadersCount);
		Assert.assertEquals(actAccPageHeadersCount, AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	
	@Test
	public void accPageHeadersCount() {
		List<String> actAccPageHeaders = accPage.getAccountsPageHeader();
		Assert.assertEquals(actAccPageHeaders, AppConstants.EXPECTED_ACC_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {
			{"macbook" , 3},
			{"imac" , 1},
			{"samsung" , 2}
		};
	}
	
	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchKey , int productCount) {
		searchResPage = accPage.doSearch(searchKey);
		int actResultsCount = searchResPage.getSearchResultsCount();
		Assert.assertEquals(actResultsCount, productCount);
	}
	
	
}

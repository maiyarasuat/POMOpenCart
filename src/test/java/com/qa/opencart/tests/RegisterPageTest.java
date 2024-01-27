package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("EPIC - 103: Design of the register page for opencart application")
@Story("US: - 203: implement register page feature for opencart application")
public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailId() {
		 return "openauto"+System.currentTimeMillis()+"@open.com";
	}
	
	@DataProvider
	public Object[][] getUserRegData() {
		return new Object[][] {
			
			{"pooja", "agarwal", "9876565644" , "pooja@p" , "yes"},
			{"shubam", "gupta", "8388375644" , "shubham@s" , "no"},
			{"mitaj", "kumar", "9847432644" ,  "mitaj@m" , "yes"},
			{"neelam", "rj", "8737565644" , "neelam@n" , "no"},
			{"lipi", "bea", "1112565644" , "lipi@l" , "yes"},
			{"naveen", "automation", "9876565644" , "naveenqa@na" , "no"},
			
		};
	}
	
	@DataProvider
	public Object[][] getUserRegSheetData() {
		return ExcelUtil.getTestData("register");
		
	}
	@Test(dataProvider = "getUserRegData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(regPage.registerUser(firstName, lastName, getRandomEmailId(), telephone, password, subscribe));
	}	
}

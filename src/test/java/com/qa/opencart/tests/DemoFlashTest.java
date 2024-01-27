package com.qa.opencart.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ElementUtilNew;

public class DemoFlashTest extends BaseTest {
	
	ElementUtilNew eleUtil;
	
	@BeforeMethod
	public void demosetup() {
		driver.get("https://classic.crmpro.com/");
		eleUtil = new ElementUtilNew(driver);
	}
	
	@Test
	public void flashElement() {
		eleUtil.doSendKeys(By.name("username"), "newautomation");
		eleUtil.doSendKeys(By.name("password"), "newautomation");
		eleUtil.doClick(By.xpath("//input[@value='Login']"));

	}
	
	

}

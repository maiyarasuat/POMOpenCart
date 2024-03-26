package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.productInfoPage;

public class BaseTest {

	protected Properties prop;
	protected WebDriver driver;
	protected LoginPage loginPage;
	protected RegisterPage regPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResPage;
	protected productInfoPage prodInfoPage;
	DriverFactory df;
	protected SoftAssert softAssert;

	@Parameters({"browser" , "browserversion"})
	@BeforeTest
	public void setup(String browserName , String browserVersion) {
		df = new DriverFactory();
		prop = df.initProp();

		if (browserName!= null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}

		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}

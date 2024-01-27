package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtilNew;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtilNew eleUtil;
	
	private By productResults = By.cssSelector("div.product-layout");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtilNew(driver);
	}

	 public int getSearchResultsCount() {
		 return eleUtil.waitForElementsVisible(productResults, AppConstants.MEDIUM_TIME_OUT).size();
	 }
	
	public productInfoPage selectProduct(String productName) {
		eleUtil.clickElementWhenReady(By.linkText(productName), AppConstants.MEDIUM_TIME_OUT);
		return new productInfoPage(driver);
		
	}
	
	
	
}

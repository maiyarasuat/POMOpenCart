package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtilNew;

public class productInfoPage {

	Map<String , String> productMap;
	
	private WebDriver driver;
	private ElementUtilNew eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.name("quantity");
	private By addToCartBtn = By.id("button-cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	
	public productInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtilNew(driver);
		
	}
	
	public String getProductHeaderValue() {
		return eleUtil.doElementGetText(productHeader);
	}
	
	
	public int getProductImagesCount() {
		int actProductImagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println("total product images for:" +getProductHeaderValue() + "==>" + actProductImagesCount);
		return actProductImagesCount;
	}
		
	private void getProductMetaData() {
		List<WebElement> metaData = eleUtil.waitForElementsVisible(productMetaData, AppConstants.MEDIUM_TIME_OUT);
		//Map<String , String> metaMap = new HashMap<String, String>();
		for(WebElement e : metaData) {
			String metaText = e.getText();
			String key = metaText.split(":")[0].trim();
			String value = metaText.split(":")[1].trim();
			productMap.put(key, value);
		}
		//return metaMap;
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData, AppConstants.MEDIUM_TIME_OUT);
		//Map<String , String> metaMap = new HashMap<String, String>();
		
		String actPrice = priceList.get(0).getText().trim();
		String exTax = priceList.get(1).getText().split(":")[0].trim();
		String exTaxValue = priceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("price", actPrice);
		productMap.put(exTax, exTaxValue);
		
		//return metaMap;
	}
	
	public Map<String,String> getProductData() {
		productMap = new HashMap<String , String>();
		productMap.put("productheader", getProductHeaderValue());
		productMap.put("productimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		
		return productMap;
	}
	
	
	

}

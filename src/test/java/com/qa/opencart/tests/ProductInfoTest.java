package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("EPIC - 102: Design of the product info page for opencart application")
@Story("US: - 202: implement product info page feature for opencart application")
public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] productTestData() {
		return new Object[][] {
			{"macbook" , "MacBook Pro"},
			{"macbook" , "MacBook Air"},
			{"macbook" , "MacBook"},
			{"imac" , "iMac"},
			{"samsung" , "Samsung SyncMaster 941BW"},
			{"samsung" , "Samsung Galaxy Tab 10.1"},
		};
	}
	
	
	@Test(dataProvider = "productTestData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		prodInfoPage = searchResPage.selectProduct(productName);
		String actProductHeader = prodInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeader, productName);
		
	}
	
	@DataProvider
	public Object[][]productData() {
		return new Object[][] {
			{"macbook" , "MacBook Pro" , 4},
			{"macbook" , "MacBook Air" , 4},
			{"macbook" , "MacBook" , 5},
			{"imac" , "iMac" , 3},
			{"samsung" , "Samsung SyncMaster 941BW" , 1},
			{"samsung" , "Samsung Galaxy Tab 10.1" , 7},
		};
	}
	
	@Test(dataProvider = "productData")
	public void productImagesCountTest(String searchKey, String productName, int expImgCount) {
		searchResPage = accPage.doSearch(searchKey);
		prodInfoPage = searchResPage.selectProduct(productName);
		int actProdImagesCount = prodInfoPage.getProductImagesCount();
		Assert.assertEquals(actProdImagesCount, expImgCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResPage = accPage.doSearch("macbook");
		prodInfoPage = searchResPage.selectProduct("MacBook Air");
		Map<String, String> productActualData = prodInfoPage.getProductData();
		System.out.println(productActualData);
		softAssert.assertEquals(productActualData.get("Brand"), "Apple");
		softAssert.assertEquals(productActualData.get("productheader"), "MacBook Air");
		softAssert.assertEquals(productActualData.get("Availability"), "In Stock");
		softAssert.assertEquals(productActualData.get("Reward Points"), "700");
		softAssert.assertEquals(productActualData.get("price"), "$1,202.00");
		softAssert.assertAll();
	}	
}

package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * 
 * @author maiyarasu
 *
 */
	public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	/**
	 * This method is used to initialize the driver
	 * @param browserName
	 * @return driver
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is :" + browserName);
		highlight = prop.getProperty("highlight");
		
		optManager = new OptionsManager(prop);
		
		switch (browserName.toLowerCase()) {
		case "chrome":
//			driver = new ChromeDriver(optManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optManager.getChromeOptions()));
			break;
		case "edge":
//			driver = new EdgeDriver(optManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optManager.getEdgeOptions()));
			break;
		case "firefox":
//			driver = new FirefoxDriver(optManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optManager.getFirefoxOptions()));
			break;
		case "safari":
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;	

		default:
			System.out.println("Please pass the right broswer" +browserName);
			break;
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();	
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/**
	 * Used to initialize the properties
	 */
	public Properties initProp() {
		
		//mvn clean install -Denv="qa"
		
		FileInputStream ip = null;
		prop = new Properties();
		
		String envName = System.getProperty("env");
		System.out.println("env name is:" + envName);
		
		try {
				if(envName == null) {
				System.out.println("No env is given hence running it on qa env by default");
			    ip = new FileInputStream("./src/test/resources/config/qa.config.properties");	
		}
		else {
			switch (envName.toLowerCase().trim()) {
			
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;	
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;	

			default:
				System.out.println("please pass the right environment name" +envName);
				break;
			}
		}
	}	
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return prop;
		}	
	
	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir")+"/screenshot"+methodName+"_"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		
		try {
			org.openqa.selenium.io.FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
}

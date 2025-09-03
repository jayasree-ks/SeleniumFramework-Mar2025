package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	// initialize the driver

	public WebDriver driver;
	public Properties prop;
	
	public static String highlightEle;
	
	/**
	 * This method is init the driver on the basis of browser.
	 * @param browsername
	 * @return it return driver instance
	 */

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("browsername : " + browserName);
		
		highlightEle = prop.getProperty("highlight");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();	
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println(AppError.INVALID_BROWSER_MESG + ":" + browserName);
			throw new FrameworkException("=====INVALID BROWSER======");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;

	}
	
	/** 
	 * This method is init the prop with properties file
	 * @return
	 */
	public Properties initProp() {
		prop=new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
		
	}

}

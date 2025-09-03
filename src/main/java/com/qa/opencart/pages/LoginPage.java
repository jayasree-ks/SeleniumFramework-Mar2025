package com.qa.opencart.pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// private By locators: page objects/page element

	private final By emailID = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");
	private final By header = By.tagName("h2");
	private final By opencartImage = By.xpath("//img[@title='naveenopencart']");
	

	// public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// public page methods/actions

	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Login Page title : " + title);
		return title;
	}

	public String getLoginPageUrl() {
		String pageURL = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("LoginPage URL : " + pageURL);
		return pageURL;
	}

	public boolean IsForgotPwdLinkExists() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	public boolean IsHeaderExists() {
		return eleUtil.isElementDisplayed(header);
	}
	
	public boolean isRegisterLinkExists() {
		return eleUtil.isElementDisplayed(registerLink);
	}
	
	public boolean isOpencartImageExists() {
		return eleUtil.isElementDisplayed(opencartImage);
	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("Application credentials: " + username + ":" + pwd);
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);		
//		driver.findElement(emailID).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		return new AccountsPage(driver);	
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new RegisterPage(driver);
	}
}

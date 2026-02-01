package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

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
	
	private final By loginErrorMessg = By.cssSelector("#account-login > div.alert");
	
	private static final Logger log = LogManager.getLogger(LoginPage.class);
	
	// public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// public page methods/actions

	@Step("getting the login page title...")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login Page title : " + title);
		log.info("Login Page title : " + title);
		return title;
	}

	@Step("getting the login page url...")
	public String getLoginPageUrl() {
		String pageURL = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
	//	System.out.println("LoginPage URL : " + pageURL);
		log.info("LoginPage URL : " + pageURL);
		return pageURL;
	}

	@Step("forgot password link exist?....")
	public boolean IsForgotPwdLinkExists() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("Is header exist?...")
	public boolean IsHeaderExists() {
		return eleUtil.isElementDisplayed(header);
	}
	
	public boolean isRegisterLinkExists() {
		return eleUtil.isElementDisplayed(registerLink);
	}
	
	public boolean isOpencartImageExists() {
		return eleUtil.isElementDisplayed(opencartImage);
	}

	@Step("login with correct username:{0} and password:{1}...")
	public AccountsPage doLogin(String username, String pwd) {
	//	System.out.println("Application credentials: " + username + ":" + pwd);
		log.info("Application credentials: " + username + ":" + pwd);
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);		
//		driver.findElement(emailID).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		return new AccountsPage(driver);	
	}
	
	@Step("login with incorrect username:{0} and password:{1}...")
	public boolean doLoginWithInvalidCredentials(String invalidusername, String invalidpwd) {
			log.info("Application invalidcredentials: " + invalidusername + ":" + invalidpwd);
			eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).clear();
			eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(invalidusername);
			eleUtil.doSendKeys(password, invalidpwd);
	        eleUtil.doClick(loginBtn);		
			String errorMessg=eleUtil.doElementGetText(loginErrorMessg);
			
			log.info("inavlid credentials error message"+errorMessg);
			if(errorMessg.contains(AppConstants.LOGIN_BLANK_CREDENTIALS_MESSG)){
				return true;
			}					
			else if(errorMessg.contains(AppConstants.LOGIN_INVALID_CREDENTIALS_MESSG)){
				return true;
			}
			return false;				
		}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new RegisterPage(driver);
	}
}

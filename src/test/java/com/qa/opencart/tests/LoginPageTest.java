package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EP-100: Design the Login Page")
@Feature("F-101: Design open cart login feature")
@Story("US50: Develop login core features: title, url etc")

public class LoginPageTest extends BaseTest {
	
	//allurereport.org\docs/testng/
	@Description("login page title test..")
	@Owner("Jayasree")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("login page title: "+actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}

	@Test
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageUrl();
		ChainTestListener.log("login page url: "+actURL);
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void isForgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.IsForgotPwdLinkExists());
	}
	
	@Test
	public void isLoginPageHeaderExistsTest() {
		Assert.assertTrue(loginPage.IsHeaderExists());
	}
	
	@Test
	public void isOpenCartImageExistsTest() {
		Assert.assertTrue(loginPage.isOpencartImageExists());
	}
	
	@Test
	public void isRegisterLinkExistsTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExists());
	}
	
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}

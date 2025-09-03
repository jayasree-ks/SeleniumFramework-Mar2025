package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class LoginPageTest extends BaseTest {
	
	
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}

	@Test
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageUrl();
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

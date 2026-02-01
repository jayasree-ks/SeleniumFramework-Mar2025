package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest{
	
	//BeforeTest(Chrome+LoginURL)-->BeforeClass(go to Register Page)
	
	@BeforeClass	
	public void goToRegisterPage() {
		registerPage = loginPage.navigateToRegisterPage();
		
	}
	
	@DataProvider
	public Object[][] getRegData(){
		return new Object[][]{
		{"Ram", "Krishna", "1111111111", "Ram@123", "Yes"},
		{"Shiv", "Ram", "2222222222", "Shiv@123", "No"}
		};
	}
		
	@DataProvider   //Excel Util
	public Object[][] getRegSheetData() {
		return ExcelUtil.getTestData("register");
	}
	
	@DataProvider   //CSV Util
	public Object[][] getRegCSVData(){
		return CsvUtil.csvData("register");
	}
	
	@Test(dataProvider= "getRegSheetData")
	public void registerTest(String firstname, String lastname, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.userRegister(firstname, lastname, StringUtils.getRandomEmail(), telephone, password, subscribe));
		
		
	}

}

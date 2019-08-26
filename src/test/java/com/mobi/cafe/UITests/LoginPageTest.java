package com.mobi.cafe.UITests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mobi.cafe.UITests.models.User;
import com.mobi.cafe.UITests.pages.BasePage;
import com.mobi.cafe.UITests.pages.EmployeeListPage;
import com.mobi.cafe.UITests.pages.LoginPage;

public class LoginPageTest extends BasePage {

	LoginPage loginPage;
	EmployeeListPage employeeListPage;

	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	// TC_0002
	@Test
	public void wrong_username() {
		loginPage.login(new User("Lukey", "Skywalker"));
		Assert.assertEquals(true, loginPage.checkErrorMessage());
	}

	// TC_0005
	@Test
	public void empty_password() {
		loginPage.login(new User("Lukey", ""));
		Assert.assertEquals(true, loginPage.isLoginPage());
	}

}

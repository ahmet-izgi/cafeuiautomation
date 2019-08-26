package com.mobi.cafe.UITests;

import static org.testng.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mobi.cafe.UITests.models.Employee;
import com.mobi.cafe.UITests.models.User;
import com.mobi.cafe.UITests.pages.BasePage;
import com.mobi.cafe.UITests.pages.EmployeeAddPage;
import com.mobi.cafe.UITests.pages.EmployeeEditPage;
import com.mobi.cafe.UITests.pages.EmployeeListPage;
import com.mobi.cafe.UITests.pages.LoginPage;

public class EmployeeAddEditTest extends BasePage {
	LoginPage loginPage;
	EmployeeEditPage employeeEditPage;
	EmployeeAddPage employeeAddPage;
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

	// TC_0008
	@Test
	public void add_employee() {
		Employee employee = new Employee("Ahmet", "İzgi", "2020-01-01", "testing@attention.mail");
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		employeeAddPage = employeeListPage.openCreatePage(employee);
		employeeAddPage.fillEmployee();
		employeeListPage.openEmployeeEditWithDoubleClick(employee);
		assertEquals(employeeAddPage.checkEmployeeOpened(employee), true);
	}

	// TC_0020
	@Test
	public void delete_employee_on_edit_page() {
		Employee employee = new Employee("Ahmet", "İzgi");
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		employeeEditPage = employeeListPage.openEditPage(employee);
		employeeEditPage.deleteEmployee();
	}

	// TC_0009
	@Test
	public void add_employee_with_long_username() {
		Employee employee = new Employee(StringUtils.repeat("a", 300), "İzgi", "2020-01-01", "testing@attention.mail");
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		employeeAddPage = employeeListPage.openCreatePage(employee);
		employeeAddPage.fillEmployee();
		assertEquals(employeeAddPage.checkErrorAlertForCreate(), true);
	}

	// TC_0018
	@Test
	public void edit_employee_with_invalid_email_address() {
		Employee employee = new Employee("John", "Doe", "2020-01-01", "testing_attention.mail");
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		employeeEditPage = employeeListPage.openEditPage(employee);
		employeeEditPage.fillEmployee();
		assertEquals(employeeEditPage.isEmployeeEditPage(), true);
	}
}

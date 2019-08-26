package com.mobi.cafe.UITests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mobi.cafe.UITests.models.Employee;
import com.mobi.cafe.UITests.models.User;
import com.mobi.cafe.UITests.pages.BasePage;
import com.mobi.cafe.UITests.pages.EmployeeEditPage;
import com.mobi.cafe.UITests.pages.EmployeeListPage;
import com.mobi.cafe.UITests.pages.LoginPage;

public class EmployeeListPageTest extends BasePage {
	LoginPage loginPage;
	EmployeeListPage employeeListPage;
	EmployeeEditPage employeeEditPage;

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

	// TC_0006
	@Test
	public void list_employees() {
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		assertEquals(employeeListPage.checkEmployeeList(), true);
	}

	// TC_0007
	@Test
	public void open_employee_details_with_double_click() {
		Employee employee = new Employee("John2", "Doe2");
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		employeeEditPage = employeeListPage.openEmployeeEditWithDoubleClick(employee);

		assertEquals(employeeEditPage.checkEmployeeOpened(employee), true);
	}

	// TC_0013
	@Test 
	public void edit_employee_when_edit_button_disabled() {
		Employee employee = new Employee("John", "Doe");
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		employeeEditPage = employeeListPage.openEditPage(employee);
		assertTrue(employeeListPage.isEmployeeListPage());
	}

	// TC_0021
	@Test
	public void delete_employee_when_delete_button_disabled() {
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		assertFalse(employeeListPage.availableToDelete());
	}

	// TC_0019 ERROR
	// @Test
	public void delete_employee() {
		Employee employee = new Employee("Ahmet", "İzgi");
		employeeListPage = loginPage.login(User.getDefaultCorrectUser());
		long countBeforeDelete = employeeListPage.findEmployeeCountOnList(employee);
		employeeListPage = employeeListPage.deleteEmployee(employee);
		long countAfterDelete = employeeListPage.findEmployeeCountOnList(employee);
		assertEquals(countBeforeDelete, countAfterDelete + 1);
	}
}

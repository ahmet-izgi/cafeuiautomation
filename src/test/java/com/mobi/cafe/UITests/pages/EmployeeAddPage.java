/**
 * 
 */
package com.mobi.cafe.UITests.pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mobi.cafe.UITests.helper.Constants;
import com.mobi.cafe.UITests.models.Employee;

/**
 * @author Ahmet.Izgi
 *
 */
public class EmployeeAddPage extends BasePage {

	private Employee employee;

	@FindBy(xpath = "//p[contains(@ng-click,'logout')]")
	private WebElement buttonLogOut;

	@FindBy(xpath = "//a[contains(@ng-click,'browseToOverview')]")
	private WebElement buttonBack;

	@FindBy(xpath = "//input[contains(@ng-model,'selectedEmployee.firstName')]")
	private WebElement inputFirstName;

	@FindBy(xpath = "//input[contains(@ng-model,'selectedEmployee.lastName')]")
	private WebElement inputLastName;

	@FindBy(xpath = "//input[contains(@ng-model,'selectedEmployee.startDate')]")
	private WebElement inputStartDate;

	@FindBy(xpath = "//input[contains(@ng-model,'selectedEmployee.email')]")
	private WebElement inputEmail;

	@FindBy(xpath = "//button[contains(text(),'Add')]")
	private WebElement buttonUpdate;

	public EmployeeAddPage(Employee employee) {
		urlIdentifier = "new";
		PageFactory.initElements(driver, this);
		this.employee = employee;
		try {
			(new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT)).until(ExpectedConditions.urlContains(urlIdentifier));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used for filling the employee form
	 */
	public void fillEmployee() {
		inputFirstName.clear();
		inputLastName.clear();
		inputStartDate.clear();
		inputEmail.clear();

		if (employee.getFirstName() != null || !employee.getFirstName().isEmpty()) {
			inputFirstName.sendKeys(employee.getFirstName());
		}
		if (employee.getLastName() != null || !employee.getLastName().isEmpty()) {
			inputLastName.sendKeys(employee.getLastName());
		}
		if (employee.getStartDate() != null || !employee.getStartDate().isEmpty()) {
			inputStartDate.sendKeys(employee.getStartDate());
		}
		if (employee.getEmail() != null || !employee.getEmail().isEmpty()) {
			inputEmail.sendKeys(employee.getEmail());
		}
		buttonUpdate.click();
	}

	/**
	 * Check the given employee and the opened employee are same
	 * @param employee
	 * @return
	 */
	public boolean checkEmployeeOpened(Employee employee) {
		// Check opened employee's first and last names equals to shown employee
		return (inputFirstName.getAttribute("value").equals(employee.getFirstName()) || inputLastName.getAttribute("value").equals(employee.getLastName()));
	}

	public void goBack() {
		buttonBack.click();
	}

	/**
	 * Check alert message is displayed with correct message
	 * @return
	 */
	public boolean checkErrorAlertForCreate() {
		try {
			new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT).ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			return driver.switchTo().alert().getText().contains(Constants.EMPLOYEE_CREATION_ERROR);
		} finally {
			driver.switchTo().alert().accept();
		}
	}

	public void logOut() {
		buttonLogOut.click();
	}

}

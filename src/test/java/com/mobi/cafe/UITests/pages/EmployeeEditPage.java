/**
 * 
 */
package com.mobi.cafe.UITests.pages;

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
public class EmployeeEditPage extends BasePage {

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

	@FindBy(xpath = "//button[contains(text(),'Update')]")
	private WebElement buttonUpdate;

	@FindBy(xpath = "//p[contains(@ng-click,'deleteEmployee')]")
	private WebElement buttonDelete;

	public EmployeeEditPage(Employee employee) {
		urlIdentifier = "edit";

		PageFactory.initElements(driver, this);
		this.employee = employee;
		try {
			(new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT)).until(ExpectedConditions.urlContains(urlIdentifier));
		} catch (Exception e) {
			System.out.println("Page did not open");
		}
	}

	public boolean isEmployeeEditPage() {
		return driver.getCurrentUrl().contains(urlIdentifier);
	}

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

	public boolean checkEmployeeOpened(Employee employee) {
		// Check opened employee's first and last names equals to shown employee
		return (inputFirstName.getAttribute("value").equals(employee.getFirstName()) || inputLastName.getAttribute("value").equals(employee.getLastName()));
	}

	public void deleteEmployee() {
		buttonDelete.click();
		confirmAlert();
	}

	public void goBack() {
		buttonBack.click();
	}

	public void logOut() {
		buttonLogOut.click();
	}

}

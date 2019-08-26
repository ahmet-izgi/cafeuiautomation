/**
 * 
 */
package com.mobi.cafe.UITests.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mobi.cafe.UITests.helper.Constants;
import com.mobi.cafe.UITests.helper.Constants.CssClasses;
import com.mobi.cafe.UITests.models.Employee;

/**
 * @author Ahmet.Izgi
 *
 */
public class EmployeeListPage extends BasePage {

	@FindBy(xpath = "//p[contains(@ng-click,'logout')]")
	private WebElement buttonLogOut;

	@FindBy(id = "bAdd")
	private WebElement buttonCreate;

	@FindBy(id = "bEdit")
	private WebElement buttonEdit;

	@FindBy(id = "bDelete")
	private WebElement buttonDelete;

	@FindBy(id = "greetings")
	private WebElement textGreetings;

	@FindBy(xpath = "//*[@id='employee-list']/li")
	private List<WebElement> listPersons;

	public EmployeeListPage() {
		urlIdentifier = "employees";

		PageFactory.initElements(driver, this);
		try {
			(new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT)).until(ExpectedConditions.urlContains(urlIdentifier));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logOut() {
		buttonLogOut.click();
	}

	/**
	 * Clicks on add button
	 * @param employee
	 * @return
	 */
	public EmployeeAddPage openCreatePage(Employee employee) {
		buttonCreate.click();
		return new EmployeeAddPage(employee);
	}

	/**
	 * Finds and selects the given employee on the list and clicks on edit button
	 * @param employee
	 * @return
	 */
	public EmployeeEditPage openEditPage(Employee employee) {
		findEmployeeOnList(employee).click();
		buttonEdit.click();
		return new EmployeeEditPage(employee);
	}

	/**
	 * 
	 * @param employee
	 * @return
	 */
	public EmployeeEditPage openEmployeeEditWithDoubleClick(Employee employee) {
		Actions actions = new Actions(driver);
		actions.doubleClick(findEmployeeOnList(employee)).perform();

		return new EmployeeEditPage(employee);
	}

	/**
	 * find the first occurence of employee with name&surname in the employee-list and click on it for selection
	 * @param employee
	 * @return
	 */
	private WebElement findEmployeeOnList(Employee employee) {
		try {
			return listPersons.stream().filter(person -> person.getText().equals(employee.getFirstName() + " " + employee.getLastName())).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method can be used for counting employee on the list with same first name and last name
	 * @param employee
	 * @return
	 */
	public long findEmployeeCountOnList(Employee employee) {
		return listPersons.stream().filter(person -> person.getText().equals(employee.getFirstName() + " " + employee.getLastName())).count();
	}

	/**
	 * Delete given employee
	 * @param employee
	 * @return
	 */
	public EmployeeListPage deleteEmployee(Employee employee) {
		findEmployeeOnList(employee).click();
		buttonDelete.click();
		confirmAlert();
		return new EmployeeListPage();
	}

	public boolean checkEmployeeList() {
		return listPersons != null;
	}

	/**
	 * Check for correct page
	 */
	public boolean isEmployeeListPage() {
		return driver.getCurrentUrl().contains(urlIdentifier);
	}

	/**
	 * This method is currently not used. But it can be used for removing all employees from the list.
	 */
	public void deleteAllEmployees() {
		listPersons.stream().forEach(element -> {
			element.click();
			buttonDelete.click();
			new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT).ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			Alert al = driver.switchTo().alert();
			al.accept();
		});
	}

	/**
	 * Check the delete button is available to click operation
	 * @return
	 */
	public boolean availableToDelete() {
		return !buttonDelete.getAttribute("class").contains(CssClasses.DISABLED.getClassName());
	}
}

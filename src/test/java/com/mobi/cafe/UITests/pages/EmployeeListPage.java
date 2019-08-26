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

	public EmployeeAddPage openCreatePage(Employee employee) {
		buttonCreate.click();
		return new EmployeeAddPage(employee);
	}

	public EmployeeEditPage openEditPage(Employee employee) {
		findEmployeeOnList(employee).click();
		buttonEdit.click();
		return new EmployeeEditPage(employee);
	}

	public EmployeeEditPage openEmployeeEditWithDoubleClick(Employee employee) {
		Actions actions = new Actions(driver);
		actions.doubleClick(findEmployeeOnList(employee)).perform();

		return new EmployeeEditPage(employee);
	}

	// find the first occurence of employee with name&surname in the employee-list and click on it for selection
	private WebElement findEmployeeOnList(Employee employee) {
		try {
			return listPersons.stream().filter(person -> person.getText().equals(employee.getFirstName() + " " + employee.getLastName())).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	public long findEmployeeCountOnList(Employee employee) {
		System.out.println("person count" + listPersons.stream().filter(person -> person.getText().equals(employee.getFirstName() + " " + employee.getLastName())).count());
		return listPersons.stream().filter(person -> person.getText().equals(employee.getFirstName() + " " + employee.getLastName())).count();
	}

	public EmployeeListPage deleteEmployee(Employee employee) {
		findEmployeeOnList(employee).click();
		buttonDelete.click();
		confirmAlert();
		return new EmployeeListPage();
	}

	public boolean checkEmployeeList() {
		return listPersons != null;
	}

	/*
	 * Check for correct page
	 */
	public boolean isEmployeeListPage() {
		return driver.getCurrentUrl().contains(urlIdentifier);
	}

	public void deleteAllEmployees() {
		listPersons.stream().forEach(element -> {
			element.click();
			buttonDelete.click();
			new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT).ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			Alert al = driver.switchTo().alert();
			al.accept();
		});
	}

	public boolean availableToDelete() {
		return !buttonDelete.getAttribute("class").contains(CssClasses.DISABLED.getClassName());
	}
}

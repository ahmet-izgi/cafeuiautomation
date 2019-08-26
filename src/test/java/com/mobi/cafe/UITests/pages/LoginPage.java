package com.mobi.cafe.UITests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mobi.cafe.UITests.helper.Constants;
import com.mobi.cafe.UITests.models.User;

/**
 * @author Ahmet.Izgi
 *
 */
public class LoginPage extends BasePage {

	@FindBy(xpath = "//input[@ng-model='user.name']")
	private WebElement inputUserName;

	@FindBy(xpath = "//input[@ng-model='user.password']")
	private WebElement inputPassword;

	// Main button used everywhere hence it is not a good way to find a button. Using localized "Login" text is better way to find the button.
	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement buttonLogin;

	@FindBy(xpath = "//p[contains(@ng-show,'showMessage')]")
	private WebElement textErrorMessage;

	public LoginPage() {
		urlIdentifier = "login";

		PageFactory.initElements(driver, this);
		try {
			(new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT)).until(ExpectedConditions.urlContains(urlIdentifier));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EmployeeListPage login(User user) {
		inputUserName.sendKeys(user.getUsername());
		inputPassword.sendKeys(user.getPassword());
		buttonLogin.click();
		return new EmployeeListPage();
	}

	public boolean checkErrorMessage() {
		// Test automation should support localization!
		return textErrorMessage.getText().equals("Invalid username or password!");
	}

	public boolean isLoginPage() {
		return driver.getCurrentUrl().contains(urlIdentifier);
	}
}

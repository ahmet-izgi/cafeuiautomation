package com.mobi.cafe.UITests.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.mobi.cafe.UITests.helper.Constants;
import com.mobi.cafe.UITests.helper.Constants.Browsers;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Ahmet.Izgi
 *
 */
public abstract class BasePage {
	protected static WebDriver driver;
	protected String urlIdentifier;

	/**
	 * This method used before every testcase.
	 */
	public static void initialize() {
		// We can read selected browser with a property file. I used Chrome as default.
		if (Browsers.CHROME == Browsers.CHROME) {
			driver = new ChromeDriver();
		} else if (Browsers.FIREFOX == Browsers.FIREFOX) {
			driver = new FirefoxDriver();
		} else if (Browsers.PHANTOM == Browsers.PHANTOM) {
			WebDriverManager.phantomjs().setup();
			driver = new PhantomJSDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(Constants.STARTUP_URL);
	}

	/**
	 * This method is used for to sleep the thread if necessary.
	 * @param millis
	 */
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Confirming alert dialogs when necessary.
	 * 
	 */
	public void confirmAlert() {
		if (driver instanceof PhantomJSDriver) {
			PhantomJSDriver phantom = (PhantomJSDriver) driver;
			phantom.executeScript("window.alert = function(){}");
			phantom.executeScript("window.confirm = function(){return true;}");
		} else {
			driver.switchTo().alert().accept();
		}
	}
}

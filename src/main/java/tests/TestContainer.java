package tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import taf.core.exceptions.DriverNotFoundException;
import taf.core.reporting.TestReporter;
import taf.core.reporting.Validate;
import taf.extensions.webdriver.factory.DriverFactory;


public class TestContainer {

	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeClass
	public void Setup(String browser) throws InterruptedException,
			DriverNotFoundException {
		// TestReporter.log("--Setup--");
		// System.setProperty("webdriver.chrome.driver",
		// "External\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new DriverFactory().getDriver(browser);

		driver.get("https://www.google.com.tr/");

		Thread.sleep(15000);
	}

	@Test(description = "test module")
	public void test1() throws Exception {

	}

	@AfterClass
	public void TearDown() {
		 driver.quit();
	}
}

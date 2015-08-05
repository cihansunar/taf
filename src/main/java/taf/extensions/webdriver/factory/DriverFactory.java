package taf.extensions.webdriver.factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import taf.core.exceptions.DriverNotFoundException;

public class DriverFactory 
{

	public WebDriver getDriver(String driverName) throws DriverNotFoundException
	{
		WebDriver driver;

		if(driverName.equalsIgnoreCase("firefox")){

			//create firefox instance

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}

		//Check if parameter passed as 'chrome'

		else if(driverName.equalsIgnoreCase("chrome")){

			//set path to chromedriver.exe You may need to download it from http://code.google.com/p/selenium/wiki/ChromeDriver

			System.setProperty("webdriver.chrome.driver","External\\Drivers\\chromedriver_win32\\chromedriver.exe");

			//create chrome instance

			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}

		else if(driverName.equalsIgnoreCase("ie32")){



			System.setProperty("webdriver.ie.driver","External\\Drivers\\iedriver_win32\\IEDriverServer.exe");

			//create chrome instance
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(ieCapabilities);

		}
		else if(driverName.equalsIgnoreCase("ie64")){



			System.setProperty("webdriver.ie.driver","External\\Drivers\\iedriver_win64\\IEDriverServer.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			//create chrome instance

			driver = new InternetExplorerDriver(ieCapabilities);

		}

		else{

			//If no browser passed throw exception

			throw new DriverNotFoundException("Requested driver is not correct");

		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}


}

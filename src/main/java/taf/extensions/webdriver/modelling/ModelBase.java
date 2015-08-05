package taf.extensions.webdriver.modelling;

import org.openqa.selenium.WebDriver;

public abstract class ModelBase 
{
	protected WebDriver driver;
	public ModelBase(WebDriver webDriver)
	{
		driver = webDriver;
	}

}

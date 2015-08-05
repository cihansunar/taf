package taf.extensions.webdriver.modelling;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import taf.core.exceptions.ElementNotFoundException;
import taf.core.reporting.LogType;
import taf.core.reporting.TestReporter;


public abstract class ElementBase 
{
	protected WebDriver driver;
	protected By findPattern;
	protected String elementName;

	public ElementBase(WebDriver webDriver, By pattern, String name)
	{
		driver = webDriver;
		findPattern=pattern;
		elementName =name;
	}

	protected WebElement retrieveElement() throws ElementNotFoundException
	{
		WebElement element = null;
		try
		{
		element = driver.findElement(findPattern);
		return element;
		}
		catch(NoSuchElementException ex)
		{
			TestReporter.log(LogType.FAILURE, "Element Not Found:" + this.elementName);
			throw new ElementNotFoundException("Element Not Found:" +this.elementName);
		}
	}
	public void moveTo() throws ElementNotFoundException{
		WebElement element = this.retrieveElement();
		new Actions(driver).moveToElement(element).perform();
	}
	
	public void click() throws ElementNotFoundException
	{
		WebElement element = this.retrieveElement();
		//new Actions(driver).click().perform();
		
		element.click();
	}
	public String getAttribute(String attribute) throws Exception
	{
		WebElement element = this.retrieveElement();
		return element.getAttribute(attribute);
	}
	public boolean isExists()
	{
		try
		{
			WebElement element = this.retrieveElement();
			if(element != null)
				return true;
			return false;
		}catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			System.out.println(this.findPattern.toString());
			System.out.println(ex.getStackTrace());
			return false;
		}
	}
	
	public boolean isEnabled() throws ElementNotFoundException
	{
		try
		{
			WebElement element = this.retrieveElement();
			if(element != null)
				return element.isEnabled();
			return false;
		}catch (ElementNotFoundException ex)
		{
			System.out.println(ex.getMessage());
			System.out.println(this.findPattern.toString());
			System.out.println(ex.getStackTrace());
			throw ex;
		}
	}
}

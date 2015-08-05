package taf.library.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import taf.extensions.webdriver.modelling.ElementBase;

public class TextControl extends ElementBase{

	
	public TextControl(WebDriver webDriver, By pattern, String name) 
	{
		super(webDriver, pattern, name);
	
	}
	public void type(String text) throws Exception
	{
		WebElement element = this.retrieveElement();
		//element.click();	
		element.sendKeys(text);
	}
	
	public String getTextValue() throws Exception
	{
		return this.retrieveElement().getText();		
	}
}

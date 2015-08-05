package taf.library.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import taf.extensions.webdriver.modelling.ElementBase;

public class ButtonControl extends ElementBase{

	public ButtonControl(WebDriver webDriver, By pattern, String name) {
		super(webDriver, pattern, name);
		// TODO Auto-generated constructor stub
	}
	public String getTextValue() throws Exception
	{
		return this.getAttribute("Text");		
	}

}

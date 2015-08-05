package taf.library.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import taf.extensions.webdriver.modelling.ElementBase;

public class LabelControl  extends ElementBase{

	public LabelControl(WebDriver webDriver, By pattern, String name) {
		super(webDriver, pattern, name);
		// TODO Auto-generated constructor stub
	}
	public String getTextValue() throws Exception
	{
		return this.retrieveElement().getText();
		//return this.GetAttribute("Text");		
	}
}

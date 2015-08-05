package taf.library.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import taf.core.exceptions.ElementNotFoundException;
import taf.extensions.webdriver.modelling.ElementBase;

public class CheckBoxControl extends ElementBase {

	public CheckBoxControl(WebDriver webDriver, By pattern, String name)
	{
		super(webDriver, pattern, name);
		// TODO Auto-generated constructor stub
	}
	public boolean isChecked() throws ElementNotFoundException
	{
		return super.retrieveElement().isSelected();
	}
}

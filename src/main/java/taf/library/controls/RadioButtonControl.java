package taf.library.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import taf.extensions.webdriver.modelling.ElementBase;

public class RadioButtonControl extends ElementBase{

	public RadioButtonControl(WebDriver webDriver, By pattern , String name) {
		super(webDriver, pattern, name);
		// TODO Auto-generated constructor stub
	}

public boolean isSelected() throws Exception
{
	
	return super.retrieveElement().isSelected();
}


}

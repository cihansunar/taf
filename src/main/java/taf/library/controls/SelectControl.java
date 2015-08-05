package taf.library.controls;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import taf.core.exceptions.ElementNotFoundException;
import taf.extensions.webdriver.modelling.ElementBase;

public class SelectControl extends ElementBase {

	public SelectControl(WebDriver webDriver, By pattern, String name) {
		super(webDriver, pattern, name);
		// TODO Auto-generated constructor stub
	}
	
	public void selectByValue(String value) throws ElementNotFoundException
	{
		new Select( super.retrieveElement()).selectByValue(value);
	}
	
	public void selectByIndex(int index) throws ElementNotFoundException
	{
		new Select( super.retrieveElement()).selectByIndex(index);
	}
	
	public void selectByVisibleText(String text) throws ElementNotFoundException
	{
		new Select( super.retrieveElement()).selectByVisibleText(text);
	}
	
	public String getSelectedValue() throws ElementNotFoundException
	{
		return new Select( super.retrieveElement()).getAllSelectedOptions().get(0).getText();
	}
	
	public List<String> getSelectedValues() throws ElementNotFoundException
	{
		List<String> selectedValues = new ArrayList<String>();
		for(WebElement element : new Select( super.retrieveElement()).getAllSelectedOptions())
			selectedValues.add(element.getText());
		return selectedValues;
	}

	public List<String> getAllSelectOptions() throws ElementNotFoundException
	{
		List<String> options = new ArrayList<String>();
		for(WebElement element : new Select( super.retrieveElement()).getOptions())
			options.add(element.getText());
		return options;
	}
}

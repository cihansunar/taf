package taf.library.controls;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import taf.extensions.webdriver.modelling.ElementBase;
import taf.extensions.webdriver.modelling.ElementContainer;

public class HtmlTableControl  extends ElementBase{

	public HtmlTableControl(WebDriver webDriver, By pattern, String name) {
		super(webDriver, pattern, name);
		// TODO Auto-generated constructor stub
	}
	//TODO it is important to implement this control
	public ElementContainer getHTMLTableValue(int row, int column, int size, By pattern) throws Exception
	{
		int value = (row-1)*size+column;
		System.out.println("Row Size");
		System.out.println(this.retrieveElement().findElements(By.tagName("td")).size());
		System.out.println("Column size");
		System.out.println(this.retrieveElement().findElements(By.tagName("td")).get(value));
		System.out.println(this.retrieveElement().findElements(By.tagName("td")).get(value).getText());
		
		return new ElementContainer(this.driver, this.retrieveElement().findElements(By.tagName("td")).get(value).findElement(pattern),"HTML Table Value");
	}
	public ElementContainer getHTMLTableValue(int row, int column, int size) throws Exception
	{
		int value = (row-1)*size+column;
		return new ElementContainer(this.driver, this.retrieveElement().findElements(By.tagName("td")).get(value),"HTML Table Value");
	}
}

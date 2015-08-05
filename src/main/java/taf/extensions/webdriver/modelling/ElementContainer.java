package taf.extensions.webdriver.modelling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementContainer extends ElementBase 
{
	WebElement Self;
	public ElementContainer(WebDriver webDriver, WebElement element, String name ) {
		super(webDriver, null,name);
		// TODO Auto-generated constructor stub
		Self = element;
	}
	
	protected WebElement retrieveElement(){
		
		return Self;
	}
	public String GetText(){
		return this.retrieveElement().getText();
	}
	

}

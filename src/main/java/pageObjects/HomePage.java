package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	private WebElement myAccountDropDown;
	
	public void clickMyAccountDropdown() {
		myAccountDropDown.click();
	}
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	public void selectRegisterOption() {
		registerOption.click();
	}

}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountpage {
	
	WebDriver driver;
	
	public MyAccountpage(WebDriver driver) {
		
		this.driver = driver;	
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='top-links']//a[contains(@href, 'route=account/logout')]")
	private WebElement Logout;
	
	public void clickLogoutBtn() {
		Logout.click();
	}

}

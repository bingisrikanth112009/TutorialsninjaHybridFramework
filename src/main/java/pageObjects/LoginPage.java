package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);		
	}
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailID;		
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement password;	
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement login;
	
	@FindBy(xpath ="//div[@class=\"alert alert-danger alert-dismissible\"]")
	private WebElement warningMsg;
	@FindBy(xpath="//div[@class='form-group']//a[text()='Forgotten Password']")
	WebElement forgotPswrdLink;
	
	
	@FindBy (xpath="//input[@placeholder='E-Mail Address']")
	WebElement emailFiledText;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement passwordFieldText;
	public void clickForgotPasswordLink() {
		forgotPswrdLink.click();
	}
	
	public void enterEmail(String emailTxt) {
		emailID.sendKeys(emailTxt);
	}
	
	public String emailFiledText() {
		return emailFiledText.getAttribute("placeholder");
	}
	public void enterPassword(String passwordTxt) {
		password.sendKeys(passwordTxt);
	}
	public String passwordFiledText() {
		return passwordFieldText.getAttribute("placeholder");
	}
	public String passwordFiledTextvalue() {
		return passwordFieldText.getAttribute("value");
	}
	public void clickLoginButton() {
		login.click();
	}		
	
	public WebElement warningLoginMsg() {
		return warningMsg;		
		
	}
	
}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='firstname']")
	private WebElement firstName;	
	public void enterFirstName(String firstname) {		
		firstName.sendKeys(firstname);
	}
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement lastName;
	public void enterLastName(String lastname) {		
		lastName.sendKeys(lastname);
	}
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement emailID;	
	public void enterEmail(String email) {		
		emailID.sendKeys(email);
	}
	
	@FindBy(xpath="//input[@name='telephone']")
	private WebElement telePhone;
	public void enterTeliphoneNbr(String telephoneNbr) {		
		telePhone.sendKeys(telephoneNbr);
	}
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement Password;
	public void enterPassword(String password) {		
		Password.sendKeys(password);
	}
	
	@FindBy(xpath="//input[@name='confirm']")
	private WebElement confirmPassword;
	public void enterConfirmPassword(String confirmPswrd) {		
		confirmPassword.sendKeys(confirmPswrd);
	}
	
	@FindBy(xpath="//input[@value='newsletter']")
	private WebElement newsletter;
	public void selectNewsLetterYes() {		
		newsletter.click();
	}
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicy;
	public void selectPrivacyPolicy() {		
		privacyPolicy.click();
	}
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueBtn;
	public void clicklContinueBtn() {		
		continueBtn.click();
	}
	

}

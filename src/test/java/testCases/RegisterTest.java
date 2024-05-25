package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import qaBase.Base;

public class RegisterTest extends Base{
	public RegisterTest() {
		super();
	}
	public static WebDriver driver;
	public static HomePage homePage;
	public static RegisterPage registerPage;
	public static AccountSuccessPage accountSuccessPage;
	@BeforeMethod
	public void setUp() {		
		driver = initializebrowserAndApplication(prop.getProperty("browserName"));		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test
	public static void registerWithMandatoryFileds() throws InterruptedException {
		
		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
		
		homePage.clickMyAccountDropdown();
		homePage.selectRegisterOption();
		registerPage.enterFirstName("srikanth");
		registerPage.enterLastName("B");
		registerPage.enterEmail("bsrikanthghg@gmail.com");
		registerPage.enterTeliphoneNbr("3265124578");
		registerPage.enterPassword("12345678");
		registerPage.enterConfirmPassword("12345678");			
		registerPage.selectPrivacyPolicy();
		registerPage.clicklContinueBtn();
		Thread.sleep(3000);
		accountSuccessPage = new AccountSuccessPage(driver);
		Assert.assertEquals(accountSuccessPage.accountCreatedSuccessfully(), "Your Account Has Been Created!", "Account not created");		
		//Assert.assertTrue(accountSuccessPage.accountCreatedSuccessfully().contains(), "User account not created");	
	}

}

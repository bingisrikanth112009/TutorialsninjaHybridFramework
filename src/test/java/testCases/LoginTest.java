package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import qaBase.Base;

public class LoginTest extends Base {
	public LoginTest() {
		super();
	}
	public static HomePage homePage;
	public static LoginPage loginPage;
	public static MyAccountpage myAccountpage;
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = initializebrowserAndApplication(prop.getProperty("browserName"));		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void loginWithValidCredentials() {
		homePage = new HomePage(driver);		
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();	
		
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();		
		String actualResult= driver.getTitle();
		Assert.assertTrue(actualResult.contains("My Account"), "User not logged in to the applcation");
		
	}
	@Test(priority=2)
	public void loginWithInvalidCredentials() {
		homePage = new HomePage(driver);
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();
		
		loginPage.enterEmail("bsrikantfdh@gmail.com");
		loginPage.enterPassword("900ddd0190660");
		loginPage.clickLoginButton();		
		
		String acterrorMsg = loginPage.warningLoginMsg().getText();	
		
		Assert.assertTrue(acterrorMsg.contains("Warning: No match for E-Mail Address and/or Password."),"Warning message not displayed");
	}
	@Test(priority=3)
	public void loginWithoutCredentials() {
		homePage = new HomePage(driver);
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();		
		loginPage.clickLoginButton();			
		String acterrorMsg = loginPage.warningLoginMsg().getText();	
		
		Assert.assertTrue(acterrorMsg.contains("Warning: No match for E-Mail Address and/or Password."),"Warning message not displayed");		
	}
	
	@Test(priority=4)
	public void loginWithInvalidUserAndValidPassword() {
		homePage = new HomePage(driver);
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();
		loginPage.enterEmail(prop.getProperty("invalidEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));		
		loginPage.clickLoginButton();	
		String acterrorMsg = loginPage.warningLoginMsg().getText();	
		
		Assert.assertTrue(acterrorMsg.contains("Warning: No match for E-Mail Address and/or Password."),"Warning message not displayed");		
		
	}
	@Test(priority=5)
	public void loginWithValidUserAndinvalidPassword() {
		homePage = new HomePage(driver);
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("invalidPassword"));		
		loginPage.clickLoginButton();	
		String acterrorMsg = loginPage.warningLoginMsg().getText();	
		
		Assert.assertTrue(acterrorMsg.contains("Warning: No match for E-Mail Address and/or Password."),"Warning message not displayed");		
		
	}	
	
	@Test(priority=6)
	public void verifyForgotPasswordOptionLink() {
		
		homePage = new HomePage(driver);
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();
		loginPage.clickForgotPasswordLink();
		
		
		String actualPasswordRecovery = driver.getTitle();
		String expectedPasswordRecovery = "Forgot Your Password?";
		Assert.assertTrue(actualPasswordRecovery.contains(expectedPasswordRecovery),"Expected password recovery messge not displayed");
	}
	@Test(priority=7)
	public void verifyEmailAndPasswordPlaceHolderText() {
		homePage = new HomePage(driver);
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();
		
		Assert.assertEquals(loginPage.emailFiledText(), "E-Mail Address", "No email filed place holder");
		
		Assert.assertEquals(loginPage.passwordFiledText(), "Password", "No password filed place holder");		
	}
	@Test(priority=8)
	public void verifyLogginInToApplicationAndBrowserBackOptn() {
		homePage = new HomePage(driver);		
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();	
		
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();		
		driver.navigate().back();
		String actualResult= driver.getTitle();
		Assert.assertTrue(actualResult.contains("My Account"), "User logged out from the application");
	}
	
	@Test(priority=9)
	public void verifyLogoutFromApplicationAndBrowserBackOptn() throws InterruptedException{
		//Verify Logging out from the Application and browsing back using Browser back button
		homePage = new HomePage(driver);		
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();	
		
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();	
		homePage.clickMyAccountDropdown();
		
		myAccountpage = new MyAccountpage(driver);		
		myAccountpage.clickLogoutBtn();
		driver.navigate().back();
		Thread.sleep(2000);
		String actualResult= driver.getTitle();
		Assert.assertTrue(actualResult.contains("Account Logout"), "User logged out from the application");
	}
	@Test(priority = 10)
	public void loginWithInactiveCredentials() {	
	//Verify logging into the Application using inactive credentials
		homePage = new HomePage(driver);		
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();	
		
		loginPage.enterEmail(prop.getProperty("invalidEmail"));
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickLoginButton();
		
		String acterrorMsg = loginPage.warningLoginMsg().getText();	
		
		Assert.assertTrue(acterrorMsg.contains("Warning: No match for E-Mail Address and/or Password."),"Warning message not displayed for inactive credentials");
	}
	@Test(priority = 11)
	public void verifyNumberofUnsuccessfulLoginAtepmts() {
		//Verify the number of unsucessful login attemps 
		int maxAtempts= 8;
		for(int i=0; i<=maxAtempts;i++) {
		homePage = new HomePage(driver);		
		homePage.clickMyAccountDropdown();
		LoginPage loginPage=homePage.selectLoginOption();	
		loginPage.enterEmail(prop.getProperty("invalidEmail"));
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickLoginButton();
		
		String acterrorMsg = loginPage.warningLoginMsg().getText();	
		
		Assert.assertTrue(acterrorMsg.contains("Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour."),"Warning message not displayed");
		
		}		
	}		
		@Test(priority = 12)
		public void verifyThetextPasswordFieldisToggledtoHideVisibility() {
			//Verify the text into the Password field is toggled to hide its visibility
			homePage = new HomePage(driver);		
			homePage.clickMyAccountDropdown();
			LoginPage loginPage=homePage.selectLoginOption();	
			loginPage.enterEmail(prop.getProperty("invalidEmail"));
			loginPage.enterPassword(prop.getProperty("invalidPassword"));	
			String passwordTxt = loginPage.passwordFiledTextvalue();
			Assert.assertEquals(passwordTxt, "********");			
	}
	
}

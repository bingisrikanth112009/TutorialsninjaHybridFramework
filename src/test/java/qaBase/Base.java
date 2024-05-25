package qaBase;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {	
	
	WebDriver driver;
	public Properties prop;
	
	public  Base() {
		prop= new Properties();
		File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\Configuration\\config.properties");
		try {
			FileInputStream fistrm= new FileInputStream(propFile);
			prop.load(fistrm);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		public WebDriver initializebrowserAndApplication(String browser) {
			if(browser.equals("chrome")) {
				driver = new ChromeDriver();
			}else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
			}		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		driver.get(prop.getProperty("url"));
		return driver;
}

}

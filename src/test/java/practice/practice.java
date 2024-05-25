package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class practice {
	
	 static WebDriver driver;
	
	public static void main(String[] args){
		driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");			
		
       driver.findElement(By.linkText("My Account")).click();
      
        
        
		
	}
	
	
}

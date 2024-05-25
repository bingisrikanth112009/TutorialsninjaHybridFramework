package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utilities.ExtentReporter;
import utilities.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentest;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport= ExtentReporter.generateExtentReports();		
		}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		extentest = extentReport.createTest(result.getName());
		extentest.log(Status.INFO,result.getName()+"started execution");				
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentest.log(Status.PASS, result.getName()+ "Got successfully executed");		
	}

	@Override
	public void onTestFailure(ITestResult result) {
			
		
		WebDriver driver= null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {			
			e.printStackTrace();
		}
		
		String destinationScreenshotPath= Utilities.captureScreenshot(driver, result.getName());
		extentest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentest.log(Status.INFO, result.getThrowable());
		extentest.log(Status.FAIL, result.getName()+"Test got failed");	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentest.log(Status.INFO, result.getThrowable());
		extentest.log(Status.SKIP, result.getName()+"Test got skipped");		
	}	

	@Override
	public void onFinish(ITestContext context) {		
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}	
	

}

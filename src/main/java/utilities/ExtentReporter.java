package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporter {
	
	public static ExtentReports generateExtentReports( ){
	ExtentReports extentReports = new ExtentReports();
	File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
	spark.config().setTheme(Theme.DARK);
	spark.config().setReportName("NOP commerce automation test results");
	spark.config().setDocumentTitle("NOP Automation report");
	spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReports.attachReporter(spark);
	
	Properties configProp = new Properties();
	File configPropFile = new File (System.getProperty("user.dir")+"\\src\\main\\java\\Configuration\\config.properties");
	try {
	FileInputStream FInpStrem = new FileInputStream(configPropFile);
			configProp.load(FInpStrem);
	} catch (Throwable e) {		
		e.printStackTrace();
	}		
	extentReports.setSystemInfo("Application URL", configProp.getProperty("url"));
	extentReports.setSystemInfo("Browser Namer", configProp.getProperty("browserName"));
	extentReports.setSystemInfo("Email", configProp.getProperty("email"));
	extentReports.setSystemInfo("Password", configProp.getProperty("password"));
	extentReports.setSystemInfo("Operating System", configProp.getProperty("os.name"));
	extentReports.setSystemInfo("User Name", configProp.getProperty("user.name"));
	extentReports.setSystemInfo("Java Version", configProp.getProperty("java.version"));
	
	return extentReports;	
	}
}

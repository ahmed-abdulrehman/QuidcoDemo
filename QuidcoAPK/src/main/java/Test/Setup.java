package Test;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import Config.AndroidBase;
import Config.Utility;
import io.appium.java_client.android.AndroidDriver;


public class Setup {
	
	public static AndroidDriver driver;
	
	AndroidBase androidBase;
	Utility utility;
	
	@BeforeTest
	// Starting my extent report
	public void startingReport() throws Exception {
		utility = new Utility(driver);
		utility.startReport();
	}
	
	@BeforeMethod
	// Starting APK
	public void browser() throws Exception {
		androidBase = new AndroidBase(driver);
		
		androidBase.startAPK();
	}
	
	@AfterMethod
	// Updating extent report and closing the driver
	public void tearDown(ITestResult result) {
		utility = new Utility(driver);
		utility.logFailStatus(result, "Fail");
		
		driver.quit();
	}
	
	@AfterTest
	// Closing the report
	public void endingReport() {
		utility = new Utility(driver);
		utility.endReport();
	}

}

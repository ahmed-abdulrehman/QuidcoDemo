package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import Test.Setup;
import io.appium.java_client.android.AndroidDriver;

public class AndroidBase extends Setup {

	Utility utility;
	DesiredCapabilities capabilities;
	
	private String deviceFileInputPath = "..//QuidcoAPK//Config//DeviceSettings//device.properties";
	private String apkFileInputPath = "..//QuidcoAPK//Config//AppSettings//ApkApp.properties";
	
	public AndroidBase(AndroidDriver driver) {
		Setup.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Getting the tested Device & app values which can be found in the config folder
	private void gettingValues(String value, String fileInputPath) throws IOException {
		utility = new Utility(driver);
		Properties settingProperty = new Properties();
		FileInputStream fileInput = new FileInputStream(fileInputPath);
		settingProperty.load(fileInput);		
		capabilities.setCapability(value, settingProperty.getProperty(value));
	}
	
	// Getting the parameter for the tested android device
	private void getDevice(String deviceValue) throws IOException {
		gettingValues(deviceValue, deviceFileInputPath);
	}
	
	// Getting the parameter for the tested apk
	private void getApk(String apkValue) throws IOException {
		gettingValues(apkValue, apkFileInputPath);
	}
	
	// Open APK
	public void startAPK() throws IOException {	
		utility = new Utility(driver);
		capabilities = new DesiredCapabilities();

		getDevice("platformName");
		getDevice("deviceName");
		getDevice("platformVersion");
		getApk("appActivity");
		getApk("appPackage");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

	


}

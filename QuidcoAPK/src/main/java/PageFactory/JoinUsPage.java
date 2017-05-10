package PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Config.Utility;
import io.appium.java_client.android.AndroidDriver;

public class JoinUsPage {

	public AndroidDriver driver;
	Utility utility;

	// First name field element
	@FindBy(id = "com.quidco:id/join_first_name")
	private WebElement firstNameField;

	// Last name field element
	@FindBy(id = "com.quidco:id/join_last_name")
	private WebElement lastNameField;
	
	// Email address field element
	@FindBy(id = "com.quidco:id/join_email_address")
	private WebElement emailAddressField;
	
	// Password field element
	@FindBy(id = "com.quidco:id/join_password")
	private WebElement passwordField;	
	
	// Join Us button element
	@FindBy(className = "android.widget.Button")
	private WebElement joinUsSubmitButton;	
	
	public JoinUsPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Set input
	private void setInput(String input, WebElement element) throws IOException {
		utility = new Utility(driver);
		
		Properties inputProperty = new Properties();
		FileInputStream fileInput = new FileInputStream("..//QuidcoAPK//Config//SignUp//signUp.properties");
		inputProperty.load(fileInput);
				
		element.clear();
		element.sendKeys(inputProperty.getProperty(input));
		
		utility.logInfoStatus("Adding "+input);
	}

	
	// This method is used for clicking
	private void clicking(WebElement element, String logInfo) throws IOException, InterruptedException {
		utility = new Utility(driver);
		
		element.click();
		Thread.sleep(3000);
		
		utility.logInfoStatus("Clicked "+logInfo);	
	}
	
	// Scroll to the desired text
	private void scrollTo(String textView) throws IOException, InterruptedException
	{		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+textView+"\").instance(0))");      
	}


	
	// Creating an account
	public void signUp() throws IOException, InterruptedException
	{
		setInput("firstName", firstNameField);
		setInput("lastName", lastNameField);
		setInput("emailAddress", emailAddressField);
		scrollTo("Read our Terms & Conditions");
		setInput("password", passwordField);
		clicking(joinUsSubmitButton, "Join us button");
	}
	
}

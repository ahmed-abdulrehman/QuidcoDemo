package PageFactory;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Config.Utility;
import io.appium.java_client.android.AndroidDriver;

public class IntroPage {
	
	public AndroidDriver driver;
	Utility utility;
	
	// Skip button element
	@FindBy(id = "com.quidco:id/skip_button")
	private WebElement skipButton;
	
	// Join us button element
	@FindBy(id = "com.quidco:id/join_button")
	private WebElement joinUsButton;
	
	public IntroPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// This method is for clicking
	private void clicking(WebElement element, String logInfo) throws IOException {
		utility = new Utility(driver);
		
		element.click();
		
		utility.logInfoStatus("Clicked "+logInfo);	
	}
	
	// Navigate to Join us page
	public void goToJoinUsPage() throws IOException{
		clicking(skipButton, "Skip button");
		clicking(joinUsButton, "Join us button");
	}

}

package PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Config.Utility;

public class IntroPage {
	
	public WebDriver driver;
	Utility utility;
	
	// Skip button element
	@FindBy(id = "com.quidco:id/skip_button")
	private WebElement skipButton;
	
	// Join us button element
	@FindBy(id = "com.quidco:id/join_button")
	private WebElement joinUsButton;
	
	public IntroPage(WebDriver driver) {
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

package PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Config.Utility;

public class ResponsiveMenu {

	public WebDriver driver;
	Utility utility;

	// Page title element
	@FindBy(className = "android.widget.TextView")
	private WebElement pageTitle;

	// Burger menu element
	@FindBy(className = "android.widget.ImageButton")
	private WebElement burgerMenu;

	// Account button element
	@FindBy(xpath = "//android.widget.RelativeLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.support.v7.widget.RecyclerView[@index='0']/android.support.v7.widget.aq[@index='4']/android.widget.CheckedTextView[@index='0']")
	private WebElement accountButton;

	// Account name element
	@FindBy(id = "com.quidco:id/account_name")
	private WebElement accountName;
	
	// First name element
	@FindBy(id = "com.quidco:id/first_name_edit_text")
	private WebElement firstName;
	
	// Last name element
	@FindBy(id = "com.quidco:id/last_name_edit_text")
	private WebElement lastName;

	public ResponsiveMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This method is used for clicking
	private void clicking(WebElement element, String logInfo) throws IOException {
		utility = new Utility(driver);

		element.click();

		utility.logInfoStatus("Clicked " + logInfo);
	}

	// Assert Account name
	private void assertAccountName(String theFirstName, String theLastName, WebElement elementOne, WebElement elementTwo) throws IOException, InterruptedException {
		Properties property = new Properties();
		FileInputStream fileInput = new FileInputStream("..//QuidcoAPK//Config//SignUp//signUp.properties");
		property.load(fileInput);

		Thread.sleep(2000);
		Assert.assertEquals(property.getProperty(theFirstName), elementOne.getText());
		Assert.assertEquals(property.getProperty(theLastName), elementTwo.getText());

		utility.logInfoStatus("Asserting account name");
	}

	private void assertPageTitle(String thePageTitle, WebElement element) throws InterruptedException, IOException {
		utility = new Utility(driver);

		Thread.sleep(2000);
		Assert.assertEquals(thePageTitle, element.getText());

		utility.logInfoStatus("Asserting page title");
	}

	// Confirm registration is successful
	public void confirmRegistrationIsSuccessful() throws IOException, InterruptedException {
		assertPageTitle("Home", pageTitle);
		clicking(burgerMenu, "Responsive menu");
		clicking(accountButton, "Account button in responsive menu");
		clicking(accountName, "Account name");
		assertAccountName("firstName", "lastName", firstName, lastName);
	}

}

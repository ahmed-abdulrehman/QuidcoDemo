package Test;

import java.io.IOException;

import org.testng.annotations.Test;

import Config.Utility;
import PageFactory.IntroPage;
import PageFactory.JoinUsPage;
import PageFactory.ResponsiveMenu;

public class JoinUsTest extends Setup{
	
	Utility utility;
	IntroPage introPage;
	JoinUsPage joinUsPage;
	ResponsiveMenu responsiveMenu;
	
	@Test
	public void signUp() throws InterruptedException, IOException {
		utility = new Utility(driver);
		introPage = new IntroPage(driver);
		joinUsPage = new JoinUsPage(driver);
		responsiveMenu = new ResponsiveMenu(driver);
		
		utility.testName("Sign up");
		utility.logInfoStatus("JPK Started");
		
		introPage.goToJoinUsPage();
		joinUsPage.signUp();
		responsiveMenu.confirmRegistrationIsSuccessful();
		
		utility.logPassStatus("Successfully created an account");
	}
}
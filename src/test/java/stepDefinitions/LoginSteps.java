package stepDefinitions;

import java.time.Duration;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssert;
import managers.TestProperties;
import pages.LoginPage;
import utilities.WaitUtility;

public class LoginSteps {

	
	private SoftAssert softAssert;
	public LoginSteps(SoftAssert softAssert) {
		this.softAssert = softAssert;
	}
	
	
	@When("user login application")
	public void user_login_application() {
		
		LoginPage loginPage = new LoginPage();
		
		new WaitUtility().waitForPageToLoad();
		loginPage.enterUsername(TestProperties.getInstance().getUserProperty("username"));
		loginPage.clickNextPage();
		new WaitUtility().waitForPageToLoad();
		loginPage.enterPassword(TestProperties.getInstance().getUserProperty("password"));
		loginPage.submit();
		new WaitUtility().waitForPageToLoad();
		try {
			Thread.sleep(Duration.ofSeconds(10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Then("user should see the dashbboard")
	public void user_should_see_the_dashbboard() {
		new WaitUtility().waitForPageToLoad();
		boolean  isHomepageVisible = new LoginPage().isNextChatButtonDisplayed();
		softAssert.assertTrue(isHomepageVisible, "Home page loaded properly.");
		
	}

	@When("user logout from the application")
	public void userLogOutFromApplication() {
		new WaitUtility().waitForPageToLoad();
		new LoginPage().clickOnlogoutButton();
	}

}

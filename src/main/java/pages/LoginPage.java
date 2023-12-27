package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import managers.AllDriverManager;
import managers.TestProperties;
import utilities.WaitUtility;

public class LoginPage extends BasePage{

	@FindBy(xpath="//input[@type=\"email\"]")
	WebElement emailInput;

	@FindBy(xpath="//input[@type=\"password\"]")
	WebElement password;

	@FindBy(xpath="//input[@type=\"submit\"]")
	WebElement submit;

	@FindBy(xpath="//div[@class=\"new-chat-area\"]/button")
	WebElement newChatButton;

	@FindBy(xpath="//div[@class=\"menu-bottom\"]//*[normalize-space()='Logout']")
	WebElement logoutButton;
	
	
	public LoginPage(){
		super();
	}

	public void launchApplication() {
		getDriver();
		driver.get(TestProperties.getInstance().getEnvProperty("baseURL"));
	}

	public void tearDown() {
		new AllDriverManager().closeDriver();
	}

	public void enterUsername(String usernameValue) {
		new WaitUtility().waitForElementToBeVisible(emailInput);
		emailInput.sendKeys(usernameValue);
	}

	public void enterPassword(String passwordValue) {
		new WaitUtility().waitForElementToBeVisible(password);
		password.sendKeys(passwordValue);
	}

	public void clickNextPage() {
		new WaitUtility().waitForElementToBeVisible(submit);
		submit.click();
	}

	public void submit() {
		new WaitUtility().waitForElementToBeVisible(submit);
		submit.click();
	}

	public boolean isNextChatButtonDisplayed() {
		new WaitUtility().waitForElementToBeVisible(newChatButton);
		return newChatButton.isDisplayed();
	}
	
	public void clickOnlogoutButton() {
		new WaitUtility().waitForElementToBeVisible(logoutButton);
		logoutButton.click();
	}
}

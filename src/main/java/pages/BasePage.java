package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import managers.AllDriverManager;

public class BasePage extends AllDriverManager{


	WebDriver driver;

	public BasePage() {
		this.driver = getDriver();
		PageFactory.initElements(driver, this);
	}

}

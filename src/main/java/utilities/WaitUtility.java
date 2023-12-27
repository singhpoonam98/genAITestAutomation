package utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import managers.AllDriverManager;


public class WaitUtility extends AllDriverManager{

	private final Logger logger = (Logger) LogManager.getLogger(WaitUtility.class);
	private final WebDriver driver = getDriver();
	protected static WebDriverWait wait;
	protected static FluentWait<WebDriver> fluentWait;


	public static final long DEFAULT_WAIT_TIMEOUT_IN_SEC = 10;

	/**
	 * Wait for page to loaded completely
	 */
	public void waitForPageToLoad() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT_IN_SEC));
		wait.until((ExpectedCondition<Boolean>) wd -> (((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("DONE")
				|| ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"))
				&& !((JavascriptExecutor) wd).executeScript("return document.readyState").equals("loading")
				&& !((JavascriptExecutor) wd).executeScript("return document.readyState").equals("interactive"));
	}

	public void waitForElementToBeVisible(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT_IN_SEC));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}

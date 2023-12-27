package managers;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllDriverManager {



	public static WebDriver driver;
	//private static String browserName="edge";
	private static String browserName="firefox";
	private static String executionEnvironment="local";
	public AllDriverManager(){
//		browserName = "Read browser name from configuration";
//		executionEnvironment = "read execution env from configuration";

	}
	public  WebDriver getDriver(){
		if (driver == null) driver = createDriver();
		return driver;

	}
	public void closeDriver(){
		driver.close();
		driver.quit();

	}
	private WebDriver createDriver(){
		switch (executionEnvironment){
		case "local":
			driver = createLocalDriver();
			break;
		case "remote":
			driver = createRemoteDriver();
			break;

		}
		return driver;

	}
	private WebDriver createLocalDriver() {
		switch (browserName) {
		case "chrome":

			ChromeOptions chromeOptions = new ChromeOptions();

			chromeOptions.setAcceptInsecureCerts(true);

			chromeOptions.addArguments("--headless","--window-size=1644,868");

			//	WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			break;
		case "firefox":

			//	WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			break;
		case "ie":

			//	WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		case "edge":

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.get("edge://settings/clearBrowserData");
			driver.findElement(By.id("clear-now")).sendKeys(Keys.ENTER);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);

		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(2));
		return driver;

	}
	private WebDriver createRemoteDriver(){
		return driver;

	}




}

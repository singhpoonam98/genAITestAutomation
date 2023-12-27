package runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import pages.LoginPage;

@CucumberOptions(
		features = "src/test/resources/features/Login.feature",
		glue = "stepDefinitions",
		plugin = {
				"pretty",
				"html:target/cucumber-reports/cucumber-pretty.html",
				"json:target/cucumber-reports/CucumberTestReport.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:target/test-output-thread/"
		}
)
public class testrunner extends AbstractTestNGCucumberTests{

	@BeforeSuite
	public void beforeSuit() {
		new LoginPage().launchApplication();
	}
	
	@AfterSuite
	public void tearDown() {
		new LoginPage().tearDown();
	}

}

package managers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.service.ExtentService;

public class Report {

	private static final String screenshotCSS = "<div style='padding-inline-start:8%%;'><img style='height: 117px; width: 194px;' data-featherlight='%1$s' src='%1$s'></div>";
	private static final String logMessageCSS = "<div style='padding-inline:5%%;'>%s<span style='padding-inline:1%%;font-style:italic;'>%s</span></div>";

	/*********************** WB Reporting Methods ***********************/

	public static void flushReport() {
		ExtentService.getInstance().flush();
	}
	public static void debug(Logger logger, String message) {
		logger.debug(message);
		String screenshotPath = ExtentService.getScreenshotReportRelatvePath() + takeScreenshot().getName();
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, String.format(screenshotCSS, screenshotPath));

	}
	public static void info(Logger logger, String message) {
		logger.info(message);
		String infoCircleCSS = "<i class='fa fa-info-circle' style='color:grey;' aria-hidden='true'></i>";
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, String.format(logMessageCSS, infoCircleCSS, message));
		String screenshotPath = ExtentService.getScreenshotReportRelatvePath() + takeScreenshot().getName();
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, String.format(screenshotCSS, screenshotPath));
	}
	public static void pass(Logger logger, String message) {
		logger.fatal(message);
		String checkCircleCSS = "<i class='fa fa-check-circle' style='color:green;' aria-hidden='true'></i>";
		ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, String.format(logMessageCSS, checkCircleCSS, message));
		String screenshotPath = ExtentService.getScreenshotReportRelatvePath() + takeScreenshot().getName();
		ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, String.format(screenshotCSS, screenshotPath));
	}
	public static void warn(Logger logger, String message) {
		logger.warn(message);
		String exclamationTriangleCSS = "<i class='fa fa-exclamation-triangle' style='color:orange;' aria-hidden='true'></i>";
		ExtentCucumberAdapter.getCurrentStep().log(Status.WARNING, String.format(logMessageCSS, exclamationTriangleCSS, message));
		String screenshotPath = ExtentService.getScreenshotReportRelatvePath() + takeScreenshot().getName();
		ExtentCucumberAdapter.getCurrentStep().log(Status.WARNING, String.format(screenshotCSS, screenshotPath));
	}

	public static void fail(Logger logger, String message) {
		logger.error(message);
		String timesCircleCSS = "<i class='fa fa-times-circle-o' style='color:red;' aria-hidden='true'></i>";
		ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, String.format(logMessageCSS, timesCircleCSS, message));
		String screenshotPath = ExtentService.getScreenshotReportRelatvePath() + takeScreenshot().getName();
		ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, String.format(screenshotCSS, screenshotPath));
	}
	public static void skip(Logger logger, String message) {
		logger.error(message);
		String timesCircleCSS = "<i class='fa fa-forward' style='color:orange;' aria-hidden='true'></i>";
		ExtentCucumberAdapter.getCurrentStep().log(Status.SKIP, String.format(logMessageCSS, timesCircleCSS, message));
		String screenshotPath = ExtentService.getScreenshotReportRelatvePath() + takeScreenshot().getName();
		ExtentCucumberAdapter.getCurrentStep().log(Status.SKIP, String.format(screenshotCSS, screenshotPath));
	}
	public static File takeScreenshot() {
		File screenshot = ((TakesScreenshot) AllDriverManager.driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = ExtentService.getScreenshotFolderName() + "screenshot_" + java.time.LocalDateTime.now().toString().replace(":", "_").replace(" ", "_") + "_.png";
		File destFile = new File(screenshotPath);
		try {
			FileUtils.copyFile(screenshot, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}
}
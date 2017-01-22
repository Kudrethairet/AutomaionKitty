package com.selenium.customlibrary;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BasePage {

	final static Logger logger = Logger.getLogger(BasePage.class);
	public static WebDriver driver;
	public static UtilityLibrary myLib;

	private static JavaPropertiesManager property;
	private static String browser;
	private static String isAutoSendEmail;
	private static String isDemoMode;

	@BeforeClass
	public void beforeAllTests() throws Exception {
		property = new JavaPropertiesManager("src/test/resources/config.properties");
		browser = property.readProperty("browserType");
		isDemoMode = property.readProperty("isDemoMode");
		isAutoSendEmail = property.readProperty("sendEmail");
		myLib = new UtilityLibrary();
		if (isDemoMode.contains("true")) {
			myLib.isDemoMode = true;
			logger.info("Test is running demo mode ON");
		} else {
			myLib.isDemoMode = false;
			logger.info("Test is running demo mode OFF");
		}

	}

	@AfterClass
	public void afterAllTests() {
		List<String> screenshots = new ArrayList<>();
		EmailManager emailSender = new EmailManager();
		emailSender.attchmentFiles.add("target/logs/Selenium-Report.html");
		emailSender.attchmentFiles.add("target/logs/log4j-selenium.log");

		screenshots = myLib.automaticallyAttachErrorImgToEmail();
		if (screenshots.size() != 0) {
			for (String temp : screenshots) {
				emailSender.attchmentFiles.add(temp);
			}
		}
		if (isAutoSendEmail.contains("true")) {
			emailSender.sendEmail(emailSender.attchmentFiles);
		}

	}

	@BeforeMethod
	public void beforEachTest() {
		logger.info("Starting test ...");
		driver = myLib.startBrowser(browser);
	}

	@AfterMethod
	public void afterEachTest() {
		myLib.customWait(10);
		driver.close();
		driver.quit();
		logger.info("Closing browser");
		logger.info("Ending test ...");

	}
}

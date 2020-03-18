package com.expedia.flightsbooking;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.expedia.pageclasses.SearchPage;

public class TestNG_TestSuite {
	private WebDriver driver;
	private String baseUrl;
	static Logger log = LogManager.getLogger(TestNG_TestSuite.class);

	@BeforeClass
	public void beforeClass() {
//		need to setProperty for Firefox driver, else Jenkins would complain when run Maven in Jenkins
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.26.0-win32\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = "https://www.expedia.com/";

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		PropertyConfigurator.configure("log4j.properties");
		driver.get(baseUrl);
	}

	@Test
	public void fillBasicInfo() throws Exception {
		SearchPage.navigateToFlightsTab(driver);
		SearchPage.fillOriginTextBox(driver, "New York");
		SearchPage.fillDestinationTextBox(driver, "Chicago");
		SearchPage.fillDepartureDateTextBox(driver, "05/25/2020");
		SearchPage.fillReturnDateTextBox(driver, "05/31/2020");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

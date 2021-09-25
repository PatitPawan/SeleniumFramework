package com.guru99.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterMethod;

import com.guru99.pages.LoginPage;

import commonLibs.implementations.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenshotUtils;

public class BaseTests {
	
	CommonDriver commonDriver;
	String url;
	
	LoginPage loginpage;
	
	WebDriver driver;
	
	String currentWorkingDirectory;
	Properties configProperty;
	String configFilename;
	
	String reportFilename;
	ReportUtils reportUtils;
	
	ScreenshotUtils screenshot;
	
	@BeforeSuite
	public void peSetup() throws Exception {
		currentWorkingDirectory = System.getProperty("user.dir");
		configFilename = currentWorkingDirectory + "/config/config.properties";
		reportFilename = currentWorkingDirectory + "/reports/guru99TestReport.html";
		configProperty = ConfigUtils.readProperties(configFilename);
		reportUtils = new ReportUtils(reportFilename);
	}
	
	@BeforeClass
	public void setUp() throws Exception{
		url = configProperty.getProperty("baseUrl");
		String browserType = configProperty.getProperty("browserType");
		commonDriver = new CommonDriver(browserType);
		driver = commonDriver.getDriver();
		loginpage = new LoginPage(driver);
		screenshot = new ScreenshotUtils(driver);
		commonDriver.navigateToURL(url);
	}
	
	@AfterMethod
	public void postTestAction(ITestResult result) throws Exception{
		String testcaseName = result.getName();
		long executionTime = System.currentTimeMillis();
		
		String screenshotFilename = currentWorkingDirectory+"/screenshots/"+testcaseName+executionTime+".JPEG";
		
		if(result.getStatus() == ItestResult.FAILURE) {
			reportUtils.addTestLog(Status.FAIL, "One or more steps failed");
			screenshot.captureAndSaveScreenshot(screenshotFilename);
			reportUtils.attachScreenshotToReport(screenshotFilename);
		}
	}
	
	@AfterClass
	public void tearDown() {
		commonDriver.closeAllBrowser();
	}
	
	@AfterSuite
	public void postTearDown() {
		reportUtils.flushReport();
	}	
}

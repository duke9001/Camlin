package com.camlin.testCases;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.camlin.utilities.ReadConfig;

import freemarker.log.Logger;

public class BaseClass {
	
	//create object from readconfig(com.cigna.utilities) class
	ReadConfig readConfig = new ReadConfig();
	
	//get chrome path from config.properties
	public String chromePath = System.getProperty("user.dir")+ readConfig.getChromePath();
	
	//get chrome driver path from config.properties
	public String chromeDriver = readConfig.getChromeDriver();
	
	//get baseUrl from config.properties
	public String baseUrl = readConfig.getBaseURL();	
	
	/*
	 * WebDriver is a remote control interface that enables introspection and
	 * control of user agents (browsers). The methods in this interface fall into
	 * three categories: Control of the browser itself 
	 * Selection of WebElements
	 * Debugging aids
	 * 
	 * static - one object for every thread which is create from main method
	 */
	public static WebDriver webdriver;
	
	/*
	 * Loggers in Java are objects which trigger log events, They are created and
	 * are called in the code of the application, where they generate Log Events
	 * before passing them to the next component which is an Appender. You can use
	 * multiple loggers in a single class to respond to various events or use
	 * Loggers in a hierarchy. They are normally named using the hierarchical
	 * dot-separated namespace. Also, all the Logger names must be based on the
	 * class or the package name of the logged component.
	 * 
	 * static - one object for every thread which is create from main method
	 */
	public static Logger logger;
	
	
	/*
	 * inside TestNG.xml <parameter name="browser" value="chrome"></parameter> for
	 * understand which browser we need to run the code we use @Parameter annotation
	 * import org.testng.annotations.Parameters;
	 */
	@Parameters("browser")	
	//before @Test test case
	@BeforeClass
	//create browser variable inside setup method
	public void setup(String browser) {
		
		//initializing Log4j class
		//pass project name
		logger = Logger.getLogger("camlin"); 
		//pass Log4j.properties file
		PropertyConfigurator.configure("Log4j.properties");
		
		
		if(browser.equals("chrome")) {
			System.setProperty(chromeDriver, chromePath);
			webdriver = new ChromeDriver();
			
		}	
	}
	
	//after @Test test case
	@AfterClass
	public void driverClose() {
		webdriver.quit();
	}
	
	//this method use for if error occur take a screenshot
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public void waitForVisibility(WebElement element){


		try { 
			new WebDriverWait(webdriver,
					Duration.ofSeconds(10000)).until(ExpectedConditions.visibilityOf(element)); 
		}catch(Exception e) { 
			System.out.println("Element not found"); 
		}

	}
}


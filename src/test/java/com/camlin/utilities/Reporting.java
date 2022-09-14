package com.camlin.utilities;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Listener class use to generate external report
public class Reporting extends TestListenerAdapter {
	
	//The ExtentSparkReporter creates a rich standalone spark file. It allows several configuration options via the config() method.
	public ExtentSparkReporter htmlReporter;
	
	/*
	 * Extent Reports is a customizable HTML report developed by Anshoo Arora which
	 * can be integrated into Selenium WebDriver using JUnit and TestNG frameworks.
	 */
	public ExtentReports extent;
	
	//Defines a node in the report file.
	public ExtentTest logger;
	
	//OnStart method is called when any Test starts	
	//ITestContext is basically used to store and share data across the tests in selenium by using TestNG framework.
	public void onStart(ITestContext testContext)
	{
		//time stamp
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		//file save name and extension
		String repName="Cigna-Test-Report-"+timeStamp+".html";
		
		//specify location of the report
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName);
		try {
			//To load configuration via XML, use LoadXmlConfig()
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//create object from Extent Reports
		extent=new ExtentReports();
		
		//attach xml configuration
		extent.attachReporter(htmlReporter);
		
		//host name
		extent.setSystemInfo("Host name","localhost");
		
		//environment 
		extent.setSystemInfo("Environemnt","QA");
		
		//user name
		extent.setSystemInfo("user","Uditha");
		
		//tile of report
		htmlReporter.config().setDocumentTitle("Cigna"); 
		
		//name of the report
		htmlReporter.config().setReportName("Functional Test Automation Report");
		
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	//onTestSuccess method is called on the success of any Test
	public void onTestSuccess(ITestResult tr)
	{
		//create new entry in the report
		logger=extent.createTest(tr.getName());
		
		//send the passed information to the report with GREEN color highlighted
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); 
	}
	
	//onTestFailure method is called on the failure of any Test
	public void onTestFailure(ITestResult tr)
	{
		//create new entry in the report
		logger=extent.createTest(tr.getName()); 
		
		//send the failed information to the report with RED color highlighted
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); 
		
	}
	
	//onTestSkipped method is called on skipped of any Test
	public void onTestSkipped(ITestResult tr)
	{
		// create new entry in the report
		logger=extent.createTest(tr.getName()); 
		
		//send the skipped information to the report with ORANGE color highlighted
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	//onFinish method is called after all Tests are executed
	public void onFinish(ITestContext testContext)
	{
		//flushing the report. Please note that the report will not be generated if the flush() method is not used
		extent.flush();
	}
	
	//Reference - https://www.guru99.com/listeners-selenium-webdriver.html

}

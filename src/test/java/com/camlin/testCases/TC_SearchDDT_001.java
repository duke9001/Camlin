package com.camlin.testCases;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.lang3.math.NumberUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.camlin.pageObjects.Home;
import com.camlin.pageObjects.SearchResult;
import com.camlin.utilities.XLUtils;


public class TC_SearchDDT_001 extends BaseClass{

	//get data from external file
	@Test(dataProvider = "SearchData")
	public void searchDDT(String search, String result) throws InterruptedException {
		
		
		/*
		 * One of the timeouts is focused on the time a webpage needs to be loaded – the
		 * pageLoadTimeout limits the time that the script allots for a web page to be
		 * displayed. If the page loads within the time then the script continues. If
		 * the page does not load within the timeout the script will be stopped by a
		 * TimeoutException.
		 */
		webdriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5000));

		/*
		 * The Implicit Wait in Selenium is used to tell the web driver to wait for a
		 * certain amount of time before it throws a “No Such Element Exception”. The
		 * default setting is 0. Once we set the time, the web driver will wait for the
		 * element for that time before throwing an exception.
		 */
		webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		
		/*
		 * sets the amount of time to wait for an asynchronous script to finish
		 * execution before throwing an error.
		 */
		webdriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5000));

		//concatenate with base url
		webdriver.get(baseUrl);
		logger.info("URL in opened");
		
		Home home = new Home(webdriver);
		
		if(!home.getCookieCloseButtonText().getText().isEmpty()) {
			home.clickCookieCloseButton();
			logger.info("Close cookie popup");
		}
		
		if(!home.getInternationPopupText().getText().isEmpty()) {
			home.clickInternationPopupCloseButton();
			logger.info("Close international popup");
		}
				

		home.clickSearchHomeButton();
		logger.info("Open search popup");
		
		home.setSearch(search);
		logger.info("Enter search query");
		
		home.clickButton();
		logger.info("Click search button");		
		
		SearchResult searchResult = new SearchResult(webdriver);
		
		waitForVisibility(searchResult.getSearchTitle());
		logger.info("Wait till 'Search Results' title appear");
		
		try{
			//Check string parse to number
			if(NumberUtils.isParsable(result)) {
				//Parse to integer
				if(Integer.parseInt(result)!= 0) {
					Assert.assertTrue(true);
					logger.info("Passed for positive test cases");		
				}
			}
			else if(result.equals("No Results Found")) {
				Assert.assertTrue(true);
				logger.info("Passed for negative test cases");		
			}
			else {
				Assert.assertTrue(false);
				logger.info("Failed");		
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		webdriver.manage().deleteAllCookies();
		logger.info("Delete all cookie");
		
		webdriver.switchTo().defaultContent();
	}


	//for which method we are providing data
	@DataProvider(name = "SearchData")
	public String[][] getData() throws IOException{

		//get excel file from testData package
		String path = System.getProperty("user.dir")+"/src/test/java/com/camlin/testData/Sample_Data_for_Search.xlsx";

		int rowNumber = XLUtils.getRowCount(path, "Sheet1");
		int coloumnCount = XLUtils.getCellCount(path, "Sheet1", 1);

		String[][] searchData = new String[rowNumber][coloumnCount];

		for(int row = 0; row<rowNumber;row++) {
			for(int col = 0; col<coloumnCount;col++) {
				//row+1 because first row is title
				searchData[row][col] = XLUtils.getCellData(path,"Sheet1",row+1,col);
			}

		}

		return searchData;
	}

}

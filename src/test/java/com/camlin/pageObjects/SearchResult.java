package com.camlin.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResult {
	
	WebDriver localDriver;
	
	@FindBy(id = "results-count")
	@CacheLookup
	private WebElement resultCount;
	
	@FindBy(xpath = "/html/body/main/section/div/div[1]/div/h1")
	@CacheLookup
	private WebElement searchTitle;
	
	@FindBy(xpath = "/html/body/main/section/div/div[2]/div/p")
	@CacheLookup
	private WebElement noResult;
	
	
	public SearchResult(WebDriver remortDriver) {
		localDriver = remortDriver;
		PageFactory.initElements(remortDriver, this);
	}

	public WebElement getResultCount() {
		return resultCount;
	}
	
	public WebElement getSearchTitle() {
		return searchTitle;
	}
}

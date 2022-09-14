package com.camlin.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	
	WebDriver localDriver;
	
	@FindBy(id="query")
	@CacheLookup
	private WebElement search;
	
	@FindBy(xpath = "//*[@id=\"search-overlay\"]/div/div/div/label/form/button")
	@CacheLookup
	private WebElement searchIcon;
	
	@FindBy(id="onetrust-accept-btn-handler")
	@CacheLookup
	private WebElement cookieCloseButton;
	
	@FindBy(xpath = "//*[@id=\"country-notice\"]/div/div/div/div/p")
	@CacheLookup
	private WebElement internationPopupCloseText;
	
	@FindBy(xpath = "//*[@id=\"country-notice\"]/div/div/div/div/button")
	@CacheLookup
	private WebElement internationPopupCloseButton;
	
	@FindBy(xpath = "//*[@id=\"main-header\"]/div/div/div/ul/li[9]/a")
	@CacheLookup
	private WebElement searchHomeButton;
	
	public Home(WebDriver remortDriver) {
		localDriver = remortDriver;
		PageFactory.initElements(remortDriver, this);
	}
	
	public void setSearch(String searchQuery) {
		search.sendKeys(searchQuery);
	}
	
	public void clickButton() {
		searchIcon.click();
	}
	
	public void clickCookieCloseButton() {
		cookieCloseButton.click();
	}
	
	public WebElement getCookieCloseButtonText() {
		return cookieCloseButton;
	}
	
	public void clickInternationPopupCloseButton() {
		internationPopupCloseButton.click();
	}
	
	public WebElement getInternationPopupText() {
		return internationPopupCloseText;
	}
	
	public void clickSearchHomeButton() {
		searchHomeButton.click();
	}
	
	

}

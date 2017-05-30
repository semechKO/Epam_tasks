package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Sample page
 */
public class HomePage extends Page {

/*
  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  public WebElement header;
  private String MAIN_URL="ebay.com";
*/
  @FindBy(xpath = ".//*[@id='gh-ac']")
  private SearchArrow searchArrow;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }

  public SearchPage searchFor(String request) {
    this.searchArrow.searchFor(request);
    return new SearchPage(driver);
  }
  /*public void Open(WebDriver webDriver){
    webDriver.get(MAIN_URL);
  }*/
}

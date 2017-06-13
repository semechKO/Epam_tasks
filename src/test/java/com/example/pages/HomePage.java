package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Sample page
 */
public class HomePage extends Page {


  @FindBy(xpath = ".//*[@id='gh-ac']")
  private SearchArrow searchArrow;

  public HomePage(WebDriver webDriver, String URL) {
    super(webDriver);
    driver.get(URL);
  }

  public SearchPage searchFor(String request) {
    this.searchArrow.searchFor(request);
    return new SearchPage(driver);
  }
}

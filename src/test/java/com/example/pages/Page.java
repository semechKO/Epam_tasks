package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

  protected WebDriver driver;

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */

  public Page(final WebDriver driver) {
    PageFactory.initElements(new HtmlElementDecorator(driver), this);
    this.driver = driver;
  }
  /*public Page(WebDriver driver) {
    this.driver = driver;
  }*/

  public String getTitle() {
    return driver.getTitle();
  }

}

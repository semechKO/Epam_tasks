package com.example;

import com.example.pages.SearchArrow;
import com.example.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.pages.HomePage;

import java.util.List;

/**
 * Class CountryUSACheck checks USA area filter
 */
public class FreeDeliveryCheck extends TestNgTestBase {

  private HomePage homepage;
  private SearchPage searchPage;

  @BeforeMethod
  public void initPageObjects() {
    //homepage = PageFactory.initElements(driver, HomePage.class);
    //searchPage=PageFactory.initElements(driver, SearchPage.class);
  }
  /**
   * Method tests if snippets shown on search page after checkbox "Free Delivery" was selected have text "Бесплатная международная доставка".
   */
  @Test
  public void testFreeDeliveryCheck() {
    driver.get("http://www.ebay.com/");
    HomePage homePage = new HomePage(driver);
    SearchPage page = homePage.searchFor("Сфинкс");
    page.ebayFilterSelectFreeInternationalDelivery();
    List items = page.getSearchResults().getId();
    WebElement result;
    String actual_result="";
    String expected_result="Бесплатная международная доставка";
    for(int i=0;i<items.size();i++){
      result=driver.findElement(By.xpath(".//*[@id=\'"+items.get(i).toString()+"\']/ul[1]/li[3]/span/span/span"));
      actual_result=result.getText();
      Assert.assertEquals(actual_result,expected_result);
    }
  }

  @AfterClass
  public void tearDown() {
    this.driver.quit();
  }
}


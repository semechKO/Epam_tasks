package com.example;

import com.example.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.example.pages.HomePage;

import java.util.List;

/**
 * Class CountryUSACheck checks USA area filter
 */
public class FreeDeliveryCheck extends TestNgTestBase {

  HomePage homePage;
  SearchPage page;

  @Test
  public void testFreeDeliveryCheck() {
    homePage = new HomePage(driver,baseUrl);
    page = homePage.searchFor("Сфинкс");
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


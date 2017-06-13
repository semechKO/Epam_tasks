package com.example;


import com.example.pages.HomePage;
import com.example.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Class PurchaseReturnCheck checks purchase return filter
 */
public class PurchaseReturnCheck extends TestNgTestBase{

    HomePage homePage;
    SearchPage page;

    @Test
    public void testPurchaseReturnCheck() {
        homePage = new HomePage(driver,baseUrl);
        page = homePage.searchFor("Сфинкс");
        page.purchaseReturnCheckboxSelect();
        List items = page.getSearchResults().getLinks();
        WebElement result;
        String actual_result="";
        String expected_result="Продавец не предлагает возврат товаров.";
        for(int i=0;i<items.size();i++){
            driver.get(items.get(i).toString());
            result=driver.findElement(By.xpath(".//*[@id=\'vi-ret-accrd-txt\']"));
            actual_result=result.getText();
            //System.out.println(actual_result);
            Assert.assertTrue(!actual_result.equalsIgnoreCase(expected_result));
        }
    }
    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }
}

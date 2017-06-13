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
 * Class UsedItemCheck checks used item filter
 */
public class UsedItemCheck extends TestNgTestBase {

    HomePage homePage;
    SearchPage page;

    @Test
    public void testUsedItemCheck() {
        homePage = new HomePage(driver,baseUrl);
        page = homePage.searchFor("Сфинкс");
        page.usedConditionSelect();
        List items = page.getSearchResults().getLinks();
        WebElement result;
        String actual_result="";
        for(int i=0;i<items.size();i++){
            driver.get(items.get(i).toString());
            result=driver.findElement(By.xpath(".//*[@id=\'vi-itm-cond\']"));
            actual_result=result.getText();
            Assert.assertTrue((!actual_result.equalsIgnoreCase("Новый")|!actual_result.equalsIgnoreCase("--")));
        }
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }
}


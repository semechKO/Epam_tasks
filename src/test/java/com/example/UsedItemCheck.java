package com.example;

import com.example.TestNgTestBase;
import com.example.pages.HomePage;
import com.example.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Class UsedItemCheck checks used item filter
 */
public class UsedItemCheck extends TestNgTestBase {

    private HomePage homepage;
    private SearchPage searchPage;

    @BeforeMethod
    public void initPageObjects() {
        //homepage = PageFactory.initElements(driver, HomePage.class);
        //searchPage=PageFactory.initElements(driver, SearchPage.class);
    }

    /**
     * Method tests if snippets shown on search page after selecting used item
     * have right text parameter.
     */
    @Test
    public void testUsedItemCheck() {
        driver.get("http://www.ebay.com/");
        HomePage homePage = new HomePage(driver);
        SearchPage page = homePage.searchFor("Сфинкс");
        page.usedConditionSelect();
        List items = page.getSearchResults().getLinks();
        WebElement result;
        String actual_result="";
        //String expected_result="Б/у";
        for(int i=0;i<items.size();i++){
            driver.get(items.get(i).toString());
            result=driver.findElement(By.xpath(".//*[@id=\'vi-itm-cond\']"));
            actual_result=result.getText();
            //System.out.println(actual_result);
            Assert.assertTrue((actual_result.equalsIgnoreCase("Б/у")|actual_result.equalsIgnoreCase("Бывший в употреблении")|
            actual_result.equalsIgnoreCase("Как новый")|actual_result.equalsIgnoreCase("Хорошее состояние"))&&
                    (!actual_result.equalsIgnoreCase("Новый")|!actual_result.equalsIgnoreCase("--")));
        }
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }
}


package com.example;

import com.example.pages.HomePage;
import com.example.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Class BestPriceFilterCheck checks best price filter
 */
public class BestPriceFilterCheck extends TestNgTestBase {
    private HomePage homepage;
    private SearchPage searchPage;

    @BeforeMethod
    public void initPageObjects() {
        //homepage = PageFactory.initElements(driver, HomePage.class);
        //searchPage=PageFactory.initElements(driver, SearchPage.class);
    }

    /**
     * Method tests if snippets shown on search page after checkbox "Best price" was selected have text "Лучшая цена".
     */
    @Test
    public void testBestPriceFilter() {
        driver.get("http://www.ebay.com/");
        HomePage homePage = new HomePage(driver);
        SearchPage page = homePage.searchFor("Сфинкс");
        page.bestPriceFilterSelect();
        List items = page.getSearchResults().getId();
        WebElement result;
        String actual_result="";
        String expected_result="Лучшая цена";
        for(int i=0;i<items.size();i++){
            result=driver.findElement(By.xpath(".//*[@id=\'"+items.get(i).toString()+"\']/ul[1]"));
            actual_result=result.getText();
            //System.out.println(actual_result=result.getText());
            Assert.assertTrue(actual_result.contains(expected_result));
        }
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }
}


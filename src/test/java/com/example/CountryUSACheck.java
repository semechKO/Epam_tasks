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
 * Class CountryUSACheck checks USA area filter
 */
public class CountryUSACheck extends TestNgTestBase {

    @Test
    public void testCountryUSACheck() {
        HomePage homePage = new HomePage(driver,baseUrl);
        SearchPage page = homePage.searchFor("Сфинкс");
        page.countryUSAFilterSelect();
        List items = page.getSearchResults().getId();
        WebElement result;
        String actual_result="";
        String expected_result="Страна: США";
        for(int i=0;i<items.size();i++){
            result=driver.findElement(By.xpath(".//*[@id=\'"+items.get(i).toString()+"\']/ul[2]"));
            actual_result=result.getText();
            //System.out.println(actual_result);
            Assert.assertTrue(actual_result.contains(expected_result));
        }
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }
}

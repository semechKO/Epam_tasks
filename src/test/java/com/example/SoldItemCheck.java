package com.example;

import com.example.pages.HomePage;
import com.example.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class SoldItemCheck checks sold item filter
 */
public class SoldItemCheck extends TestNgTestBase{

    public static DateFormat item_sell_date_format = new SimpleDateFormat("MM.dd HH:mm");

    @Test
    public void testFreeDeliveryCheck() throws ParseException {
        HomePage homePage = new HomePage(driver,baseUrl);
        SearchPage page = homePage.searchFor("Сфинкс");
        page.soldItemFilterCheckboxSelect();
        List items = page.getSearchResults().getId();
        WebElement result;
        String actual_result="";
        for(int i=0;i<items.size();i++){
            result=driver.findElement(By.xpath(".//*[@id=\'"+items.get(i).toString()+"\']/ul[2]/li[1]/span/span"));
            actual_result=result.getText();
            Date item_date =item_sell_date_format.parse(actual_result);
            Date date_now=new Date();
            Assert.assertTrue(item_date.compareTo(date_now)<0);
        }
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }
}
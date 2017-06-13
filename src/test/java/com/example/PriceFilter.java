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
 * Class PriceFilter checks price area filter
 */
public class PriceFilter extends TestNgTestBase {

    HomePage homePage;
    SearchPage page;

    @Test
    public void testPriceFilterCheck() {
        final double high_price=15000;
        final double low_price=10000;
        homePage = new HomePage(driver,baseUrl);
        page = homePage.searchFor("Сфинкс");
        page.checkPriceFilter(low_price,high_price);
        List items = page.getSearchResults().getId();
        WebElement result;
        String actual_result="";
        for(int i=0;i<items.size();i++){
            result=driver.findElement(By.xpath(".//*[@id=\'"+items.get(i).toString()+"\']/ul[1]/li[1]/span"));
            actual_result=result.getText().replaceAll("[\\sа-я]","").replaceAll(",",".");
            actual_result=actual_result.substring(0,actual_result.length()-1);
            if((actual_result.length())>=(String.valueOf(low_price).length()*2)){
                actual_result=actual_result.substring(0,String.valueOf(low_price).length());
            }
            System.out.println(actual_result);
            System.out.println(low_price+" "+high_price);
            Assert.assertTrue(Double.parseDouble(actual_result)>low_price&&
            Double.parseDouble(actual_result)<high_price);
        }
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }

}

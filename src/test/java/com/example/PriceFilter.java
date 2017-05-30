package com.example;

import com.example.TestNgTestBase;
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
 * Class PriceFilter checks price area filter
 */
public class PriceFilter extends TestNgTestBase {

    private HomePage homepage;
    private SearchPage searchPage;

    @BeforeMethod
    public void initPageObjects() {
        //homepage = PageFactory.initElements(driver, HomePage.class);
        //searchPage=PageFactory.initElements(driver, SearchPage.class);
    }

    /**
     * Method tests if snippets shown on search page after selecting price range
     * have price in right range.
     */
    @Test
    public void testPriceFilterCheck() {
        driver.get("http://www.ebay.com/");
        HomePage homePage = new HomePage(driver);
        SearchPage page = homePage.searchFor("Сфинкс");
        page.checkPriceFilter("10000","15000");
        List items = page.getSearchResults().getId();
        WebElement result;
        String actual_result="";
        //String expected_result="Б/у";
        for(int i=0;i<items.size();i++){
            result=driver.findElement(By.xpath(".//*[@id=\'"+items.get(i).toString()+"\']/ul[1]/li[1]/span"));
            actual_result=result.getText().replaceAll("[\\sа-я]","").replaceAll(",",".");
            actual_result=actual_result.substring(0,actual_result.length()-1);
            if((actual_result.length())>=(String.valueOf(page.getLow_price()).length()*2)){
                actual_result=actual_result.substring(0,String.valueOf(page.getLow_price()).length());
            }
            //System.out.println(actual_result);
            //System.out.println(page.getLow_price()+" "+page.getHigh_price());
            Assert.assertTrue(Double.parseDouble(actual_result)>page.getLow_price()&&
            Double.parseDouble(actual_result)<page.getHigh_price());
        }
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }

}

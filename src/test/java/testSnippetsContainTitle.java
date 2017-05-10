/**
 * Created by Dmitriy on 10.05.2017.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class testSnippetsContainTitle {
    private static final String URL = "http://ebay.com";
    private static final String XPATH_SEARCH = "//*[@id=\"gh-ac\"]";
    private static final String XPATH_BTN_SEARCH = "//*[@id=\"gh-btn\"]";
    private static final String XPATH_ELEMENTS = ".//*[@id=\'ListViewInner\']/li/h3/a";
    private WebDriver driver;

    @BeforeTest
    public void preparation(){
        //System.setProperty("webdriver.gecko.driver", "C:\\selenium_driver\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\selenium_driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @DataProvider
    public Object[][] getItems(){
        return new Object[][]{
                {"iphune","iphone"},
               // {"дастаевский", "достоевский"},
               // {"monhunt","manhunt"},
                //{"volitile times","volatile times"}
        };
    }
    @Test(dataProvider = "getItems")
    public void testExample(String WRONG_WORD, String RIGHT_WORD) {
        driver.get(URL);
        WebElement element = driver.findElement(By.xpath(XPATH_SEARCH));
        element.clear();
        element.sendKeys(WRONG_WORD);
        WebElement btn = driver.findElement(By.xpath(XPATH_BTN_SEARCH));
        btn.click();
        List<WebElement> items = driver.findElements(By.xpath(XPATH_ELEMENTS));
        Collection<String> links = new ArrayList<String>();
        for(WebElement item:items){
            links.add(item.getAttribute("href"));
        }
        for(String link:links){
            driver.get(link);
            element =driver.findElement(By.xpath(".//*[@id=\'itemTitle\']"));
            Assert.assertTrue(element.getText().toLowerCase().contains(RIGHT_WORD));
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}

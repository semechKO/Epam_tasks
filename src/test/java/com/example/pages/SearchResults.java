package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created Class SearchResults  set and returns Arrays with items id's and links
 */
public class SearchResults extends Page {
    //private WebDriver driver;
    private List<WebElement>itemsSearchPageResultforId;
    private List<WebElement>getItemsSearchPageResultforLinks;
    private ArrayList links=new ArrayList();
    private ArrayList id=new ArrayList();
    final static String PAGE_RESULTS_IDS_LOCATOR=".//*[@class='sresult lvresult clearfix li']";
    final static String PAGE_RESULTS_LINKS_LOCATOR=".//*[@id='ListViewInner']/li/h3/a";

    public SearchResults(WebDriver driver) {
        super(driver);
    }

    /**
     * Method sets Lists with web elements for items Id's and links
     */
    public void setSearchResults(WebDriver driver) {
        this.itemsSearchPageResultforId=driver.findElements(By.xpath(PAGE_RESULTS_IDS_LOCATOR));
        this.getItemsSearchPageResultforLinks = driver.findElements(By.xpath(PAGE_RESULTS_LINKS_LOCATOR));
        this.setItems();
    }

    /*
    public List<WebElement>getItemsSearchPageResult(WebDriver driver){
        return this.itemsSearchPageResult;
    }*/

    /**
     * Method returns ArrayList with links
     * @return ArrayList
     */
    public ArrayList getLinks(){
        return this.links;
    }

    /**
     * Method returns ArrayList with id's
     * @return ArrayList
     */
    public ArrayList getId(){
        return this.id;
    }

    /**
     * Method adds web elements attributes to ArrayLists with id's and links
     */
    public void setItems(){
        Iterator itemsIteratorForId = this.itemsSearchPageResultforId.iterator();
        Iterator itemsIteratorForLinks =this.getItemsSearchPageResultforLinks.iterator();
        WebElement item;
        while(itemsIteratorForId.hasNext()) {
            item=(WebElement)itemsIteratorForId.next();
            //this.links.add(item.getAttribute("href"));
            this.id.add(item.getAttribute("id"));
        }
        while (itemsIteratorForLinks.hasNext()){
            item=(WebElement)itemsIteratorForLinks.next();
            this.links.add(item.getAttribute("href"));
        }
    }
}

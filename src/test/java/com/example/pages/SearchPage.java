package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.*;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;

/**
 * Created by Dmitriy on 24.05.2017.
 */
public class SearchPage {

    private WebDriver driver;

    @FindBy(xpath = ".//*[@id=\'see-all-form\']/div/input")
    private Button accept_button;
    @FindBy(xpath = ".//*[@id=\'LH_BO_1\']")
    private CheckBox best_price_checkbox;
    @FindBy(xpath = ".//*[@id=\'e1-43\']/div[1]/span[2]/button")
    private Button show_all_button;
    @FindBy(xpath = ".//*[@id=\'e1-48\']")
    private CheckBox sold_item_checkbox;
    @FindBy(xpath = ".//*[@id=\'e1-44\']")
    private CheckBox purchase_return_checkbox;
    @FindBy(xpath = ".//*[@id=\'ListViewInner\']/li/h3/a")
    private SearchResults search_results;
    @FindBy(xpath = "//*[@id=\'gh-ac\']")
    private SearchArrow searchArrow;
    @FindBy(xpath =".//*[@id=\'e1-32\']" )
    private CheckBox country_USA;
    @FindBy(xpath = "//*[@id=\'e1-41\']")
    private CheckBox free_international_delivery_checkbox;
    @FindBy(xpath = ".//*[@id=\'e1-17\']")
    private CheckBox used_condition_checkbox;
    @FindBy(xpath = ".//*[@id=\'e1-7\']")
    private TextInput low_price_input;
    @FindBy(xpath = ".//*[@id=\'e1-8\']")
    private TextInput high_price_input;
    @FindBy(xpath = ".//*[@id=\'e1-20\']")
    private Button search_by_price_button;
    private double high_price;
    private double low_price;

    /**
     * Method initialize search page
     * @param driver
     */
    public SearchPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
        this.driver = driver;
    }

    /**
     * Method returns high price
     * @return double
     */
    public double getHigh_price(){
        return this.high_price;
    }

    /**
     * Method returns low price
     * @return double
     */
    public double getLow_price(){
        return this.low_price;
    }

    public SearchPage searchFor(String request) {
        this.searchArrow.searchFor(request);
        return this;
    }

    /**
     * Selects free international deliver checkbox
     * @return search page
     */
    public SearchPage ebayFilterSelectFreeInternationalDelivery(){
        free_international_delivery_checkbox.select();
        return this;
    }

    /**
     * Selects USA Area checkbox
     * @return search page
     */
    public SearchPage countryUSAFilterSelect(){
        country_USA.select();
        return this;
    }

    /**
     * Sets high and low price and returns search page
     * @param low_price
     * @param high_price
     * @return search page
     */
    public SearchPage checkPriceFilter(String low_price,String high_price){
        low_price_input.sendKeys(low_price);
        high_price_input.sendKeys(high_price);
        this.high_price=Double.parseDouble(high_price);
        this.low_price=Double.parseDouble(low_price);
        search_by_price_button.click();
        return this;
    }

    /**
     * Selects purchase return checkbox
     * @return search page
     */
    public SearchPage purchaseReturnCheckboxSelect(){
        purchase_return_checkbox.select();
        return this;
    }

    /**
     * Selects sold item checkbox
     * @return search page
     */
    public SearchPage soldItemFilterCheckboxSelect(){
        sold_item_checkbox.select();
        return this;
    }

    /**
     * Selects best price checkbox
     * @return search page
     */
    public SearchPage bestPriceFilterSelect(){
        show_all_button.click();
        best_price_checkbox.select();
        accept_button.click();
        return this;
    }

    /**
     * Selects used item checkbox
     * @return search page
     */
    public SearchPage usedConditionSelect(){
        used_condition_checkbox.select();
        return this;
    }

    /**
     * Get search results
     * @return SeachResults
     */
    public SearchResults getSearchResults() {
        SearchResults searchResults=new SearchResults();
        searchResults.setSearchResults(driver);
        return this.search_results=searchResults;
        //return this.searchResults.getItemsSearchPageResult(driver);
    }
}
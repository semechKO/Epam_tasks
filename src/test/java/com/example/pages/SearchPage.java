package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.*;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;

/**
 * Created by Dmitriy on 24.05.2017.
 */
public class SearchPage extends Page {

   // private WebDriver driver;

    private static final String ACCEPT_BUTTON_LOCATOR = ".//*[@id='see-all-form']/div/input";
    private static final String BEST_PRICE_CHECKBOX_LOCATOR = ".//*[@id='LH_BO_1']";
    private static final String SHOW_ALL_BUTTON_LOCATOR = ".//*[@id='e1-46']/div[1]/span[2]/button";
    private static final String SOLD_ITEM_CHECKBOX_LOCATOR = ".//*[@id='e1-48']";
    private static final String PURCHASE_RERURN_LOCATOR = ".//*[@id='e1-44']";
    private static final String SEARCH_RESULTS_LOCATOR = ".//*[@id='ListViewInner']/li/h3/a";
    private static final String SEARCH_ARROW_LOCATOR = "//*[@id='gh-ac']";
    private static final String USA_CHECKBOX_LOCATOR = ".//*[@id='e1-32']";
    private static final String FREE_INTERNATIONAL_DELIVERY_CHECKBOX_LOCATOR = "//*[@id='e1-41']";
    private static final String USED_CONDITION_CHECKBOX_LOCATOR = ".//*[@id='e1-17']";
    private static final String LOW_PRICE_INPUT_LOCATOR = ".//*[@id='e1-7']";
    private static final String HIGH_PRICE_INPUT_LOCATOR = ".//*[@id='e1-8']";
    private static final String PRICE_SEARCH_BUTTON_LOCATOR = ".//*[@id='e1-20']";

    @FindBy(xpath = ACCEPT_BUTTON_LOCATOR)
    private Button accept_button;
    @FindBy(xpath = BEST_PRICE_CHECKBOX_LOCATOR)
    private CheckBox best_price_checkbox;
    @FindBy(xpath = SHOW_ALL_BUTTON_LOCATOR)
    private Button show_all_button;
    @FindBy(xpath = SOLD_ITEM_CHECKBOX_LOCATOR)
    private CheckBox sold_item_checkbox;
    @FindBy(xpath = PURCHASE_RERURN_LOCATOR)
    private CheckBox purchase_return_checkbox;
    @FindBy(xpath = SEARCH_RESULTS_LOCATOR)
    private SearchResults search_results;
    @FindBy(xpath = SEARCH_ARROW_LOCATOR)
    private SearchArrow searchArrow;
    @FindBy(xpath =USA_CHECKBOX_LOCATOR )
    private CheckBox country_USA;
    @FindBy(xpath = FREE_INTERNATIONAL_DELIVERY_CHECKBOX_LOCATOR)
    private CheckBox free_international_delivery_checkbox;
    @FindBy(xpath = USED_CONDITION_CHECKBOX_LOCATOR)
    private CheckBox used_condition_checkbox;
    @FindBy(xpath = LOW_PRICE_INPUT_LOCATOR)
    private TextInput low_price_input;
    @FindBy(xpath = HIGH_PRICE_INPUT_LOCATOR)
    private TextInput high_price_input;
    @FindBy(xpath = PRICE_SEARCH_BUTTON_LOCATOR)
    private Button search_by_price_button;


    /**
     * Method initialize search page
     * @param driver
     */
    public SearchPage(WebDriver driver) {
        //PageFactory.initElements(new HtmlElementDecorator(driver), this);
        //this.driver = driver;
        super(driver);
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
    public SearchPage checkPriceFilter(Double low_price,Double high_price){
        low_price_input.sendKeys(low_price.toString());
        high_price_input.sendKeys(high_price.toString());
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
        SearchResults searchResults=new SearchResults(driver);
        searchResults.setSearchResults(driver);
        return this.search_results=searchResults;
    }
}
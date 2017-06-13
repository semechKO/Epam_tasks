package com.example.pages;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import org.openqa.selenium.support.*;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by Dmitriy on 24.05.2017.
 */
public class SearchArrow extends HtmlElement{

    private static final String REQUEST_INPUT_LOCATOR = "//*[@id='gh-ac']";
    private static final String SEARCH_BUTTON_LOCATOR = "//*[@id='gh-btn']";

        @FindBy(xpath = REQUEST_INPUT_LOCATOR)
        public HtmlElement requestInput;

        @FindBy(xpath = SEARCH_BUTTON_LOCATOR)
        public HtmlElement searchButton;

    /**
     * Writes request in searchArrow and clicks on search button
     * @param request
     */
        public void searchFor(String request) {
            requestInput.clear();
            requestInput.sendKeys(request);
            searchButton.click();
        }

}

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

        @FindBy(xpath = "//*[@id=\'gh-ac\']")
        public WebElement requestInput;

        @FindBy(xpath = "//*[@id=\'gh-btn\']")
        public WebElement searchButton;

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

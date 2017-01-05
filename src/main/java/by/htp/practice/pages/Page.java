package by.htp.practice.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ark on 02.01.2017.
 */
abstract class Page {
    WebDriver driver;

    protected Page(WebDriver driver) {
        super();
        this.driver = driver;
    }

    protected abstract void open();

}

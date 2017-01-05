package by.htp.practice.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ark on 02.01.2017.
 */
public class GooglePage extends Page {
    protected static Logger logger = LogManager.getLogger(GooglePage.class);
    private static final String URL = "https://google.com/";

    private String searchWord;
    private By titleSelector = By.cssSelector("html>head>title");

    @FindBy(id = "lst-ib")
    private WebElement searchField;
    @FindBy(name = "btnK")
    private WebElement submitButton;

    public GooglePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.navigate().to(URL);
        logger.info("Search page open");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void submitSearch(String searchWord) {
        this.searchWord = searchWord;
        searchField.sendKeys(searchWord);
        submitButton.submit();
        logger.info("search request submitted");
        try {
            (new WebDriverWait(driver, 15))
                    .until(ExpectedConditions.titleContains(searchWord));
        } catch (Exception e) { }
        logger.info("Result page open");
    }

    public String getResultPageTitle() {
        return driver.getTitle();
    }


}

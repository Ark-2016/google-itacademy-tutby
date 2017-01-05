package by.htp.practice.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Ark on 05.01.2017.
 */
public class TutByPage extends Page {
    public static final Logger logger = LogManager.getLogger(TutByPage.class);

    private final String URL = "https://www.tut.by/";
    private final By dayNewsSelector = By.cssSelector("[itemprop='articleSection']");

    @FindBy(className = "header_link")
    private WebElement mainNews;
    @FindBy(css = ".header._title")
    private WebElement mainNewsTitle;
    @FindBy(id = "article_body")
    private WebElement articleBody;

    protected TutByPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.navigate().to(URL);
        logger.info("page open");
    }

    public String getMainNewsTitle() {
        return mainNewsTitle.getText();
    }

    public void openMainNews() {
        mainNews.click();
        waitForWebElementPresence(dayNewsSelector, 60);
    }

    public int countNumberOfParagraphs() {
        List<WebElement> paragraphs = articleBody.findElements(By.tagName("p"));
        return paragraphs.size();
    }

    public String getDayNewsLabel() {
        return driver.findElement(dayNewsSelector).getText();
    }

    private void waitForWebElementPresence(By selector, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch(WebDriverException e) {}
    }

}

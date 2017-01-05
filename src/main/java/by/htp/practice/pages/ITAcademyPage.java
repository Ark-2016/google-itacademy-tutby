package by.htp.practice.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static by.htp.practice.utilities.JavaScriptHandler.*;

/**
 * Created by Ark on 03.01.2017.
 */
public class ITAcademyPage extends Page {

    private static final String URL = "http://www.it-academy.by/";
    protected static final Logger logger = LogManager.getLogger(ITAcademyPage.class);

    @FindBy(css = ".leaf.menu-depth-2.menu-item-3132>a")
    private static WebElement marketingItem;

    @FindBy(css = ".expanded.menu-depth-1.menu-item-633>ul>li>a")
    private static List<WebElement> whoToLearnMenu;
    @FindBy(css = ".last.expanded.menu-depth-2.menu-item-657>ul>li>a")
    private static List<WebElement> careerList;

    public ITAcademyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.navigate().to(URL);
        logger.info("Home page open");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<String> getWhoToLearnMenuItems() {
        return getMenuItems(whoToLearnMenu);
    }

    public List<String> getCareerSubmenuItems() {
        return getMenuItems(careerList);
    }

    public void clickMarketingItem() {
        clickWebElementByJavascript(driver, marketingItem);
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page-title")));
        } catch(WebDriverException e) {}
        logger.info("Marketing page open");
    }

    private List<String> getMenuItems(List<WebElement> webElementList) {
        List<String> list = new ArrayList<>();
        for(WebElement webElement : webElementList) {
            String text = getWebElementTextByJavascript(driver, webElement);
            list.add(text);
        }
        return list;
    }

}

/*    @FindBy(css = ".first.expanded.menu-depth-1.menu-item-553>ul>li>a")
    private static List<WebElement> coursesMenu;*/
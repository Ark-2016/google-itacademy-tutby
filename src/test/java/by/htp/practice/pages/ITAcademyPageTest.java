package by.htp.practice.pages;

import by.htp.practice.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static by.htp.practice.pages.ITAcademyPage.logger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Ark on 04.01.2017.
 * Протестировать функциональность домашней страницы
 * сайта http://www.it-academy.by/:
 * раздел меню "На кого учиться" (прочитать и вывести на консоль
 * все пункты выпадающего меню и подменю пунктов при наличии);
 * раздел "Каталог курсов" (найти пункт "Маркетинг" и перейти по
 * этой ссылке).
 */
public class ITAcademyPageTest {
    private static final String TITLE1_STARTS_WITH = "Курсы программирования - IT курсы в Минске";
    private static final String TITLE2_STARTS_WITH = "Маркетинг - Каталог курсов";

    private WebDriver driver;
    private ITAcademyPage page;

    @BeforeClass(description = "start browser")
    public void setUp() throws Exception {
        driver = DriverSingleton.getDriver();
        page = new ITAcademyPage(driver);
    }

    @AfterClass(description = "stop browser")
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test(description = "verify Home page title")
    public void testHomePage() throws Exception {
        page.open();
        assertTrue(page.getTitle().startsWith(TITLE1_STARTS_WITH), "Wrong Home page title");
        logger.info("Home page title got");
    }

    @Test(description = "verify Marketing page title")
    public void testMarketingPage() throws Exception {
        page.open();
        page.clickMarketingItem();
        assertTrue(page.getTitle().startsWith(TITLE2_STARTS_WITH), "Wrong Marketing page title");
        logger.info("Marketing page title got");
    }

    @Test(description = "count number of WhoToLearn menu items")
    public void testWhoToLearnMenu() throws Exception {
        page.open();
        List<String> list = page.getWhoToLearnMenuItems();
        logging(list);
        assertEquals(list.size(), 2, "Wrong number of WhoToLearn menu items.");
        logger.info("WhoToLearnMenu items got");
    }

    @Test(description = "count number of Career submenu items")
    public void testCareerSubmenu() throws Exception {
        page.open();
        List<String> list = page.getCareerSubmenuItems();
        logging(list);
        assertEquals(list.size(), 4, "Wrong number of Career submenu items.");
        logger.info("CareerSubmenu items got");
    }

    private void logging(List<String> list) {
        for(String item : list) {
            logger.info(item);
        }
    }

}
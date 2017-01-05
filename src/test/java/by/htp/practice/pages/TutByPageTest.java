package by.htp.practice.pages;

import by.htp.practice.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.htp.practice.pages.TutByPage.logger;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertNotNull;

/**
 * Created by Ark on 05.01.2017.
 * Прочитать с сайта tut.by название главной новости.
 * Клинуть на главную новость и подсчитать количество
 * параграфов в тексте новости.
 */
public class TutByPageTest {
    private final String homePageTitle = "Белорусский портал TUT.BY";
    private final String  dayNewsLabel = "НОВОСТЬ ДНЯ";
    private WebDriver driver;
    private TutByPage page;

    @BeforeClass
    public void setUp() throws Exception {
        driver = DriverSingleton.getDriver();
        page = new TutByPage(driver);
    }

    @AfterClass
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test(description = "verify the home page title and read the main news title")
    public void testHomePage() throws Exception {
        page.open();
        assertTrue(driver.getTitle().contentEquals(homePageTitle), "Wrong home page!");
        logger.info("Home page title verified");
        String mainNewsTitle = page.getMainNewsTitle();
        assertNotNull(mainNewsTitle, "Main news title failed");
        logger.info("Main news title: " + mainNewsTitle);
    }

    @Test(description = "open the main news article and count the number of paragraphs")
    public void testMainNewsPage() throws Exception {
        page.open();
        page.openMainNews();
        assertTrue(page.getDayNewsLabel().toUpperCase().contentEquals(dayNewsLabel), "Wrong main news page");
        logger.info("Main news page open");
        int count = page.countNumberOfParagraphs();
        assertTrue(count > 0, "Failed to count");
        logger.info("Number of paragraphs: " + count);
    }

}
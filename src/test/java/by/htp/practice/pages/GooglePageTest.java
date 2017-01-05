package by.htp.practice.pages;

import by.htp.practice.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.htp.practice.pages.GooglePage.logger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


/**
 * Created by Ark on 02.01.2017.
 * Протестировать поисковую страницу поисковика GooglePage:
 * открыть поисковик, в строку поиска ввести слово Java,
 * убедиться, что название страницы с результатом поиска
 * содержит слово Java
 */
public class GooglePageTest {
    private WebDriver driver;
    private GooglePage page;
    private static final String PAGE_TITLE = "Google";
    private static  final String searchWord = "Java";

    @BeforeClass(description = "start browser")
    public void setUp() throws Exception {
        driver = DriverSingleton.getDriver();
        page = new GooglePage(driver);
    }
    @AfterClass(description = "stop browser")
    public void tearDown() throws Exception {
        DriverSingleton.closeDriver();
    }

    @Test(description = "verify Search page title")
    public void testSearchPage() throws Exception {
        page.open();
        assertEquals(page.getTitle(),PAGE_TITLE,"Wrong Search page title!");
        logger.info("Search page title got");
    }
    @Test(description = "submit search and verify Result page title")
    public void testResultPage() throws Exception {
        page.open();
        page.submitSearch(searchWord);
        assertTrue(page.getResultPageTitle().startsWith(searchWord), "Search failed...");
        logger.info("Result page title got");
    }
}
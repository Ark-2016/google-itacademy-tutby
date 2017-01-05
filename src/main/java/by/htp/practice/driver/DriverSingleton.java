package by.htp.practice.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;


/**
 * Created by Ark on 02.01.2017.
 */
public class DriverSingleton {
    private static WebDriver driver;
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_PATH = "src\\main\\resources\\geckodriver.exe";
    private static Logger logger = LogManager.getLogger(DriverSingleton.class);

    public static WebDriver getDriver() {
        if(null == driver) {
            System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_PATH);
            DesiredCapabilities desiredCapabilities =
                    new DesiredCapabilities("firefox","50.1.0", Platform.WIN8_1);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.startup.page", "about:blank");
            profile.setPreference("app.update.auto", false);
            profile.setPreference("app.update.enabled", false);
            profile.setPreference("extensions.update.autoUpdateDefault", false);
            profile.setPreference("extensions.update.enable", false);
            profile.setPreference("dom.ipc.plugins.enabled", false);
            profile.setPreference("marionette.logging", "fatal");
            profile.setPreference("browser.cache.disk.capacity", 256);
            desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);

            driver = new FirefoxDriver(desiredCapabilities);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
//            driver.manage().window().maximize();
            logger.info("Browser started");
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
        logger.info("Browser stopped");
    }

}

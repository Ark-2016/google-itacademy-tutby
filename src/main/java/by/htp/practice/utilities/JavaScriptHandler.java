package by.htp.practice.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Ark on 04.01.2017.
 */
public class JavaScriptHandler {

    public static String getWebElementTextByJavascript(final WebDriver driver, final WebElement webElement) {
        String scriptString = "var element = arguments[0]; return element.textContent;";
        return (String) ((JavascriptExecutor)driver).executeScript(scriptString, webElement);
    }

    public static void clickWebElementByJavascript(final WebDriver driver, final WebElement webElement) {
        String scriptString = "var element = arguments[0]; return element.click();";
        ((JavascriptExecutor)driver).executeScript(scriptString, webElement);
    }

}

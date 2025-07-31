package rahulshettyacademy.AbstractComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SeleniumUtils {

    public static void safeClick(WebDriver driver, WebElement element) {
        // 1. Create an explicit wait that waits up to 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // 2. Wait until the element is clickable (visible and enabled)
            wait.until(ExpectedConditions.elementToBeClickable(element));
            
            // 3. Scroll the element into view (make sure it's visible on screen)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            
            // 4. Try to click the element normally
            element.click();
        } catch (ElementClickInterceptedException e) {
            // 5. If the click is blocked/intercepted (common in headless or tricky UI),
            //    then fallback to clicking the element using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
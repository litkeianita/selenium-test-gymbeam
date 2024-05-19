import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitForVisibilityAndReturn(By by){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this.driver.findElement(by);
    }
}
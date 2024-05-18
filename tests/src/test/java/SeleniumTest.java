import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private String username = "tomsmith";
    private String password = "SuperSecretPassword!";

    private final By usernameLocator = By.id("username");
    private final By passwordLocator = By.id("password");
    private final By logInButtonLocator = By.xpath("//button[@type='submit']");
    private final By logOutButtonLocator = By.xpath("//a[@href='/logout']");
    private final By successMessageLocator = By.className("success");
    private final By errorMessageLocator = By.className("error");

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testLoginCorrectly() {
        this.driver.get("http://the-internet.herokuapp.com/login");

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
        WebElement usernameField = this.driver.findElement(usernameLocator);
        usernameField.sendKeys(username);

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
        WebElement passwordField = this.driver.findElement(passwordLocator);
        passwordField.sendKeys(password);

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(logInButtonLocator));
        WebElement logInButton = this.driver.findElement(logInButtonLocator);
        logInButton.click();

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
        WebElement successMessage = this.driver.findElement(successMessageLocator);
        assertTrue(successMessage.getText().contains("You logged into a secure area!"));



        this.wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButtonLocator));
        WebElement logOutButton = this.driver.findElement(logOutButtonLocator);
        logOutButton.click();



        this.wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
        usernameField = this.driver.findElement(usernameLocator);
        usernameField.sendKeys("incorrect user name");

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
        passwordField = this.driver.findElement(passwordLocator);
        passwordField.sendKeys("incorrect password");

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(logInButtonLocator));
        logInButton = this.driver.findElement(logInButtonLocator);
        logInButton.click();

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        WebElement errorMessage = this.driver.findElement(errorMessageLocator);
        assertTrue(errorMessage.getText().contains("Your username is invalid!"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
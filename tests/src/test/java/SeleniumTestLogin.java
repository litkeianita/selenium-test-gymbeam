import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTestLogin {

    private WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testLoginCorrectly() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.clickDeclineCookies();

        loginPage.setEmail("lobiyij220@neixos.com");
        loginPage.setPassword("Selenium.gymbeam");
        loginPage.clickLoginButton();
        
        assertTrue(loginPage.getUserName().contains("selenium"));
    }
   
    @Test
    public void testLoginIncorrectly() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.clickDeclineCookies();

        loginPage.setEmail("wrong.email@something.hu");
        loginPage.setPassword("******");
        loginPage.clickLoginButton();
        
        assertFalse(loginPage.getLoginErrorMessage().isEmpty());
    }

    @Test
    public void testLoginThenLogout() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.clickDeclineCookies();

        loginPage.setEmail("lobiyij220@neixos.com");
        loginPage.setPassword("Selenium.gymbeam");
        loginPage.clickLoginButton();
        
        assertTrue(loginPage.getUserName().contains("selenium"));

        loginPage.clickLogOut();

        assertTrue(loginPage.getLogoutSuccessMessage().contains("Kijelentkezett"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
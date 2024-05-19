import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTestGymBeam {
    private WebDriver driver;
    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        /*Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.cookies", 1);
        prefs.put("profile.cookie_controls_mode", 0);
        options.setExperimentalOption("prefs", prefs);*/
        
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testLoginCorrectly() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);

        mainPage.clickDeclineCookies();

        mainPage.clickLoginPage();

        mainPage.setEmail("lobiyij220@neixos.com");
        mainPage.setPassword("Selenium.gymbeam");

        mainPage.clickLoginButton();
        
        mainPage.getUserName();
        assertTrue(mainPage.getUserName().contains("selenium"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
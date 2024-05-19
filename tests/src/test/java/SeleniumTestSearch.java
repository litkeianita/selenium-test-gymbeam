import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTestSearch {
    private WebDriver driver;
    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testSearch() {
        MainPage mainPage = new MainPage(this.driver);

        mainPage.clickDeclineCookies();

        mainPage.searchForItem("italok");
        mainPage.clickOnItalok();

        System.out.println(this.driver.getTitle());
    }

   @Test
    public void testSearchWithLogin() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.clickDeclineCookies();

        loginPage.setEmail("lobiyij220@neixos.com");
        loginPage.setPassword("Selenium.gymbeam");
        loginPage.clickLoginButton();
        
        assertTrue(loginPage.getUserName().contains("selenium"));

        loginPage.searchForItem("italok");
        loginPage.clickOnItalok();
        System.out.println(this.driver.getTitle());
    }

    @Test
    public void testSearchHistory() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);

        String prevSite = this.driver.getTitle();
        System.out.println("prev: " + prevSite);

        mainPage.clickDeclineCookies();

        mainPage.searchForItem("italok");
        mainPage.clickLoginPage();

        String newSite = this.driver.getTitle();
        System.out.println("new: " + newSite);
        
        this.driver.navigate().back();

        String backedSite = this.driver.getTitle();
        System.out.println("back: " + backedSite);

        assertEquals(prevSite, backedSite);
        assertNotEquals(prevSite, newSite);
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
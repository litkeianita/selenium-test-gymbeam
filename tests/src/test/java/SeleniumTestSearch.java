import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

     /*@Test
    public void testSearch() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);

        mainPage.clickDeclineCookies();

        mainPage.searchForItem("italok");
        mainPage.clickOnItalok();
        System.out.println(this.driver.getTitle());
    }

   @Test
    public void testLoginThenSearch() throws InterruptedException {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.clickDeclineCookies();

        loginPage.setEmail("lobiyij220@neixos.com");
        loginPage.setPassword("Selenium.gymbeam");

        loginPage.clickLoginButton();
        
        loginPage.getUserName();
        assertTrue(loginPage.getUserName().contains("selenium"));

        loginPage.searchForItem("italok");
        loginPage.clickOnItalok();
        System.out.println(this.driver.getTitle());
    }
    */

    @Test
    public void testSearchHistory() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);

        String previousSite = this.driver.getTitle();
        System.out.println(this.driver.getTitle());
        mainPage.clickDeclineCookies();

        mainPage.searchForItem("italok");
        mainPage.clickOnItalok();
        System.out.println(this.driver.getTitle());
        
        this.driver.navigate().back();

        String backedSite = this.driver.getTitle();
        System.out.println(this.driver.getTitle());
        assertEquals(previousSite, backedSite);
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
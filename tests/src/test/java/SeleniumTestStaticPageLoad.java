import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTestStaticPageLoad {
    private WebDriver driver;
    private List<String> urls = Arrays.asList(
        "https://gymbeam.hu/italok",
             "https://gymbeam.hu/sporttaplalek",
             "https://gymbeam.hu/customer/account/login/"
    );

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }


    /*@Test
    public void testStaticPages() {
        for (String url : urls) {
            driver.get(url);
            performCommonTests(url);
        }
    }

    private void performCommonTests(String url) {
        MainPage page = new MainPage(driver, url);

        assertTrue(page.driver.getTitle() != null);
    }*/


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
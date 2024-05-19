import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.net.MalformedURLException;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTestStaticPageLoad {
    private WebDriver driver;
    private List<String> urls = Arrays.asList(
            "https://gymbeam.hu",
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


    @Test
    public void testStaticPages() {
        for (String url : urls) {
            this.driver.get(url);
            MainPage page = new MainPage(this.driver, url);
            page.clickMainPageLogo();
        }
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends BasePage {

    private final By userIconLocator = By.xpath("//a[@href='/customer/account/login']");
    private final By mainPageIconLocator = By.xpath("//a[@title='GymBeam s.r.o.']");
    private final By logOutLocator = By.xpath("//a[@href='https://gymbeam.hu/customer/account/logout/']");

    private final By allowAllCookiesButtonLocator = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
    private final By declineAllCookiesButtonLocator = By.id("CybotCookiebotDialogBodyButtonDecline");

    private final By userNameLocator = By.xpath("//span[@class='logged-in']");
    
    private final By titleLocator = By.className("base");

    private final By successfulLogoutMessageLocator = By.xpath("//span[@data-ui-id='page-title-wrapper']");

    private final By searchBarLocator = By.id("search");
    private final By italokButtonLocator = By.xpath("//a[@href='https://gymbeam.hu/italok']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://gymbeam.hu");
    }

    public MainPage(WebDriver driver, String url) {
        super(driver);
        this.driver.get(url);
    }

    public void clickLoginPage(){
        WebElement loginField = waitForVisibilityAndReturn(userIconLocator);
        loginField.click();
    }

    public void clickAcceptCookies(){
        WebElement cookiesButton = waitForVisibilityAndReturn(allowAllCookiesButtonLocator);
        cookiesButton.click();
    }

    public void clickDeclineCookies(){
        WebElement cookiesButton = waitForVisibilityAndReturn(declineAllCookiesButtonLocator);
        cookiesButton.click();
    }

    public void clickMainPageLogo(){
        WebElement mainPageIcon = waitForVisibilityAndReturn(mainPageIconLocator);
        mainPageIcon.click();
    }

    public String getUserName(){
        WebElement name;
        String userName;
        do {
            name = waitForVisibilityAndReturn(userNameLocator);
            userName = name.getText();
            System.out.println("user name: " + userName);
        } while (userName.isEmpty());
        return name.getText();
    }

    public String getTitle(){
        WebElement title = waitForVisibilityAndReturn(titleLocator);
        return title.getText();
    }

    public void clickLogout(){
        WebElement loginField = waitForVisibilityAndReturn(userIconLocator);
        
        Actions actions = new Actions(this.driver);

        actions.moveToElement(loginField).perform();

        WebElement logoutButton = waitForVisibilityAndReturn(logOutLocator);
        
        actions.moveToElement(logoutButton);
        //actions.click().build().perform();
        logoutButton.click();
    }

    public String getLogoutSuccessMessage(){
        WebElement message = waitForVisibilityAndReturn(successfulLogoutMessageLocator);
        System.out.println("message: " + message.getText());
        return message.getText();
    }

    public void searchForItem(String item){
        WebElement searchField = waitForVisibilityAndReturn(searchBarLocator);
        searchField.sendKeys(item);
    }

    public void clickOnItalok(){
        WebElement item = waitForVisibilityAndReturn(italokButtonLocator);
        item.click();
    }
}
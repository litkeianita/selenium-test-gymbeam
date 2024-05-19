import org.openqa.selenium.*;

public class MainPage extends BasePage {
    //private String username = "lobiyij220@neixos.com";
    //private String password = "Selenium.gymbeam";

    private final By userIconLocator = By.xpath("//a[@href='/customer/account/login']");
    private final By mainPageIconLocator = By.xpath("//a[@title='GymBeam s.r.o.']");

    private final By emailInputFieldLocator = By.id("email");
    private final By passwordInputFieldLocator = By.id("pass");
    private final By loginButtonLocator = By.xpath("//button[@type='submit' and @class='action login primary' and @name='send' and @id='send2']");

    private final By allowAllCookiesButtonLocator = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
    private final By declineAllCookiesButtonLocator = By.id("CybotCookiebotDialogBodyButtonDecline");

    private final By nameLocator = By.xpath("//span[@class='logged-in']");
    
    private final By titleLocator = By.className("base");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://gymbeam.hu");

        /*Set<Cookie> cookiesList =  driver.manage().getCookies();
        for(Cookie getcookies :cookiesList) {
            System.out.println(getcookies );
        }*/        
    }

    public void clickLoginPage(){
        WebElement loginField = waitForVisibilityAndReturn(userIconLocator);
        loginField.click();
    }

    public void setEmail(String email){
        WebElement usernameField = waitForVisibilityAndReturn(emailInputFieldLocator);
        usernameField.sendKeys(email);
    }
    
    public void setPassword(String password){
        WebElement passwordField = waitForVisibilityAndReturn(passwordInputFieldLocator);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton(){
        WebElement loginButton = waitForVisibilityAndReturn(loginButtonLocator);
        loginButton.click();
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
        WebElement name = waitForVisibilityAndReturn(nameLocator);
        System.out.println("user name: " + name.getText());
        return name.getText();
    }

    public String getTitle(){
        WebElement title = waitForVisibilityAndReturn(titleLocator);
        return title.getText();
    }
}
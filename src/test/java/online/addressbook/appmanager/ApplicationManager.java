package online.addressbook.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ApplicationManager {
    private final String browser;
    private WebDriver driver;
    @Getter
    private NavigationHelper navigationHelper;
    @Getter
    private GroupHelper groupHelper;
    @Getter
    private SessionHelper sessionHelper;
    @Getter
    private ContactHelper contactHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        switch (browser.toLowerCase()) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "safari" -> driver = new SafariDriver();
            default -> throw new IllegalArgumentException("Wrong browser");
        }
        driver.manage().timeouts().implicitlyWait(Duration.of(0, ChronoUnit.SECONDS));
        driver.get("http://addressbook2077.online/addressbook/");
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        contactHelper = new ContactHelper(driver);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        driver.quit();
    }

    public void initBrowser() {
        switch (browser.toLowerCase()) {
            case "chrome" -> WebDriverManager.chromedriver().setup();
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "safari" -> WebDriverManager.safaridriver().setup();
            default -> throw new IllegalArgumentException("Wrong browser");
        }
    }
}

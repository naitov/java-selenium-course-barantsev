package online.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ApplicationManager {
    private WebDriver driver;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;

    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        driver.get("https://addressbook2077.000webhostapp.com/");
        driver.manage().window().maximize();
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        driver.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}

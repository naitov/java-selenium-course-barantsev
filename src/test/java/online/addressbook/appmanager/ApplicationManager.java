package online.addressbook.appmanager;

import lombok.Getter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ApplicationManager {
    private WebDriver driver;
    @Getter
    private NavigationHelper navigationHelper;
    @Getter
    private GroupHelper groupHelper;
    @Getter
    private SessionHelper sessionHelper;
    @Getter
    private ContactHelper contactHelper;

    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        driver.get("https://addressbook2077.000webhostapp.com/");
        driver.manage().window().setSize(new Dimension(1366, 768));
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        contactHelper = new ContactHelper(driver);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        driver.quit();
    }
}

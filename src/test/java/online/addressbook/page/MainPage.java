package online.addressbook.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static online.addressbook.test.AddressBookTest.MAIN_PAGE_URL;

public class MainPage extends Page{
    private String groupPageUrl = MAIN_PAGE_URL + "/group.php";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public GroupPage openGroupPage() {
        driver.get(groupPageUrl);
        return new GroupPage(driver);
    }

    public String getUsernameFromLogoutField() {
        WebElement logoutUsername = new WebDriverWait(driver, Duration.of(3, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='logout']")));
        String result = logoutUsername.getText();
        return result.substring(1, result.length() - 7);
    }
}

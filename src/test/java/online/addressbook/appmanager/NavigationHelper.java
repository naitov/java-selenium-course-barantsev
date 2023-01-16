package online.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver driver;

    public NavigationHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoGroupPage() {
        driver.findElement(By.xpath("//*[@id='nav']/ul/li[3]/a")).click();
    }
}

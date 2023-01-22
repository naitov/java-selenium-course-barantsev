package online.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void groupPage() {
        if (isElementPresent(By.name("new")) && driver.getCurrentUrl().contains("/group.php")) {
            return;
        }
        click(By.xpath("//*[@id='nav']/ul/li[3]/a"));
    }

    public void contactPage() {
        if (isElementPresent(By.name("quickadd"))) {
            return;
        }
        click(By.xpath("//*[@id='nav']/ul/li[2]/a"));
    }

    public void mainPage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.xpath("//*[@id='nav']/ul/li[1]/a"));
    }
}

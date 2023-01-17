package online.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoGroupPage() {

        click(By.xpath("//*[@id='nav']/ul/li[3]/a"));
    }

    public void gotoContactPage() {
        click(By.xpath("//*[@id='nav']/ul/li[2]/a"));
    }

    public void goToMainPage() {
        click(By.xpath("//*[@id='nav']/ul/li[1]/a"));
    }
}

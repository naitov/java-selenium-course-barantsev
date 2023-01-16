package online.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type("user", username);
        type("pass", password);
        click(By.cssSelector("input:nth-child(7)"));
    }
}

package online.addressbook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(xpath = "//input[@name='user']")
    private WebElement userField;

    @FindBy(xpath = "//input[@name='pass']")
    private WebElement passField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage getLoginPage(String url) {
        driver.get(url);
        return this;
    }

    public MainPage login(String name, String pass) {
        userField.sendKeys(name);
        passField.sendKeys(pass);
        loginBtn.click();
        return new MainPage(driver);
    }
}

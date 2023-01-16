package online.addressbook.appmanager;

import online.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitCreation() {
        click(By.cssSelector("input:nth-child(87)"));
    }

    public void fillContactForm(ContactData contactData) {
        type("address", contactData.address());
        click(By.cssSelector("#content > form > input[type=\"submit\"]:nth-child(1)"));
        type("middlename", contactData.middleName());
        type("lastname", contactData.lastName());
        type("home", contactData.homePhone());
    }

    public void selectFirstContact() {
        click(By.xpath("//*[@name='selected[]']"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
    }

    public void closeAlertWindow() {
        driver.switchTo().alert().accept();
    }
}

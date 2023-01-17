package online.addressbook.appmanager;

import online.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitCreation() {
        click(By.cssSelector("input:nth-child(87)"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        if (creation) {
            click(By.cssSelector("#content > form > input[type=\"submit\"]:nth-child(1)"));
        }
        type(By.name("firstname"), contactData.firstName());
        type(By.name("lastname"), contactData.lastName());
        if (creation) {
            selectElementByVisibleText(By.name("new_group"), contactData.group());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

    public void initContactModification() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a"));
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData data) {
        fillContactForm(data, true);
        submitCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}

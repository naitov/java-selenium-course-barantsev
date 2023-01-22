package online.addressbook.appmanager;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@Log
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
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        if (creation) {
            selectElementByVisibleText(By.name("new_group"), contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
    }

    public void closeAlertWindow() {
        driver.switchTo().alert().accept();
    }

    public void initContactModification(int index) {
        click(By.xpath(String.format("//*[@id='maintable']/tbody/tr[%s]/td[8]/a", index + 2)));
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

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = new WebDriverWait(driver, getTimeout(Timeouts.FIVE_SEC))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//*[@id='maintable']/tbody/*/td[3]")));
        for (WebElement e : elements) {
            String surnameName = e.getText();
            String surname = surnameName.substring(0, surnameName.indexOf(" "));
            String name = surnameName.substring(surnameName.indexOf(" ") + 1);
            contacts.add(new ContactData(name, surname, "test33"));
        }
        return contacts;
    }
}

package online.addressbook.appmanager;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public int amount() {
        return new WebDriverWait(driver, getTimeout(Timeouts.FIVE_SEC))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("selected[]"))).size();
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

    public void create(ContactData contactData) {
        fillContactForm(contactData, true);
        submitCreation();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
        closeAlertWindow();
    }

    public void modify(ContactData contact, int index) {
        initContactModification(index);
        fillContactForm(contact, false);
        submitModification();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = new WebDriverWait(driver, getTimeout(Timeouts.FIVE_SEC))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//*[@id='maintable']/tbody/*/td[3]")));
        for (WebElement e : elements) {
            String surnameName = e.getText();
            String surname = surnameName.substring(0, surnameName.indexOf(" "));
            String name = surnameName.substring(surnameName.indexOf(" ") + 1);
            contacts.add(new ContactData()
                    .withFirstName(name)
                    .withLastName(surname)
                    .withGroup("test33"));
        }
        return contacts;
    }
}

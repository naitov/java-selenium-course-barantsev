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
import java.util.Arrays;
import java.util.List;

@Log
public class ContactHelper extends HelperBase {
    private Contacts contactCache = null;

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitCreation() {
        click(By.cssSelector("input:nth-child(87)"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        if (creation) {
            click(By.cssSelector("#content > form > input[type='submit']:nth-child(1)"));
        }
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        if (creation) {
            selectElementByVisibleText(By.name("new_group"), contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        if (contactData.getHomePhone() != null) {
            type(By.name("home"), contactData.getHomePhone());
        }
        if (contactData.getMobilePhone() != null) {
            type(By.name("mobile"), contactData.getMobilePhone());
        }
        if (contactData.getWorkPhone() != null) {
            type(By.name("work"), contactData.getWorkPhone());
        }
        if (contactData.getEmail() != null) {
            type(By.name("email"), contactData.getEmail());
        }
        if (contactData.getSecondEmail() != null) {
            type(By.name("email2"), contactData.getSecondEmail());
        }
        if (contactData.getThirdEmail() != null) {
            type(By.name("email3"), contactData.getThirdEmail());
        }
        if (contactData.getAddress() != null) {
            type(By.name("address"), contactData.getAddress());
        }
    }

    public int amount() {
        return new WebDriverWait(driver, getTimeout(Timeouts.TEN_SEC))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//*[@name='selected[]']"))).size();
    }

    private void selectContactById(int id) {
        driver.findElement(By.xpath("//input[@id='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlertWindow() {
        driver.switchTo().alert().accept();
    }

    public void initContactModification(int id) {
        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        fillContactForm(contactData, true);
        submitCreation();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        closeAlertWindow();
    }

    public void modify(ContactData contact) {
        int id = contact.getId();
        selectContactById(id);
        initContactModification(id);
        fillContactForm(contact, false);
        submitModification();
        contactCache = null;
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = new WebDriverWait(driver, getTimeout(Timeouts.TEN_SEC))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//tr[@name='entry']")));
        for (WebElement e : elements) {
            int id = Integer.parseInt(e.findElement(By.xpath("./td/input")).getAttribute("id"));
            String surnameName = e.findElement(By.xpath("./td[3]")).getText();
            String surname = surnameName.substring(0, surnameName.indexOf(" "));
            String name = surnameName.substring(surnameName.indexOf(" ") + 1);
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstName(name)
                    .withLastName(surname)
                    .withGroup("test33"));
        }
        return contactCache;
    }

    public ContactData withPhonesFromTable(ContactData contact) {
        int id = contact.getId();
        WebElement row = getWebElementWithClickableWait(By.xpath("//input[@id='" + id + "']"),
                getTimeout(Timeouts.FIVE_SEC));
        WebElement record = row.findElement(By.xpath("./../.."));
        String surnameName = record.findElement(By.xpath("./td[3]")).getText();
        String surname = surnameName.substring(0, surnameName.indexOf(" "));
        String name = surnameName.substring(surnameName.indexOf(" ") + 1);
        String[] phones = record.findElement(By.xpath("./td[6]")).getText().split("\n");
        return new ContactData()
                .withId(id)
                .withFirstName(name)
                .withLastName(surname)
                .withHomePhone(phones[0])
                .withMobilePhone(phones[1])
                .withWorkPhone(phones[2]);
    }

    public ContactData withEmailFromTable(ContactData contact) {
        int id = contact.getId();
        WebElement row = getWebElementWithClickableWait(By.xpath("//input[@id='" + id + "']"),
                getTimeout(Timeouts.FIVE_SEC));
        WebElement record = row.findElement(By.xpath("./../.."));
        String surnameName = record.findElement(By.xpath("./td[3]")).getText();
        String surname = surnameName.substring(0, surnameName.indexOf(" "));
        String name = surnameName.substring(surnameName.indexOf(" ") + 1);
        String email = record.findElement(By.xpath("./td[5]")).getText();
        return new ContactData()
                .withId(id)
                .withFirstName(name)
                .withLastName(surname)
                .withEmail(email);
    }

    public ContactData details(ContactData contact) {
        int id = contact.getId();
        getWebElementWithClickableWait(By.xpath("//a[@href='view.php?id=" + id + "']"),
                getTimeout(Timeouts.FIVE_SEC)).click();
        List<String> content = new ArrayList<>(Arrays.asList(driver.findElement(
                By.xpath("//div[@id='content']")).getText().split("\n")));
        return new ContactData()
                .withId(id)
                .withFirstName(content.get(0).substring(0, content.get(0).indexOf(" ")))
                .withLastName(content.get(0).substring(content.get(0).indexOf(" ") + 1))
                .withAddress(content.get(1))
                .withHomePhone(trimmed(content.get(3)))
                .withMobilePhone(trimmed(content.get(4)))
                .withWorkPhone(trimmed(content.get(5)))
                .withEmail(trimmed(content.get(7)))
                .withSecondEmail(trimmed(content.get(8)))
                .withThirdEmail(trimmed(content.get(9)));
    }

    private String trimmed(String s) {
        return s.replaceAll("\\s[(]www.[a-zA-Z0-9]+[)]", "")
                .replaceAll("[HMW]: ", "");
    }
}

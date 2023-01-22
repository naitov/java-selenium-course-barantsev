package online.addressbook.tests.contacttests;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

@Log
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToMainPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("test1", "test2", "test33"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData("test1_m", "test2_m", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitModification();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        log.info("Contact has been modified");

        before.remove(before.size() - 1);
        before.add(contact);
        before.sort(Comparator.comparing(ContactData::getLastName));
        after.sort(Comparator.comparing(ContactData::getLastName));
        Assert.assertEquals(after, before);
        log.info("Lists of contacts before and after modification except modified contact are equal");
    }
}

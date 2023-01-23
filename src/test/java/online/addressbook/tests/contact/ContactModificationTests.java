package online.addressbook.tests.contact;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.model.Contacts;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

@Log
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().mainPage();
        if (app.contact().all().size() == 0) {
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData()
                    .withFirstName("test1")
                    .withLastName("test2")
                    .withGroup("test33"));
        }
    }

    @Test
    public void testContactModification() {
        ensurePreconditions();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("test1_m")
                .withLastName("test2_m");
        int index = before.size() - 1;
        app.contact().modify(contact, index);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        log.info("Contact has been modified");

        before.remove(index);
        before.add(contact);
        Assert.assertEquals(after, before);
        log.info("Lists of contacts before and after modification except modified contact are equal");
    }


}

package online.addressbook.tests.contact;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
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
        if (app.contact().list().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstName("test1")
                    .withLastName("test2")
                    .withGroup("test33"));
        }
    }

    @Test
    public void testContactModification() {
        ensurePreconditions();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData()
                .withFirstName("test1_m")
                .withLastName("test2_m");
        int index = before.size() - 1;
        app.contact().modify(contact, index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        log.info("Contact has been modified");

        before.remove(index);
        before.add(contact);
        before.sort(Comparator.comparing(ContactData::getLastName));
        after.sort(Comparator.comparing(ContactData::getLastName));
        Assert.assertEquals(after, before);
        log.info("Lists of contacts before and after modification except modified contact are equal");
    }


}

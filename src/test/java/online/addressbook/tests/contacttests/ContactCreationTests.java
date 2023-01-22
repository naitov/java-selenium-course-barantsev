package online.addressbook.tests.contacttests;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

@Log
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contact().list();
        app.goTo().contactPage();
        ContactData contactData = new ContactData("test1", "test2", "test33");
        app.contact().create(contactData);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size() - 1, before.size());
        log.info("Created new contact: " + contactData.getFirstName());

        before.add(contactData);
        before.sort(Comparator.comparing(ContactData::getLastName));
        after.sort(Comparator.comparing(ContactData::getLastName));
        Assert.assertEquals(after, before);
    }


}


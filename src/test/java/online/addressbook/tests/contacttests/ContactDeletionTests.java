package online.addressbook.tests.contacttests;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Log
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().mainPage();
        if (app.contact().list().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData("test1", "test2", "test33"));
        }
    }

    @Test
    public void testContactDeletion() {
        ensurePreconditions();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);
        log.info("Contact has been removed");

        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
        log.info("Two lists before and after deletion has remained unchanged");
    }


}

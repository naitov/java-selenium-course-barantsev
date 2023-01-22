package online.addressbook.tests.contacttests;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

@Log
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        ContactData contactData = new ContactData("test1", "test2", "test33");
        app.getContactHelper().fillContactForm(contactData, true);
        app.getContactHelper().submitCreation();
        log.info("Created new contact: " + contactData.firstName());
    }

}


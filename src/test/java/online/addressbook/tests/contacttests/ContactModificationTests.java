package online.addressbook.tests.contacttests;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

@Log
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToMainPage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("test1", "test1421", null), false);
        app.getContactHelper().submitModification();
        log.info("Modified first contact in list");
    }
}

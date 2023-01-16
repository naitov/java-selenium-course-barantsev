package online.addressbook.tests.contacttests;

import lombok.extern.java.Log;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

@Log
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlertWindow();
        log.info("Deleted first contact in list");
    }
}

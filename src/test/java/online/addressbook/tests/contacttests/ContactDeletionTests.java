package online.addressbook.tests.contacttests;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

@Log
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToMainPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("test1","test2","test33"));
        }
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlertWindow();
        log.info("Deleted first contact in list");
    }
}

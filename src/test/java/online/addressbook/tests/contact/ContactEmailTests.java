package online.addressbook.tests.contact;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.model.Contacts;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Log
public class ContactEmailTests extends TestBase {

    @Test
    public void testContactEmail() {
        app.goTo().contactCreationPage();
        ContactData testEmailContact = new ContactData()
                .withFirstName("test_em_name")
                .withLastName("test_em_surname")
                .withEmail("test@addressbook.online");
        app.contact().create(testEmailContact);
        Contacts contacts = app.contact().all();
        ContactData contactInTable = app.contact().withEmailFromTable(testEmailContact.withId(
                contacts.stream().mapToInt(ContactData::getId).max().getAsInt()));
        assertThat(testEmailContact, equalTo(contactInTable));
        log.info("Emails in contact creation form and in contact table are equal");
    }
}

package online.addressbook.tests.contact;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.model.Contacts;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Log
public class ContactPhonesTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().contactCreationPage();
        ContactData testPhonesContact = new ContactData()
                .withFirstName("test_ph_name")
                .withLastName("test_ph_surname")
                .withHomePhone("+48 223392839")
                .withMobilePhone("+48 (22) 339-28-39")
                .withWorkPhone("222-333-444");
        app.contact().create(testPhonesContact);
        Contacts contacts = app.contact().all();
        ContactData contactInTable = app.contact().withPhonesFromTable(testPhonesContact.withId(
                contacts.stream().mapToInt(ContactData::getId).max().getAsInt()));
        assertThat(testPhonesContact.withoutBannedSymbolsInPhones(), equalTo(contactInTable));
        log.info("Phones in contact creation form and in contact table are equal");
    }
}

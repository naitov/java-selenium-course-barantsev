package online.addressbook.tests.contact;

import online.addressbook.model.ContactData;
import online.addressbook.model.Contacts;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactPhonesTest extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().contactCreationPage();
        ContactData testPhonesContact = new ContactData()
                .withFirstName("test_ph_name")
                .withLastName("test_ph_surname")
                .withHomePhone("+48 223392839")
                .withMobilePhone("(0) 223323323")
                .withWorkPhone("222-333-444");
        app.contact().create(testPhonesContact);
        Contacts contacts = app.contact().all();
        ContactData contactInTable = app.contact().getValuesFromTable(testPhonesContact.withId(
                        contacts.stream().mapToInt(ContactData::getId).max().getAsInt()));
        assertThat(testPhonesContact.withoutBannedSymbolsInPhones(), equalTo(contactInTable));
    }
}

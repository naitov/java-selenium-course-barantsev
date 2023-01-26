package online.addressbook.tests.contact;

import lombok.extern.slf4j.Slf4j;
import online.addressbook.model.ContactData;
import online.addressbook.model.Contacts;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class ContactDetailsTests extends TestBase {

    @Test
    public void testContactDetails() {
        app.goTo().contactCreationPage();
        ContactData testDetailsContact = new ContactData()
                .withFirstName("test_em_name")
                .withLastName("test_em_surname")
                .withAddress("Test, test, 1/2")
                .withEmail("test1")
                .withSecondEmail("test2")
                .withThirdEmail("test3")
                .withHomePhone("111111")
                .withMobilePhone("234-345-567")
                .withWorkPhone("+48 12345678");

        app.contact().create(testDetailsContact);
        Contacts contacts = app.contact().all();
        ContactData details = app.contact().details(testDetailsContact.withId(
                contacts.stream().mapToInt(ContactData::getId).max().getAsInt()));
        assertThat(testDetailsContact, equalTo(details));
        log.info("Emails in contact creation form and in contact table are equal");
    }
}

package online.addressbook.tests.contact;

import lombok.extern.java.Log;
import online.addressbook.model.ContactData;
import online.addressbook.model.Contacts;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Log
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactWithGroupCreation() {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        app.goTo().contactCreationPage();
        ContactData contact = new ContactData()
                .withFirstName("test1")
                .withLastName("test2")
                .withGroup("test33");
        app.contact().create(contact);
        assertThat(app.contact().amount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        log.info("Created one contact with specific group, other contacts are unchanged");
    }

    @Test
    public void testContactWithPhotoCreation() {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        app.goTo().contactCreationPage();
        File photo = new File("src/test/resources/cat.jpg");
        ContactData contact = new ContactData()
                .withFirstName("test1")
                .withLastName("test2")
                .withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().amount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        log.info("Created one contact with photo, other contacts are unchanged");
    }
}


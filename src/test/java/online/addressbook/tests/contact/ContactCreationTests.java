package online.addressbook.tests.contact;

import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import online.addressbook.model.ContactData;
import online.addressbook.model.Contacts;
import online.addressbook.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))) {
            StringBuilder xml = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                xml.append(line);
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.allowTypes(new Class[]{ContactData.class});
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml.toString());
            return contacts.stream().map((contactData) -> new Object[]{contactData}).toList().iterator();
        }
    }

    @Test(dataProvider = "validContactsFromXml")
    public void testContactWithGroupCreation(ContactData provided) {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        app.goTo().contactCreationPage();
        ContactData contact = new ContactData()
                .withFirstName(provided.getFirstName())
                .withLastName(provided.getLastName())
                .withGroup("test33");
        app.contact().create(contact);
        assertThat(app.contact().amount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        log.info("Created one contact with specific group, other contacts are unchanged");
    }

    @Test(dataProvider = "validContactsFromXml")
    public void testContactWithPhotoCreation(ContactData provided) {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        app.goTo().contactCreationPage();
        File photo = new File("src/test/resources/cat.jpg");
        ContactData contact = new ContactData()
                .withFirstName(provided.getFirstName())
                .withLastName(provided.getLastName())
                .withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().amount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt(ContactData::getId).max().getAsInt()))));
        log.info("Created one contact with photo, other contacts are unchanged");
    }
}


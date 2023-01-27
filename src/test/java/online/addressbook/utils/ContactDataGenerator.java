package online.addressbook.utils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import online.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Amount of groups to create, i.e. \"-c 10\"")
    public int count;
    @Parameter(names = "-f", description = "File format, xml or json, i.e. \"-f json\"")
    public String format;
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jc = new JCommander(generator);
        try {
            jc.parse(args);
        } catch (ParameterException e) {
            jc.usage();
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        file = String.format("src/test/resources/contacts.%s", format);
        switch (format) {
            case "xml" -> {
                saveAsXml(contacts, new File(file));
                logSuccess(file);
            }
            case "json" -> {
                saveAsJson(contacts, new File(file));
                logSuccess(file);
            }
            default -> log.warn("Unrecognized format: " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("testName%s", i))
                    .withLastName(String.format("testSurname%s", i))
                    .withMobilePhone(random()));
        }
        return contacts;
    }

    private void logSuccess(String path) {
        log.info(String.format("Generated %s contacts in %s", count, path));
    }

    private String random() {
        Random r = new Random();
        return String.valueOf(r.nextInt(100000000, 999999999));
    }
}

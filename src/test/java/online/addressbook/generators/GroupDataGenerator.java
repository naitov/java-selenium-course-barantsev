package online.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import lombok.extern.java.Log;
import online.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Log
public class GroupDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Path to file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jc = new JCommander(generator);
        try {
            jc.parse(args);
        } catch (ParameterException e) {
            jc.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv")) {
            saveAsCsv(groups, new File(file));
        }
        else if (format.equals("xml")) {
            saveAsXml(groups, new File(file));
        }
        else {
            log.info("Unrecognized format: " + format);
        }
    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test%s", i))
                    .withHeader(String.format("header%s", i))
                    .withFooter(String.format("footer%s", i)));
        }
        return groups;
    }
}

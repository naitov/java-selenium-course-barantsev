package online.addressbook.tests.group;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import online.addressbook.model.GroupData;
import online.addressbook.model.Groups;
import online.addressbook.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"))) {
            StringBuilder xml = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                xml.append(line);
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.allowTypes(new Class[]{GroupData.class});
            xStream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml.toString());
            return groups.stream().map((groupData) -> new Object[]{groupData}).toList().iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json.toString(), new TypeToken<>() {
            });
            return groups.stream().map((groupData) -> new Object[]{groupData}).toList().iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData g) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName(g.getName()).withHeader(g.getHeader()).withFooter(g.getFooter());
        app.group().create(group);
        assertThat(app.group().amount(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
        log.info("Created one group, other groups are unchanged");
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test33'");
        app.group().create(group);
        assertThat(app.group().amount(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
        log.info("Created one group, other groups are unchanged");
    }
}

package online.addressbook.tests.group;

import com.thoughtworks.xstream.XStream;
import lombok.extern.java.Log;
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

@Log
public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.allowTypes(new Class[]{GroupData.class});
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return groups.stream().map((groupData) -> new Object[]{groupData}).toList().iterator();
    }

    @Test(dataProvider = "validGroups")
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

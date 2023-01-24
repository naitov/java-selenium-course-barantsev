package online.addressbook.tests.group;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.model.Groups;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Log
public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test33");
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

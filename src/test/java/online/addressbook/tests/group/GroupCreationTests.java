package online.addressbook.tests.group;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

@Log
public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData groupData = new GroupData().withName("test33");
        app.group().create(groupData);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        log.info("Created new Group with name " + groupData.getName());

        groupData.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt());
        before.add(groupData);
        Assert.assertEquals(after, before);
        log.info("Lists of groups before and after creation has remained unchanged");
    }


}

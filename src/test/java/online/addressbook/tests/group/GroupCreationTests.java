package online.addressbook.tests.group;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

@Log
public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData groupData = new GroupData()
                .withName("test33")
                .withHeader("test33")
                .withFooter("test33");
        app.group().create(groupData);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        log.info("Created new Group with name " + groupData.getName());

        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        groupData.withId(after.stream().max(byId).get().getId());
        before.add(groupData);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
        log.info("Lists of groups before and after creation has remained unchanged");
    }


}

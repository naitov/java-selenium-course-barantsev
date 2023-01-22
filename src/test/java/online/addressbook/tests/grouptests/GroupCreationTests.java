package online.addressbook.tests.grouptests;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Log
public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().initGroupCreation();
        GroupData groupData = new GroupData("test33", "test33", "test33");
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitCreation();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
        log.info("Created new Group with name " + groupData.getName());

        Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
        groupData.setId(after.stream().max(byID).get().getId());
        before.add(groupData);
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(after, before);
        log.info("Lists of groups before and after creation has remained unchanged");
    }
}

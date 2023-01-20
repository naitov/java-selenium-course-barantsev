package online.addressbook.tests.grouptests;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        log.info("Created new Group with name " + groupData.name());
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}

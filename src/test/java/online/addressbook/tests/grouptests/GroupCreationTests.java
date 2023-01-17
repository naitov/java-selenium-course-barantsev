package online.addressbook.tests.grouptests;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

@Log
public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        GroupData groupData = new GroupData("test33", "test33", "test33");
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitCreation();
        app.getGroupHelper().returnToGroupPage();
        log.info("Created new Group with name " + groupData.name());
    }

}

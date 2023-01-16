package online.addressbook.tests;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import org.testng.annotations.Test;

@Log
public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        GroupData groupData = new GroupData("test33", "test44", "test55");
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitCreation();
        app.getGroupHelper().returnToGroupPage();
        log.info("Created new Group with name " + groupData.name());
    }

}

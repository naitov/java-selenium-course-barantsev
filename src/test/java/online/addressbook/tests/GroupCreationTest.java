package online.addressbook.tests;

import online.addressbook.entities.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test33", "test44", "test55"));
        submitCreation();
        returnToGroupPage();
    }

}

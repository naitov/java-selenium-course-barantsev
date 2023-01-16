package online.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() {
        gotoGroupPage();
        selectFirstGroup();
        deleteGroup();
        returnToGroupPage();
    }
}

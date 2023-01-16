package online.addressbook.tests.grouptests;

import lombok.extern.java.Log;
import online.addressbook.tests.TestBase;
import org.testng.annotations.Test;

@Log
public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectFirstGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
        log.info("Deleted first group in list");
    }
}

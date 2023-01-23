package online.addressbook.tests.group;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

@Log
public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.stream().iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        assertEquals(after.size(), before.size() - 1);
        log.info("Removed the last group in set");

        before.remove(deletedGroup);
        assertEquals(after, before);
        log.info("List of other groups after deletion has remained unchanged");
    }
}

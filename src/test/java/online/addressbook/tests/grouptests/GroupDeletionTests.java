package online.addressbook.tests.grouptests;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log
public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {
        ensurePreconditions();
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        assertEquals(after.size(), index);
        log.info("Removed the last group in list");

        before.remove(before.size() - 1);
        assertEquals(after, before);
        log.info("List of other groups after deletion has remained unchanged");
    }


}

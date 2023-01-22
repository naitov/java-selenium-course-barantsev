package online.addressbook.tests.grouptests;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Log
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupModification() {
        ensurePreconditions();
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "test1", "test2", "test3");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());
        log.info("Group list sizes before and after modification are equal.");
    }
}

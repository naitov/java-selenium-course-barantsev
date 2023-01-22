package online.addressbook.tests.group;

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
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        ensurePreconditions();
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData()
                .withId(before.get(index).getId())
                .withName("test1")
                .withHeader("test2")
                .withFooter("test3");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());
        log.info("Group list sizes before and after modification are equal.");
    }
}

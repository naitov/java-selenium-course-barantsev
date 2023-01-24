package online.addressbook.tests.group;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.model.Groups;
import online.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        Groups before = app.group().all();
        GroupData deletedGroup = before.stream().iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().amount(), equalTo(before.size() - 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
        log.info("Removed one group, other groups are unchanged");
    }
}

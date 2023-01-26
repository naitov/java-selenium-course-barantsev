package online.addressbook.tests.group;

import lombok.extern.slf4j.Slf4j;
import online.addressbook.model.GroupData;
import online.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("test1")
                .withHeader("test2")
                .withFooter("test3");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));
        log.info("Sets before and after modification have equal size");
    }
}

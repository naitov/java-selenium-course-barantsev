package online.addressbook.appmanager;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import online.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log
public class GroupHelper extends HelperBase {

    private Groups groupCache = null;

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    private void selectGroupById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int amount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.getAttribute("value"));
            String name = element.getAttribute("title").substring(8, element.getAttribute("title").length() - 1);
            GroupData gData = new GroupData().withId(id).withName(name);
            groupCache.add(gData);
        }
        return groupCache;
    }
}

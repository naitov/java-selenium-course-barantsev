package online.addressbook.appmanager;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Log
public class GroupHelper extends HelperBase {

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

    public void selectGroup(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitCreation();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return (isElementPresent(By.name("selected[]")));
    }

    public int getGroupCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groupList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.getAttribute("value"));
            String name = element.getAttribute("title").substring(8, element.getAttribute("title").length() - 1);
            GroupData gData = new GroupData(id, name, null, null);
            groupList.add(gData);
        }
        return groupList;
    }
}

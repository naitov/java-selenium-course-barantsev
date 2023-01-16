package online.addressbook.appmanager;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        type("group_name", groupData.name());
        type("group_header", groupData.header());
        type("group_footer", groupData.footer());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void selectFirstGroup() {
        click(By.name("selected[]"));
    }
}

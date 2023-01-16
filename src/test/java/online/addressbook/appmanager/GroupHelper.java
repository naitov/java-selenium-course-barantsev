package online.addressbook.appmanager;

import lombok.extern.java.Log;
import online.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log
public class GroupHelper {
    private WebDriver driver;

    public GroupHelper(WebDriver driver) {

        this.driver = driver;
    }

    public void returnToGroupPage() {
        driver.findElement(By.linkText("group page")).click();
    }

    public void submitCreation() {
        driver.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(groupData.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(groupData.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(groupData.footer());
    }

    public void initGroupCreation() {
        driver.findElement(By.name("new")).click();
    }

    public void deleteGroup() {
        driver.findElement(By.name("delete")).click();
    }

    public void selectFirstGroup() {
        driver.findElement(By.name("selected[]")).click();
    }
}

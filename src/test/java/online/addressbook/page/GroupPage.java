package online.addressbook.page;

import online.addressbook.section.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class GroupPage extends Page {

    @FindBy(xpath = "//input[@name='new']")
    WebElement newGroupBtn;

    @FindBy(xpath = "//input[@name='group_name']")
    WebElement groupName;

    @FindBy(xpath = "//textarea[@name='group_header']")
    WebElement groupHeader;

    @FindBy(xpath = "//textarea[@name='group_footer']")
    WebElement groupFooter;

    @FindBy(xpath = "//input[@name='submit']")
    WebElement groupSubmitBtn;

    public GroupPage(WebDriver driver) {
        super(driver);
    }

    public void makeNewGroupRecord(Group group) {
        newGroupBtn.click();
        groupName.sendKeys(group.getName());
        groupHeader.sendKeys(group.getHeader());
        groupFooter.sendKeys(group.getFooter());
        groupSubmitBtn.click();
    }

    public String getPostCreationMessage() {
        return new WebDriverWait(driver, Duration.of(2, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='msgbox']")))
                .getText();
    }

}

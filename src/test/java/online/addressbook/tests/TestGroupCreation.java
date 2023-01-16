package online.addressbook.tests;

import online.addressbook.entities.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TestGroupCreation {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        driver.get("https://addressbook2077.000webhostapp.com/");
        driver.manage().window().maximize();
        login("admin", "secret");
    }

    private void login(String username, String password) {
        driver.findElement(By.name("user")).sendKeys(username);
        driver.findElement(By.name("pass")).sendKeys(password);
        driver.findElement(By.cssSelector("input:nth-child(7)")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGoupForm(new GroupData("test33", "test44", "test55"));
        submitCreation();
        returnToGroupPage();
    }

    private void returnToGroupPage() {
        driver.findElement(By.linkText("group page")).click();
    }

    private void submitCreation() {
        driver.findElement(By.name("submit")).click();
    }

    private void fillGoupForm(GroupData groupData) {
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(groupData.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(groupData.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(groupData.footer());
    }

    private void initGroupCreation() {
        driver.findElement(By.name("new")).click();
    }

    private void gotoGroupPage() {
        driver.findElement(By.xpath("//*[@id='nav']/ul/li[3]/a")).click();
    }
}

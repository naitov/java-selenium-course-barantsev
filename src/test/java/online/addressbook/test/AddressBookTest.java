package online.addressbook.test;

import online.addressbook.page.GroupPage;
import online.addressbook.page.LoginPage;
import online.addressbook.page.MainPage;
import online.addressbook.section.Group;
import online.addressbook.user.AddressBookUser;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class AddressBookTest {

    public static final String MAIN_PAGE_URL = "https://addressbook2077.000webhostapp.com";
    public static final String USER_NAME = "admin";
    public static final String USER_PASS = "secret";
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(5, ChronoUnit.SECONDS));
    }

    @AfterMethod(alwaysRun = true)
    public void terminateDriver() {
        driver.quit();
        driver = null;
    }

    @Test
    public void userCanLogin() {
        AddressBookUser user = new AddressBookUser();
        MainPage mainPage = new LoginPage(driver)
                .getLoginPage(MAIN_PAGE_URL)
                .login(user.getName(), user.getPassword());
        String actualResult = mainPage.getUsernameFromLogoutField();
        assertThat("Username in logout field should be the same as in the login field", actualResult, is(USER_NAME));
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void userCanNotLoginWithWrongPassword() {
        AddressBookUser user = new AddressBookUser();
        MainPage mainPage = new LoginPage(driver)
                .getLoginPage(MAIN_PAGE_URL)
                .login(user.getName(), user.getRandomPassword());
            String actualResult = mainPage.getUsernameFromLogoutField();
        assertThat("Wrong password should not open a new page", actualResult, null);
    }

    @Test
    public void groupCanBeCreated() {
        AddressBookUser user = new AddressBookUser();
        MainPage mainPage = new LoginPage(driver)
                .getLoginPage(MAIN_PAGE_URL)
                .login(user.getName(), user.getPassword());
        Group group = new Group("test1", "testh", "testf");
        GroupPage groupPage = mainPage.openGroupPage();
        groupPage.makeNewGroupRecord(group);
        assertThat("Message can be shown after group creation", groupPage.getPostCreationMessage(),
                containsString("A new group has been entered"));
    }


}

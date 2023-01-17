package online.addressbook.tests;

import lombok.extern.java.Log;
import online.addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

@Log
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager("safari");

    @BeforeClass
    public void setUpBrowser() {
        app.initBrowser();
    }

    @BeforeMethod
    public void setUpFramework() {
        app.init();
        log.info("Init webDriver and login");
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
        log.info("Quit webDriver");
    }

}

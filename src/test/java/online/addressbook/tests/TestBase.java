package online.addressbook.tests;

import lombok.extern.java.Log;
import online.addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@Log
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager("chrome");

    @BeforeSuite
    public void setUpFramework() {
        app.initBrowser();
        app.initWebDriver();
        log.info("Init webDriver and login");
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
        log.info("Quit webDriver");
    }

}

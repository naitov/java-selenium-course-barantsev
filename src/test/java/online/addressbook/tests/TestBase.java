package online.addressbook.tests;

import lombok.extern.slf4j.Slf4j;
import online.addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));

    @BeforeSuite
    public void setUpFramework() {
        app.initBrowser();
        app.initWebDriver();
        log.info("Init webDriver and login");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
        log.info("Quit webDriver");
    }

    @BeforeMethod
    public void logOnTestStart(Method m, Object[] p) {
        log.info(String.format("Started %s with parameters: %s", m.getName(), Arrays.asList(p)));
    }

    @AfterMethod(alwaysRun = true)
    public void logOnTestStop(Method m) {
        log.info(String.format("Stopped %s", m.getName()));
    }
}

package online.addressbook.appmanager;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(By by) {
        new WebDriverWait(driver, getTimeout(Timeouts.TWO_SEC)).until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private static Duration getTimeout(Timeouts amount) {
        return Duration.of(amount.getValue(), ChronoUnit.SECONDS);
    }

    protected void type(String locatorByName, String text) {
        if (text != null) {
            click(By.name(locatorByName));
            driver.findElement(By.name(locatorByName)).sendKeys(text);
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private enum Timeouts {
        TWO_SEC (2),
        FIVE_SEC (5);
        @Getter private final int value;
        Timeouts(int timeout) {
            this.value = timeout;
        }
    }
}

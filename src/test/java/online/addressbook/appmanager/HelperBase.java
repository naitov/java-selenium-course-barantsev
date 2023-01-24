package online.addressbook.appmanager;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    protected static Duration getTimeout(Timeouts amount) {
        return Duration.of(amount.getValue(), ChronoUnit.SECONDS);
    }

    protected void click(By by) {
        getWebElementWithClickableWait(by, getTimeout(Timeouts.FIVE_SEC)).click();
    }

    protected WebElement getWebElementWithClickableWait(By by, Duration timeout) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement getWebElementWithPresenceWait(By by, Duration timeout) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void type(By locator, String text) {
        if (text != null) {
            String existingText = getWebElementWithClickableWait(locator, getTimeout(Timeouts.TWO_SEC)).getAttribute("value");
            if (!existingText.equals(text)) {
                click(locator);
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
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

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void selectElementByVisibleText(By listLocator, String text) {
        getWebElementWithClickableWait(listLocator, getTimeout(Timeouts.FIVE_SEC));
        new Select(driver.findElement(listLocator)).selectByVisibleText(text);
    }

    protected enum Timeouts {
        TWO_SEC(2),
        FIVE_SEC(5);
        @Getter
        private final int value;

        Timeouts(int timeout) {
            this.value = timeout;
        }
    }
}

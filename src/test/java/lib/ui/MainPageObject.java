package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String errorMessage) {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            TouchAction action = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int yStart = (int) (size.height * 0.8);
            int yEnd = (int) (size.height * 0.2);
            action.press(x, yStart).waitAction(timeOfSwipe).moveTo(x, yEnd).release().perform();
        } else
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
    }

    protected void swipeUpQuick() {
        swipeUp(500);
    }

    public void swipeUpTillElementAppers(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        int alreadySwiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
                return;
            }
            swipeUpQuick();
            alreadySwiped++;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element by locator", 1)
                .getLocation()
                .getY();
        if (Platform.getInstance().isMobileWeb()) {
            JavascriptExecutor jsExecutor = driver;
            Object jsResult = jsExecutor.executeScript("return window.pageYOffset");
            elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            swipeUpQuick();
            alreadySwiped++;
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up \n" + errorMessage, 0);
                return;
            }
        }
    }

    public void swipeElementToTheLeft(String locator, String errorMessage) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitForElementPresent(locator, errorMessage, 10);
            int xLeft = element.getLocation().getX();
            int xRight = xLeft + element.getSize().getWidth();
            int yUpper = element.getLocation().getY();
            int yLower = yUpper + element.getSize().getHeight();
            int yMiddle = (yUpper + yLower) / 2;
            TouchAction action = new TouchAction(driver);
            action.press(xRight, yMiddle)
                    .waitAction(300)
                    .moveTo(xLeft, yMiddle)
                    .release()
                    .perform();
        } else
            System.out.println("Method swipeElementToTheLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        String defaultMessage = "An element " + locator + " supposed to be not present";
        int elementsAmount = getAmountOfElements(locator);
        if (elementsAmount > 0) {
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertElementPresent(String locator, String errorMessage) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        assertTrue(errorMessage, elements.size() > 0);
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else if (byType.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
        }
    }

    public void scrollWebPage() {
        if (Platform.getInstance().isMobileWeb()) {
            JavascriptExecutor jsExecutor = driver;
            jsExecutor.executeScript("window.scrollBy(0,250)");
        } else
            System.out.println("Method scrollWebPage() does nothing for platform " + Platform.getInstance().getPlatformVar());
    }

    public void scrollWebPageTillElementNotVisible(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        WebElement element = this.waitForElementPresent(locator, errorMessage);
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPage();
            ++alreadySwiped;
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMessage, element.isDisplayed());
            }
        }
        if (Platform.getInstance().isMobileWeb()) {
            JavascriptExecutor jsExecutor = driver;
            jsExecutor.executeScript("window.scrollBy(0,250)");
        } else
            System.out.println("Method scrollWebPage() does nothing for platform " + Platform.getInstance().getPlatformVar());
    }

    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int maxAttempts) {
        int currentAttempts = 0;
        boolean needMoreAttempts = true;

        while (needMoreAttempts) {
            try {
                this.waitForElementAndClick(locator, errorMessage, 1);
                needMoreAttempts = false;
            } catch (Exception e) {
                if (currentAttempts > maxAttempts) {
                    this.waitForElementAndClick(locator, errorMessage, 1);
                }
            }
            ++currentAttempts;
        }
    }

    public String takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("This screenshot was taken: " + path);
        }
        catch (Exception e) {
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }
}

package lesson2.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    AppiumDriver driver;

    @Before
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Java/GitHub/Projects/MobileATCourse/apks/wiki.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        waitForElementAndClick(By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find init search field",
                5);
        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Java",
                "Cannot find search input",
                5);
        WebElement element =
                waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_description' " +
                                "and @text = 'Object-oriented programming language']"),
                        "Cannot find 'Object-oriented programming language'",
                        15);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);
        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Java",
                "Cannot find search input",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find button to clear search",
                5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"),
                "clear button is still present",
                5);
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Java",
                "Cannot find search input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_description' " +
                        "and @text = 'Object-oriented programming language']"),
                "Cannot find search input",
                5);

        WebElement titleElement = waitForElementPresent(By.xpath("//android.view.View[@content-desc='Java (programming language)']"),
                "Cannot find article title",
                15);

        String articleTitle = titleElement.getAttribute("name");
        Assert.assertEquals("We see unexpected title", "Java (programming language)", articleTitle);
    }

    WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}

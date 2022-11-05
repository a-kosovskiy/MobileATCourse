package lesson2.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
                "Cannot find 'skip' button",
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
                "Cannot find 'skip' button",
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
                "Cannot find 'skip' button",
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

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'skip' button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Appium",
                "Cannot find search input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = 'Appium']"),
                "Cannot find search input",
                5);

        waitForElementPresent(By.xpath("//android.view.View[@content-desc = 'Appium']"),
                "Cannot find article title",
                15);

        swipeUpToFindElement(By.xpath("//*[@content-desc = 'View article in browser']"), "Test error message", 20);
    }

    @Test
    public void saveFirstArticleToMyList() {
        String itemTitle = "Java (programming language)";
        String listName = "Learning programming";
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'skip' button",
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

        waitForElementPresent(By.xpath("//android.view.View[@content-desc='" + itemTitle + "']"),
                "Cannot find article title",
                15);

        waitForElementAndClick(By.xpath("//*[@content-desc = 'Save']"),
                "Cannot find 'Save' button",
                5);

        waitForElementAndClick(By.xpath("//*[@text = 'ADD TO LIST']"),
                "Cannot find 'Add to list' button",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), listName,
                "Cannot put text into articles folder name",
                5);

        waitForElementAndClick(By.xpath("//*[@text = 'OK']"),
                "Cannot find 'OK' button",
                5);

        waitForElementAndClick(By.xpath("//*[@text = 'VIEW LIST']"),
                "Cannot press 'VIEW LIST' button",
                5);

        swipeElementToTheLeft(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + itemTitle + "']"),
                "Cannot delete article by swipe");

        waitForElementPresent(By.id("org.wikipedia:id/reading_list_empty_text"),
                "Cannot find 'no articles' text",
                5);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchText = "Linkin Park Discography";
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'skip' button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), searchText,
                "Cannot find search input",
                5);

        waitForElementPresent(By.xpath(searchResultLocator),
                "Cannot find search results",
                15);

        int searchResultsAmount = getAmountOfElements(By.xpath(searchResultLocator));
        Assert.assertTrue("We found no results", searchResultsAmount > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchText = "ozirsgofewp";
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String emptyResultLabel = "//*[@text='No results']";
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), searchText,
                "Cannot find search input",
                5);

        waitForElementPresent(By.xpath(emptyResultLabel),
                "Cannot find empty result label for search text " + searchText,
                15);

        assertElementNotPresent(By.xpath(searchResultLocator), "We've found some search results for " + searchText);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        String searchText = "Java";
        String itemTitle = "Java (programming language)";
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'skip' button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Java",
                "Cannot find search input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + itemTitle + "']"),
                "Cannot find search result item by searching " + searchText,
                15);

        String descriptionBeforeRotation = waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find article title",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);
        String descriptionAfterRotation = waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find article title",
                15);

        Assert.assertEquals("Article title changed after screen rotation", descriptionBeforeRotation,
                descriptionAfterRotation);

        driver.rotate(ScreenOrientation.PORTRAIT);
        String descriptionAfterSecondRotation = waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find article title",
                15);

        Assert.assertEquals("Article title changed after screen rotation", descriptionBeforeRotation,
                descriptionAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        String searchText = "Java";
        String itemTitle = "Java (programming language)";
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'skip' button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Java",
                "Cannot find search input",
                5);

        waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + itemTitle + "']"),
                "Cannot find search result item by searching " + searchText,
                5);

        driver.runAppInBackground(2);

        waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + itemTitle + "']"),
                "Cannot find search result after returning from background",
                5);
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

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int yStart = (int) (size.height * 0.8);
        int yEnd = (int) (size.height * 0.2);
        action.press(x, yStart).waitAction(timeOfSwipe).moveTo(x, yEnd).release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(500);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            swipeUpQuick();
            alreadySwiped++;
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up \n" + errorMessage, 0);
                return;
            }
        }
    }

    protected void swipeElementToTheLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 10);
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
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String errorMessage) {
        String defaultMessage = "An element " + by.toString() + " supposed to be not present";
        int elementsAmount = getAmountOfElements(by);
        if (elementsAmount > 0) {
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}

package lesson2.tests;

import lib.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;

public class Ex5 extends CoreTestCase {

    @Test
    public void saveTwoArticlesTest() {
        String article1Title = "Java (programming language)";
        String article2Title = "JavaScript";
        String listName = "Ex5";
        mainPageObject.clickSkipButton();

        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Java",
                "Cannot find search input",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + article1Title + "']"),
                "Cannot find article",
                5);

        mainPageObject.waitForElementPresent(By.xpath("//android.view.View[@content-desc='" + article1Title + "']"),
                "Cannot find article title",
                15);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc = 'Save']"),
                "Cannot find 'Save' button",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.Button[@text = 'ADD TO LIST']"),
                "Cannot find 'Add to list' button",
                5);

        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), listName,
                "Cannot put text into articles folder name",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@text = 'OK']"),
                "Cannot find 'OK' button",
                5);

        mainPageObject.waitForElementAndClick(By.id("Navigate up"),
                "Cannot find 'Navigate up' button",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + article2Title + "']"),
                "Cannot find article",
                5);

        mainPageObject.waitForElementPresent(By.xpath("//android.view.View[@content-desc='" + article2Title + "']"),
                "Cannot find article title",
                15);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc = 'Save']"),
                "Cannot find 'Save' button",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.Button[@text = 'ADD TO LIST']"),
                "Cannot find 'Add to list' button",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@text = '" + listName + "']"),
                "Cannot find list button",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@text = 'VIEW LIST']"),
                "Cannot press 'View list' button",
                5);

        mainPageObject.swipeElementToTheLeft(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + article1Title + "']"),
                "Cannot delete article by swipe");

        mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + article2Title + "']"),
                "Cannot find article",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + article2Title + "']"),
                "Cannot open article",
                5);

        mainPageObject.waitForElementPresent(By.xpath("//android.view.View[@content-desc='" + article2Title + "']"),
                "Cannot find article title",
                15);
    }
}

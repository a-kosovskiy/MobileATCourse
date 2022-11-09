package lesson2.tests;

import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

public class Ex6 extends CoreTestCase {

    @Test
    public void testAssertTitlePresent() {
        String articleTitle = "Java (programming language)";
        mainPageObject.clickSkipButton();

        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Java",
                "Cannot find search input",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
                        "and @text = '" + articleTitle + "']"),
                "Cannot find article",
                5);

        assertElementPresent(By.xpath("//android.view.View[@content-desc='" + articleTitle + "']"),
                "Cannot find article title");
    }

    private void assertElementPresent(By by, String errorMessage) {
        List elements = driver.findElements(by);
        Assert.assertTrue(errorMessage, elements.size() > 0);
    }
}

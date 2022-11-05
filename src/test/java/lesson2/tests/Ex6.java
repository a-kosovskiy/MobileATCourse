package lesson2.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

public class Ex6 extends FirstTest {

    @Test
    public void testAssertTitlePresent() {
        String articleTitle = "Java (programming language)";
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);

        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Java",
                "Cannot find search input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' " +
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

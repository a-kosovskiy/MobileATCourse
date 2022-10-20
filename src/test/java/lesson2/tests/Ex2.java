package lesson2.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Ex2 extends FirstTest {

    @Test
    public void testSearchInputText() {
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);
        assertElementHasText(By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Поле не содержит текст 'Search Wikipedia'");
    }

    private void assertElementHasText(By by, String text, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        Assert.assertEquals(errorMessage, text, element.getAttribute("text"));
    }
}

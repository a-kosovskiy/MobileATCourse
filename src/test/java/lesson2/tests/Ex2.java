package lesson2.tests;

import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Ex2 extends CoreTestCase {

    @Test
    public void testSearchInputText() {
        mainPageObject.clickSkipButton();
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);
        assertElementHasText(By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Поле не содержит текст 'Search Wikipedia'");
    }

    private void assertElementHasText(By by, String text, String errorMessage) {
        WebElement element = mainPageObject.waitForElementPresent(by, errorMessage, 5);
        Assert.assertEquals(errorMessage, text, element.getAttribute("text"));
    }
}

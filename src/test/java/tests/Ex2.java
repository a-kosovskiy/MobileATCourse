package tests;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class Ex2 extends CoreTestCase {

    @Test
    public void testSearchInputText() {
        new WelcomePageObject(driver).clickSkipButton();
        mainPageObject.waitForElementAndClick("org.wikipedia:id/search_container",
                "Cannot find init search field",
                5);
        assertElementHasText("id:org.wikipedia:id/search_src_text",
                "Search Wikipedia",
                "Поле не содержит текст 'Search Wikipedia'");
    }

    private void assertElementHasText(String locator, String text, String errorMessage) {
        WebElement element = mainPageObject.waitForElementPresent(locator, errorMessage, 5);
        Assert.assertEquals(errorMessage, text, element.getAttribute("text"));
    }
}

package lesson2.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Ex3 extends FirstTest {

    @Test
    public void testFindResultsAndClearSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);
        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), "Selenium",
                "Cannot find search input",
                5);
        List<WebElement> searchResults = waitForAllElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "Не отображается список результатов",
                15);
        Assert.assertTrue("Найдено более одной статьи", searchResults.size() > 1);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find button to clear search",
                5);
        waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "Отображается список результатов",
                15);
    }

    private List<WebElement> waitForAllElementsPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
}

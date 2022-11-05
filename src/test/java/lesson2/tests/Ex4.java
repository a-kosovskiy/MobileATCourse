package lesson2.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Ex4 extends FirstTest {

    @Test
    public void testFindWordInSearchResults() {
        String searchText = "Java";
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find init search field",
                5);
        waitForElementAndSendKeys(By.xpath("//*[@text='Search Wikipedia']"), searchText,
                "Cannot find search input",
                5);
        List<WebElement> searchResults = waitForAllElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "Не отображается список результатов",
                15);
        for (WebElement webElement : searchResults) {
            Assert.assertTrue("Название статьи не содержит ожидаемый текст " + searchText,
                    webElement.getText().toLowerCase().contains(searchText.toLowerCase()));
        }
    }

    private List<WebElement> waitForAllElementsPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
}
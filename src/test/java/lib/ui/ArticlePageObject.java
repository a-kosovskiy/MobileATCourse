package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String TITLE_ELEMENT = "//*[@resource-id = 'pcs-edit-section-title-description']/preceding-sibling::*[@content-desc]",
            FOOTER_ELEMENT = "//*[@content-desc = 'View article in browser']",
            SAVE_BUTTON = "//*[@content-desc = 'Save']",
            ADD_TO_MY_LIST_BUTTON = "//*[@text = 'ADD TO LIST']",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text = 'OK']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE_ELEMENT),
                "Cannot find article title on page",
                15);
    }

    public void swipeToTheFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("name");
    }

    public void addArticleToMyList(String listName) {
        this.waitForElementAndClick(By.xpath(SAVE_BUTTON),
                "Cannot find 'Save' button",
                5);

        this.waitForElementAndClick(By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Cannot find 'Add to list' button",
                5);

        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT), listName,
                "Cannot put text into articles folder name",
                5);

        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find 'OK' button",
                5);
    }
}

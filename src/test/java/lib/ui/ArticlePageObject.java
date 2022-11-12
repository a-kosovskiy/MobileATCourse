package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String TITLE_ELEMENT,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE_ELEMENT,
                "Cannot find article title on page",
                15);
    }

    public void swipeToTheFooter() {
        this.swipeUpTillElementAppers(FOOTER_ELEMENT, "Cannot find the end of article", 40);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("name");
    }

    public void addArticleToNewList(String listName) {
        this.waitForElementAndClick(SAVE_BUTTON,
                "Cannot find 'Save' button",
                5);

        this.waitForElementAndClick(ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to list' button",
                5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, listName,
                "Cannot put text into articles folder name",
                5);

        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot find 'OK' button",
                5);
    }

    public void addArticleToList(String listName) {
        this.waitForElementAndClick(SAVE_BUTTON,
                "Cannot find 'Save' button",
                5);

        this.waitForElementAndClick(ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to list' button",
                5);

        this.waitForElementAndClick("xpath://*[@text = '" + listName + "']",
                "Cannot find list button",
                5);
    }

    public void assertTitlePresent() {
        this.assertElementPresent(TITLE_ELEMENT, "Cannot find title element");
    }
}

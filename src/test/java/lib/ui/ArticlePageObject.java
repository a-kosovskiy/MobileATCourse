package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String TITLE_ELEMENT,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            REMOVE_FROM_MY_LIST_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE_ELEMENT,
                "Cannot find article title on page",
                15);
    }

    public void swipeToTheFooter() {
        String errorMessage = "Cannot find the end of article";
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpTillElementAppers(FOOTER_ELEMENT, errorMessage, 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, errorMessage, 40);
        }

    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("name");
        } else
            return titleElement.getText();
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

    public void addArticleToMySaved() {
        if (Platform.getInstance().isMobileWeb()) {
            this.removeArticleFromSavedIfItWasAdded();
        }
        this.waitForElementAndClick(ADD_TO_MY_LIST_BUTTON, "Cannot find button to add article to saved list", 5);
    }

    public void removeArticleFromSavedIfItWasAdded() {
        if (this.isElementPresent(REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved", 1);
        }
        this.waitForElementPresent(ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to add article to saved list after removing it from this list before");
    }

    public void checkArticleIsSavedToMyList() {
        this.waitForElementPresent(REMOVE_FROM_MY_LIST_BUTTON, "Cannot find 'Remove from my list' button",
                5);
    }
}

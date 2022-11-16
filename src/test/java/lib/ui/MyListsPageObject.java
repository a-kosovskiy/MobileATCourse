package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String ARTICLE_BY_TITLE_TPL, ADD_TO_SAVED_BUTTON, REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Get saved article xpath by title '{articleTitle}'")
    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    @Step("Get 'remove' button xpath by title '{articleTitle}'")
    private static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    @Step("Get 'add' button xpath by title '{articleTitle}'")
    private static String getAddButtonByTitle(String articleTitle) {
        return ADD_TO_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    @Step("Swipe article to delete by title '{articleTitle}'")
    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        if (Platform.getInstance().isAndroid()) {
            this.swipeElementToTheLeft(getSavedArticleXpathByTitle(articleTitle),
                    "Cannot delete article by swipe");
        } else if (Platform.getInstance().isMobileWeb()) {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(removeLocator, "Cannot click button to remove article from saved",
                    10);
        } else {
            System.out.println("Method scrollWebPage() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
        if (Platform.getInstance().isMobileWeb()) {
            this.waitForElementPresent(getAddButtonByTitle(articleTitle), "Cannon find 'Add to saved' button",
                    5);
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    @Step("Wait for article with title '{articleTitle}' to appear")
    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementPresent(getSavedArticleXpathByTitle(articleTitle),
                "Cannot find saved article by title " + articleTitle,
                15);
    }

    @Step("Wait for article with title '{articleTitle}' to disappear")
    public void waitForArticleToDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(getSavedArticleXpathByTitle(articleTitle),
                "Saved article still present with title " + articleTitle,
                15);
    }

    @Step("Click by article with title '{articleTitle}'")
    public void clickByArticleWithTitle(String articleTitle) {
        this.waitForElementAndClick(getSavedArticleXpathByTitle(articleTitle),
                "Cannot open article",
                5);
    }
}

package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String ARTICLE_BY_TITLE_TPL;

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        this.swipeElementToTheLeft(getSavedArticleXpathByTitle(articleTitle),
                "Cannot delete article by swipe");
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementPresent(getSavedArticleXpathByTitle(articleTitle),
                "Cannot find saved article by title " + articleTitle,
                15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(getSavedArticleXpathByTitle(articleTitle),
                "Saved article still present with title " + articleTitle,
                15);
    }

    public void clickByArticleWithTitle(String articleTitle) {
        this.waitForElementAndClick(getSavedArticleXpathByTitle(articleTitle),
                "Cannot open article",
                5);
    }
}

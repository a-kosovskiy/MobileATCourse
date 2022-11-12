package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class Ex5 extends CoreTestCase {

    @Test
    public void testSaveTwoArticles() {
        String searchLine = "Java";
        String article1Title = "Java (programming language)";
        String article2Title = "JavaScript";
        String listName = "Ex5";
        new WelcomePageObject(driver).clickSkipButton();
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring(article1Title);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToNewList(listName);
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.navigateUp();
        searchPageObject.clickByArticleWithSubstring(article2Title);
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToList(listName);
        navigationUI.viewList();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.swipeByArticleToDelete(article1Title);
        myListsPageObject.waitForArticleToAppearByTitle(article2Title);
        myListsPageObject.clickByArticleWithTitle(article2Title);
        articlePageObject.waitForTitleElement();
        assertEquals("Article title is correct", article2Title, articlePageObject.getArticleTitle());
    }
}

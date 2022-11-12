package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        String articleTitle = "Java (programming language)";
        String listName = "Learning programming";
        new WelcomePageObject(driver).clickSkipButton();
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToNewList(listName);

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.viewList();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }
}

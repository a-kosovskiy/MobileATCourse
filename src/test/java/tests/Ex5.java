package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex5 extends CoreTestCase {

    @Test
    public void testSaveTwoArticles() {
        String searchLine = "Java";
        String article1Title = "Java (programming language)";
        String article2Title = "JavaScript";
        String listName = "Ex5";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring(article1Title);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToNewList(listName);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.navigateUp();
        searchPageObject.clickByArticleWithSubstring(article2Title);
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToList(listName);
        navigationUI.viewList();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.swipeByArticleToDelete(article1Title);
        myListsPageObject.waitForArticleToAppearByTitle(article2Title);
        myListsPageObject.clickByArticleWithTitle(article2Title);
        articlePageObject.waitForTitleElement();
        assertEquals("Article title is correct", article2Title, articlePageObject.getArticleTitle());
    }
}

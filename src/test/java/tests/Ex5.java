package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
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
        String article1Description = "Object-oriented programming language";
        String article2Title = "JavaScript";
        String article2Description = "High-level programming language";
        String listName = "Ex5";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring(article1Description);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewList(listName);
        } else {
            articlePageObject.addArticleToMySaved();
            AuthorizationPageObject authPage = new AuthorizationPageObject(driver);
            authPage.clickAuthButton();
            authPage.enterLoginData();
            authPage.submitForm();
            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login", article1Title, articlePageObject.getArticleTitle());
            articlePageObject.checkArticleIsSavedToMyList();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            navigationUI.navigateUp();
        } else {
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine(searchLine);
        }

        searchPageObject.clickByArticleWithSubstring(article2Description);
        articlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToList(listName);
            navigationUI.viewList();
        } else {
            articlePageObject.addArticleToMySaved();
            navigationUI.openNavigation();
            navigationUI.clickMyLists();
        }

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.swipeByArticleToDelete(article1Title);
        myListsPageObject.waitForArticleToAppearByTitle(article2Title);
        myListsPageObject.clickByArticleWithTitle(article2Title);
        articlePageObject.waitForTitleElement();
        assertEquals("Article title is correct", article2Title, articlePageObject.getArticleTitle());
        if (Platform.getInstance().isMobileWeb()) {
            articlePageObject.checkArticleIsSavedToMyList();
        }
    }
}

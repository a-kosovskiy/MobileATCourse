package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        String articleTitle = "Java (programming language)";
        String listName = "Learning programming";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewList(listName);
        } else {
            articlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMobileWeb()) {
            AuthorizationPageObject authPage = new AuthorizationPageObject(driver);
            authPage.clickAuthButton();
            authPage.enterLoginData();
            authPage.submitForm();
            articlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", articleTitle, articlePageObject.getArticleTitle());
            articlePageObject.addArticleToMySaved();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            navigationUI.viewList();
        } else if (Platform.getInstance().isMobileWeb()) {
            navigationUI.openNavigation();
            navigationUI.clickMyLists();
        }

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }
}

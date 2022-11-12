package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        String searchLine = "Java";
        String articleTitle = "Java (programming language)";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring(articleTitle);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String titleBeforeRotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle();
        assertEquals("Article title changed after screen rotation", titleBeforeRotation,
                titleAfterRotation);
        this.rotateScreenPortrait();
        String titleAfterSecondRotation = articlePageObject.getArticleTitle();
        assertEquals("Article title changed after screen rotation", titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        String searchLine = "Java";
        String articleTitle = "Java (programming language)";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForSearchResult(articleTitle);
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult(articleTitle);
    }
}

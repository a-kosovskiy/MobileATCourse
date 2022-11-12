package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class Ex6 extends CoreTestCase {

    @Test
    public void testAssertTitlePresent() {
        String searchLine = "Java";
        String articleTitle = "Java (programming language)";
        new WelcomePageObject(driver).clickSkipButton();
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring(articleTitle);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertTitlePresent();
    }
}

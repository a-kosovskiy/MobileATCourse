package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features({@Feature("Search"), @Feature("Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Java (Object-oriented programming language)' article and make sure the title is expected")
    @Step("Starting test testCompareArticleTitle")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();

        Assert.assertEquals("We see unexpected title", "Java (programming language)", articleTitle);
    }

    @Test
    @Features({@Feature("Search"), @Feature("Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(SeverityLevel.NORMAL)
    public void testSwipeArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("utomation");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToTheFooter();
    }
}

package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import org.junit.Assert;
import org.junit.Test;

public class Ex3 extends CoreTestCase {

    @Test
    public void testFindResultsAndClearSearch() {
        String searchLine = "Selenium";
        new WelcomePageObject(driver).clickSkipButton();
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        Assert.assertTrue("Найдено более одной статьи", searchPageObject.getAmountOfFoundArticles() > 1);
        searchPageObject.clickCancelSearch();
        searchPageObject.assertSearchResultNotPresent();
    }
}

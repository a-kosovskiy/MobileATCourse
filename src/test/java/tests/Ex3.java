package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class Ex3 extends CoreTestCase {

    @Test
    public void testFindResultsAndClearSearch() {
        String searchLine = "Selenium";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        Assert.assertTrue("Найдено более одной статьи", searchPageObject.getAmountOfFoundArticles() > 1);
        searchPageObject.clickCancelSearch();
        searchPageObject.assertSearchResultNotPresent();
    }
}

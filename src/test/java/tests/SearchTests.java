package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        new WelcomePageObject(driver).clickSkipButton();
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        new WelcomePageObject(driver).clickSkipButton();
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchLine = "Linkin Park Discography";
        new WelcomePageObject(driver).clickSkipButton();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);

        assertTrue("We found no results", searchPageObject.getAmountOfFoundArticles() > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchLine = "ozirsgofewp";
        new WelcomePageObject(driver).clickSkipButton();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertSearchResultNotPresent();
    }
}

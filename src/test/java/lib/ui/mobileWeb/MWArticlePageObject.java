package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE_ELEMENT = "css:div.page-heading>h1#firstHeading";
        FOOTER_ELEMENT = "css:div.footer-content";
        ADD_TO_MY_LIST_BUTTON = "xpath://li[@id='page-actions-watch']/a[not(contains(@class, 'watched'))]";
        REMOVE_FROM_MY_LIST_BUTTON = "xpath://li[@id='page-actions-watch']/a[contains(@class, 'watched')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

package lib.ui.mobileWeb;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://li[contains(@class,'page-summary')]//h3[text()='{TITLE}']";
        ADD_TO_SAVED_BUTTON = "xpath://li[contains(@class,'page-summary')][@title='{TITLE}']//a[not(contains(@class,'watched'))]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[contains(@class,'page-summary')][@title='{TITLE}']//a[contains(@class,'watched')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE_ELEMENT = "xpath://*[@resource-id = 'pcs-edit-section-title-description']/preceding-sibling::*[@content-desc]";
        FOOTER_ELEMENT = "xpath://*[@content-desc = 'View article in browser']";
        SAVE_BUTTON = "xpath://*[@content-desc = 'Save']";
        ADD_TO_MY_LIST_BUTTON = "xpath://*[@text = 'ADD TO LIST']";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

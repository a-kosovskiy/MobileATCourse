package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String VIEW_LIST_LINK,
            NAVIGATE_UP,
            OPEN_NAVIGATION,
            MY_LISTS_LINK;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void viewList() {
        this.waitForElementAndClick(VIEW_LIST_LINK,
                "Cannot press 'View list' button",
                5);
    }

    public void navigateUp() {
        this.waitForElementAndClick(NAVIGATE_UP,
                "Cannot find and click 'Navigate up' button",
                5);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMobileWeb()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click 'Open navigation' button", 5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyLists() {
        if (Platform.getInstance().isMobileWeb()) {
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Cannot find navigation button to My list", 10);
        } else {
            System.out.println("Method clickMyLists() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}

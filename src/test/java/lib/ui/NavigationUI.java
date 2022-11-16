package lib.ui;

import io.qameta.allure.Step;
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

    @Step("Click 'View list' button")
    public void viewList() {
        this.waitForElementAndClick(VIEW_LIST_LINK,
                "Cannot press 'View list' button",
                5);
    }

    @Step("Click 'Navigate up' button")
    public void navigateUp() {
        this.waitForElementAndClick(NAVIGATE_UP,
                "Cannot find and click 'Navigate up' button",
                5);
    }

    @Step("Click 'Open navigation' button (not supported for android and ios)")
    public void openNavigation() {
        if (Platform.getInstance().isMobileWeb()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click 'Open navigation' button", 5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open Watchlist (not supported for android and ios)")
    public void clickMyLists() {
        if (Platform.getInstance().isMobileWeb()) {
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Cannot find navigation button to My list", 10);
        } else {
            System.out.println("Method clickMyLists() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}

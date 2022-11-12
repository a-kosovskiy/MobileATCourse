package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String VIEW_LIST_LINK;
    protected static String NAVIGATE_UP;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void viewList() {
        this.waitForElementAndClick(VIEW_LIST_LINK,
                "Cannot press 'VIEW LIST' button",
                5);
    }

    public void navigateUp() {
        this.waitForElementAndClick(NAVIGATE_UP,
                "Cannot find and click 'Navigate up' button",
                5);
    }

}

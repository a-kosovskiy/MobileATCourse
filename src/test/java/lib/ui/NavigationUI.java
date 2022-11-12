package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {

    private static final String VIEW_LIST_LINK = "xpath://*[@text = 'VIEW LIST']";
    private static final String NAVIGATE_UP = "id:Navigate up";

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

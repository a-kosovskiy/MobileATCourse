package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        VIEW_LIST_LINK = "xpath://*[@text = 'VIEW LIST']";
        NAVIGATE_UP = "id:Navigate up";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}

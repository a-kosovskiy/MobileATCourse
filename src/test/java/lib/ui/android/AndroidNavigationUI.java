package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
        VIEW_LIST_LINK = "xpath://*[@text = 'VIEW LIST']";
        NAVIGATE_UP = "id:Navigate up";
    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}

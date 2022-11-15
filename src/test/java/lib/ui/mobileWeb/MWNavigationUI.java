package lib.ui.mobileWeb;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        OPEN_NAVIGATION = "xpath://label[@data-event-name='ui.mainmenu']";
        MY_LISTS_LINK = "css:a[data-event-name='menu.unStar']";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

}

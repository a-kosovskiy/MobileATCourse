package lib.ui.factories;

import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMyListsPageObject;
import lib.ui.mobileWeb.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else if (Platform.getInstance().isMobileWeb()) {
            return new MWMyListsPageObject(driver);
        } else
            return new AndroidMyListsPageObject(driver);
    }
}

package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String VIEW_LIST_LINK = "//*[@text = 'VIEW LIST']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void viewList() {
        this.waitForElementAndClick(By.xpath(VIEW_LIST_LINK),
                "Cannot press 'VIEW LIST' button",
                5);
    }
}

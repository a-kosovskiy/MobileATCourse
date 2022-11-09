package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String VIEW_LIST_LINK = "//*[@text = 'VIEW LIST']";
    private static final String NAVIGATE_UP_ID = "Navigate up";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void viewList() {
        this.waitForElementAndClick(By.xpath(VIEW_LIST_LINK),
                "Cannot press 'VIEW LIST' button",
                5);
    }

    public void navigateUp() {
        this.waitForElementAndClick(By.id(NAVIGATE_UP_ID),
                "Cannot find and click 'Navigate up' button",
                5);
    }

}

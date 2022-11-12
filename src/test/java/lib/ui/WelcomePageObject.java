package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickSkipButton() {
        this.waitForElementAndClick(SKIP_BUTTON,
                "Cannot find 'Skip' button",
                5);
    }
}

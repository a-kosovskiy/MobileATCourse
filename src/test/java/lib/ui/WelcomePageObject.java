package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickSkipButton() {
        this.waitForElementAndClick(SKIP_BUTTON,
                "Cannot find 'Skip' button",
                5);
    }
}

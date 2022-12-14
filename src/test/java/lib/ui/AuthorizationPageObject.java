package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String LOGIN_BUTTON = "xpath://a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";
    private static final String login = "AKosovskiy", password = "CS7qNaBY";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find 'Login' button");
        this.tryClickElementWithFewAttempts(LOGIN_BUTTON, "Cannot find and click 'Login' button", 5);
    }

    public void enterLoginData() {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login in the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password in the password input", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click Submit button", 5);
        this.waitForElementAndClick("xpath://a[text()='Mobile view']", "Cannot find 'Mobile view' button", 5);
    }
}

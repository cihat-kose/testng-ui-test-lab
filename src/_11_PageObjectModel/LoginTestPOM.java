package _11_PageObjectModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class LoginTestPOM extends BaseDriver {

    @Test(dataProvider = "credentials")
    public void loginTest(String username, String password) {

        LoginTestElements loginTestElements = new LoginTestElements();
        driver.get("https://www.saucedemo.com/");

        loginTestElements.usernameInput.sendKeys(username);
        loginTestElements.passwordInput.sendKeys(password);
        loginTestElements.loginButton.click();

        if (isUserValid(username) && password.equals("secret_sauce")) {
            waitForElementVisible(loginTestElements.burgerMenu);
            loginTestElements.burgerMenu.click();
            loginTestElements.logout.click();
        } else if (username.equals("locked_out_user") && password.equals("secret_sauce")) {
            verifyErrorMessage(loginTestElements);
        } else {
            verifyErrorMessage(loginTestElements);
        }
    }

    // Helper method to check valid usernames
    private boolean isUserValid(String username) {
        return username.equals("standard_user") ||
                username.equals("problem_user") ||
                username.equals("performance_glitch_user") ||
                username.equals("error_user") ||
                username.equals("visual_user");
    }

    // Helper method to verify error message appears
    private void verifyErrorMessage(LoginTestElements loginTestElements) {
        waitForElementVisible(loginTestElements.errorMessage);
        Assert.assertTrue(loginTestElements.errorMessage.isDisplayed(), "Error message was not displayed!");
        loginTestElements.errorCloseButton.click();
        loginTestElements.usernameInput.clear();
        loginTestElements.passwordInput.clear();
    }

    // Waits for a WebElement to become visible
    private void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Data provider for test credentials
    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"fake_user_01", "secret_sauce"},
                {"error_user", "fake_password_01"},
                {"performance_glitch_user", "secret_sauce"},
                {"fake_user_02", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "fake_password_01"},
                {"visual_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
        };
    }
}

package _10_DataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class LoginDataProviderTest extends BaseDriver {

    /**
         Task:
         Login Test using Data Provider

         1) Navigate to https://www.saucedemo.com/.
         2) Attempt to log in using valid and invalid usernames:
            - Accepted usernames:
              - standard_user
              - locked_out_user
              - problem_user
              - performance_glitch_user
              - error_user
              - visual_user
            - Password for all users: secret_sauce

         3) Verify error message for invalid credentials.
         4) For valid login, verify success and perform logout.
         5) Use TestNG DataProvider to supply multiple sets of credentials.
     */

    @Test(dataProvider = "loginCredentials")
    public void loginWithDataProvider(String username, String password) {
        // Step 1: Navigate to the login page
        driver.get("https://www.saucedemo.com/");

        // Step 2: Enter username and password
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Step 3: Validate login scenarios
        if ((username.equals("standard_user") || username.equals("problem_user")
                || username.equals("performance_glitch_user") || username.equals("error_user")
                || username.equals("visual_user")) && password.equals("secret_sauce")) {

            // Step 4: Successful login - perform logout
            WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
            burgerMenu.click();

            WebElement logout = driver.findElement(By.id("logout_sidebar_link"));
            logout.click();

        } else if (username.equals("locked_out_user") && password.equals("secret_sauce")) {
            // Step 5: Check error message for locked out user
            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed());

            WebElement errorCloseButton = driver.findElement(By.xpath("//h3[@data-test='error']/button"));
            errorCloseButton.click();

        } else {
            // Step 6: Check error message for invalid credentials
            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed());

            WebElement errorCloseButton = driver.findElement(By.xpath("//h3[@data-test='error']/button"));
            errorCloseButton.click();
        }
    }

    /**
     * Data Provider:
     * Supplies multiple sets of usernames and passwords for the login test.
     */
    @DataProvider(name = "loginCredentials")
    public Object[][] loginCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"fake_user_01", "secret_sauce"},
                {"error_user", "fake_password"},
                {"visual_user", "secret_sauce"},
                {"visual_user", "wrong_password"}
        };
    }
}

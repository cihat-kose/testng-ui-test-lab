package _07_XMLConfigurations.eCommerceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class EcommerceLoginTest extends BaseDriver {

    /**
         Task: User Login using XML Parameters and shared email from registration

         Scenario:
         1. Navigate to: http://tutorialsninja.com/demo/
         2. Click on "My Account" and then "Login".
         3. Fill in the login form:
            - Email is retrieved from the static variable in EcommerceRegisterTest.
            - Password is received via XML parameter.
         4. Submit the form.
         5. Verify that login was successful by asserting the presence of the "Logout" link.

         Parameters:
         - password: Password used during registration (from XML)
     */

    @Test()
    @Parameters("password")
    public void loginTest(String password) {
        driver.get("http://tutorialsninja.com/demo/");

        WebElement myAccountMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
        myAccountMenu.click();

        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));
        loginLink.click();

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
        emailInput.sendKeys(EcommerceRegisterTest.dynamicEmail);

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        loginButton.click();

        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        Assert.assertEquals(logoutLink.getText(), "Logout", "Login failed!");
    }
}

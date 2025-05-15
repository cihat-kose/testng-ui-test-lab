package _07_XMLConfigurations.eCommerceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class EcommerceRegisterTest extends BaseDriver {

    /**
         Task: User Registration with XML Parameters

         Scenario:
         1. Navigate to: http://tutorialsninja.com/demo/
         2. Click on "My Account" and then "Register".
         3. Fill in the registration form:
            - First Name, Last Name, Telephone are received via XML parameters.
            - Email is dynamically generated to ensure uniqueness.
            - Password is also received via XML.
         4. Accept the Privacy Policy and click "Continue".
         5. Verify successful registration by asserting the message: "Your Account Has Been Created!"
         6. Log out of the account.

         Parameters:
         - firstName: User's first name (from XML)
         - lastName: User's last name (from XML)
         - telephone: User's phone number (from XML)
         - password: Password (from XML)
     */

    public static String dynamicEmail;

    @Test
    @Parameters({"firstName", "lastName", "telephone", "password"})
    public void registerTest(String firstName, String lastName, String telephone, String password) {
        driver.get("http://tutorialsninja.com/demo/");

        dynamicEmail = "testuser_" + System.currentTimeMillis() + "@example.com";

        WebElement myAccountMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
        myAccountMenu.click();

        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));
        registerLink.click();

        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys(lastName);

        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(dynamicEmail);

        WebElement telephoneInput = driver.findElement(By.id("input-telephone"));
        telephoneInput.sendKeys(telephone);

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(password);

        WebElement confirmPasswordInput = driver.findElement(By.id("input-confirm"));
        confirmPasswordInput.sendKeys(password);

        WebElement privacyPolicyCheckbox = driver.findElement(By.name("agree"));
        privacyPolicyCheckbox.click();

        WebElement continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        continueButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#content h1")));
        Assert.assertTrue(successMessage.getText().contains("Your Account Has Been Created!"), "Registration failed!");

        WebElement myAccountDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
        myAccountDropdown.click();

        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        logoutLink.click();
    }
}

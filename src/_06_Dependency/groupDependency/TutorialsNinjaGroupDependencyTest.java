package _06_Dependency.groupDependency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

import java.util.Random;

public class TutorialsNinjaGroupDependencyTest extends BaseDriver {

    /**
         Task: User Registration, Login, and Account Update Operations (Group Dependency)

         Scenario 1: "auth" Group – Registration and Login
         1. Go to the homepage: http://tutorialsninja.com/demo/
         2. Click on the "My Account" menu and select "Register".
         3. Fill out the registration form:
            - First Name, Last Name, Email, Telephone, Password, Confirm Password.
         4. Check the "Privacy Policy" box and click the "Continue" button.
         5. Verify that registration is successful (check the success message).
         6. Logout to verify the session ends.

         Scenario 2: "auth" Group – Login
         1. Go to the homepage and click "My Account" → "Login".
         2. Login using the credentials created during registration.
         3. Verify that login was successful (check for presence of the Logout link).

         Scenario 3: "accountOperations" Group – Update Account and Logout
         1. Update the account information (change First Name to "UpdatedName").
         2. Verify that the update was successful.
         3. Logout and confirm that the session has ended.
     */

    Random random = new Random();

    // Generate random email and password for user registration
    String generatedEmail = "user_" + random.nextInt(10000) + "@testmail.com";
    String generatedPassword = "password" + random.nextInt(10000);

    @Test(groups = "auth")
    public void registerTest() {
        driver.get("http://tutorialsninja.com/demo/");

        // Open "My Account" → "Register"
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        // Fill out the registration form
        driver.findElement(By.id("input-firstname")).sendKeys("Kerem");
        driver.findElement(By.id("input-lastname")).sendKeys("Mert");
        driver.findElement(By.id("input-email")).sendKeys(generatedEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("15551234567");
        driver.findElement(By.id("input-password")).sendKeys(generatedPassword);
        driver.findElement(By.id("input-confirm")).sendKeys(generatedPassword);
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Verify registration success
        wait.until(ExpectedConditions.titleIs("Your Account Has Been Created!"));
        WebElement successMessage = driver.findElement(By.cssSelector("div#content h1"));
        Assert.assertTrue(successMessage.getText().contains("Your Account Has Been Created!"), "Registration failed!");

        // Logout after registration
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test(dependsOnMethods = "registerTest", groups = "auth")
    public void loginTest() {
        driver.get("http://tutorialsninja.com/demo/");

        // Open "My Account" → "Login"
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        // Login with previously created credentials
        driver.findElement(By.id("input-email")).sendKeys(generatedEmail);
        driver.findElement(By.id("input-password")).sendKeys(generatedPassword);
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Verify login success by checking for Logout link
        wait.until(ExpectedConditions.titleIs("My Account"));
        WebElement logoutText = driver.findElement(By.xpath("//*[@id='column-right']/div/a[13]"));
        Assert.assertEquals(logoutText.getText(), "Logout", "Login failed!");
    }

    @Test(dependsOnGroups = "auth", groups = "accountOperations")
    public void updateAccountTest() {
        // Open Edit Account page
        WebElement accountDetailsLink = driver.findElement(By.linkText("Edit Account"));
        accountDetailsLink.click();

        // Update first name
        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.clear();
        firstNameInput.sendKeys("UpdatedName");

        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Verify account update success message
        WebElement successMessage = driver.findElement(By.cssSelector("div.alert.alert-success"));
        Assert.assertTrue(successMessage.getText().contains("Your account has been successfully updated."));
    }

    @Test(dependsOnMethods = "updateAccountTest", groups = "accountOperations")
    public void logoutTest() {
        // Open "My Account" → Logout
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();

        // Verify successful logout
        WebElement logoutMessage = driver.findElement(By.cssSelector("div#content h1"));
        Assert.assertTrue(logoutMessage.getText().contains("Account Logout"));
    }
}

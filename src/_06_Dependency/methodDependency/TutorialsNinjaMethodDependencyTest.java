package _06_Dependency.methodDependency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

import java.util.Random;

public class TutorialsNinjaMethodDependencyTest extends BaseDriver {

    /**
         Task: User Registration and Login (Method Dependency)

         Scenario 1: User Registration
         1. Navigate to the homepage: http://tutorialsninja.com/demo/
         2. Click the "My Account" menu.
         3. Select the "Register" link from the dropdown menu.
         4. Fill in the registration form with:
            - First Name, Last Name, Email, Telephone, Password, Confirm Password
         5. Check the "Privacy Policy" box and click the "Continue" button.
         6. Verify that registration is successful (check success message).
         7. Perform logout to end the session.

         Scenario 2: User Login
         1. Go back to the homepage.
         2. Click "My Account" → "Login".
         3. Log in using the email and password used during registration.
         4. Verify that login was successful (check if "Logout" link is visible).
     */

    Random random = new Random();

    // Generate a random email and password for registration
    String generatedEmail = "user_" + random.nextInt(10000) + "@testmail.com";
    String generatedPassword = "password" + random.nextInt(10000);

    @Test
    public void registerTest() {
        // Step 1: Go to homepage
        driver.get("http://tutorialsninja.com/demo/");

        // Step 2: Click "My Account" → "Register"
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        // Step 4: Fill in registration form
        driver.findElement(By.id("input-firstname")).sendKeys("Kerem");
        driver.findElement(By.id("input-lastname")).sendKeys("Mert");
        driver.findElement(By.id("input-email")).sendKeys(generatedEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("15551234567");
        driver.findElement(By.id("input-password")).sendKeys(generatedPassword);
        driver.findElement(By.id("input-confirm")).sendKeys(generatedPassword);

        // Step 5: Accept privacy policy and submit form
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Step 6: Verify successful registration
        wait.until(ExpectedConditions.titleIs("Your Account Has Been Created!"));
        WebElement successMessage = driver.findElement(By.cssSelector("div#content h1"));
        Assert.assertTrue(successMessage.getText().contains("Your Account Has Been Created!"),
                "Registration failed!");

        // Step 7: Log out
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Logout")).click();
    }

    /**
     * This method depends on successful user registration.
     * It runs only if "registerTest" completes without errors.
     */
    @Test(dependsOnMethods = "registerTest")
    public void loginTest() {
        // Step 1: Go back to homepage
        driver.get("http://tutorialsninja.com/demo/");

        // Step 2: Click "My Account" → "Login"
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        // Step 3: Enter email and password used during registration
        driver.findElement(By.id("input-email")).sendKeys(generatedEmail);
        driver.findElement(By.id("input-password")).sendKeys(generatedPassword);
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Step 4: Verify login success
        wait.until(ExpectedConditions.titleIs("My Account"));
        WebElement logoutText = driver.findElement(By.xpath("//*[@id='column-right']/div/a[13]"));
        Assert.assertEquals(logoutText.getText(), "Logout", "Login failed!");
    }
}

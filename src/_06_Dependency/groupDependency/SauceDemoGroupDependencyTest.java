package _06_Dependency.groupDependency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class SauceDemoGroupDependencyTest extends BaseDriver {

    /**
        Task:
        User Login, Add Product to Cart, and Logout Operations

        Scenario 1: User Login
        1. Navigate to the Swag Labs homepage: https://www.saucedemo.com/
        2. Fill in the Username and Password fields on the login form:
        - Username: "standard_user"
        - Password: "secret_sauce"
        3. Click the "Login" button.
        4. Verify successful login:
        - The "Swag Labs" title should be visible on the page.

        Scenario 2: Add Product to Cart
        1. After logging in, add a product (e.g., "Sauce Labs Backpack") to the cart.
        2. Click the cart icon.
        3. Verify that the product appears in the cart.

        Scenario 3: Logout
        1. After logging in, logout using the menu.
        2. Verify successful logout:
        - The login button should be visible again.
     */

    // Valid login credentials for the Swag Labs website
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";

    @Test(groups = "auth")
    public void loginTest() {
        // Step 1: Navigate to Swag Labs homepage
        driver.get("https://www.saucedemo.com/");

        // Step 2: Fill in username and password fields
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(validUsername);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(validPassword);

        // Step 3: Click the login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Step 4: Verify successful login
        wait.until(ExpectedConditions.titleIs("Swag Labs"));
        WebElement productsTitle = driver.findElement(By.className("title"));
        Assert.assertTrue(productsTitle.isDisplayed(), "Login failed!");
    }

    /**
         This method depends on a successful login.
         It will only run if "loginTest" completes successfully.
         Ensures that test steps follow a valid and logical sequence.
     */
    @Test(dependsOnMethods = "loginTest", groups = "cartOperations")
    public void addProductToCartTest() {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();

        // Verify product is in the cart
        WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
        Assert.assertTrue(cartItem.getText().contains("Sauce Labs Backpack"), "Product was not added to the cart!");
    }

    /**
         This method runs after the product has been successfully added to the cart.
         It depends on "addProductToCartTest".
     */
    @Test(dependsOnMethods = "addProductToCartTest", groups = "cartOperations")
    public void checkoutTest() {
        // Step 1: Proceed to checkout
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        // Step 2: Enter required purchase information
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        firstNameInput.sendKeys("Test");

        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        lastNameInput.sendKeys("User");

        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
        postalCodeInput.sendKeys("12345");

        // Click continue
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        // Click finish to complete the purchase
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        // Verify purchase was successful
        WebElement checkoutCompleteMessage = driver.findElement(By.className("complete-header"));
        Assert.assertTrue(checkoutCompleteMessage.getText().contains("Thank you for your order!"), "Checkout failed!");
    }

    /**
         This method runs only after all tests in the "cartOperations" group have completed successfully.
         It ensures logout only happens after a full cart workflow is done.
     */
    @Test(dependsOnGroups = "cartOperations", groups = "accountOperations")
    public void logoutTest() {
        // Open the menu and logout
        WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
        burgerMenu.click();

        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
        logoutLink.click();

        // Verify successful logout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginButton.isDisplayed(), "Logout failed!");
    }
}

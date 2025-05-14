package _06_Dependency.methodDependency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class SauceDemoMethodDependencyTest extends BaseDriver {

    /**
         Task:
         User Login, Add Product to Cart, and Logout Operations (Method Dependency)

         Scenario 1: User Login
         1. Navigate to the Swag Labs homepage: https://www.saucedemo.com/
         2. Fill in the Username and Password fields:
            - Username: "standard_user"
            - Password: "secret_sauce"
         3. Click the "Login" button.
         4. Verify that the login was successful by checking the page title.

         Scenario 2: Add Product to Cart
         1. After logging in, add a product (e.g., "Sauce Labs Backpack") to the cart.
         2. Click the cart icon.
         3. Verify that the product was successfully added to the cart.

         Scenario 3: Logout
         1. After completing the cart operation, perform a logout via the menu.
         2. Verify that logout was successful by checking for the login button.
     */

    // Valid login credentials for the Swag Labs website
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";

    @Test
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

        // Step 4: Verify login was successful by checking page title
        wait.until(ExpectedConditions.titleIs("Swag Labs"));
        WebElement productsTitle = driver.findElement(By.className("title"));
        Assert.assertTrue(productsTitle.isDisplayed(), "Login failed!");
    }

    /**
     * This method depends on a successful login.
     * It will only execute if "loginTest" passes.
     * Ensures proper sequence of test steps.
     */
    @Test(dependsOnMethods = "loginTest")
    public void addProductToCartTest() {
        // Step: Add product to cart
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        // Click the cart icon
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();

        // Verify that the product is in the cart
        WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
        Assert.assertTrue(cartItem.getText().contains("Sauce Labs Backpack"), "Product was not added to the cart!");
    }

    /**
     * This method runs after a successful cart operation.
     * It depends on "addProductToCartTest" to enforce sequential execution.
     */
    @Test(dependsOnMethods = "addProductToCartTest")
    public void logoutTest() {
        // Step: Perform logout via menu
        WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
        burgerMenu.click();

        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
        logoutLink.click();

        // Verify logout was successful by checking login button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginButton.isDisplayed(), "Logout failed!");
    }
}

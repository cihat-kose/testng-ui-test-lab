package _09_GroupsTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdvancedGroupsTest {

    // Basic system-level check to ensure the application is up and responsive
    // Part of the "sanity" group
    @Test(groups = "sanity")
    public void basicSanityCheck() {
        System.out.println("Sanity Test: Performing basic validation.");
        Assert.assertTrue(true, "Sanity test failed.");
    }

    // Tests whether the login feature works with valid user credentials
    // Belongs to the "functional" group
    @Test(groups = "functional")
    public void loginFunctionalityTest() {
        System.out.println("Functional Test: Testing login functionality.");
        Assert.assertTrue(true, "Login functionality test failed.");
    }

    // Verifies that a product can be added to the cart successfully
    // Belongs to the "functional" group
    @Test(groups = "functional")
    public void addProductToCartTest() {
        System.out.println("Functional Test: Testing product add to cart.");
        Assert.assertTrue(true, "Add to cart test failed.");
    }

    // Tests the checkout process after the system passes sanity check
    // Part of the "regression" group and depends on "sanity"
    @Test(groups = "regression", dependsOnGroups = "sanity")
    public void checkoutTest() {
        System.out.println("Regression Test: Testing checkout process.");
        Assert.assertTrue(true, "Checkout test failed.");
    }

    // Verifies that the order history is displayed properly
    // Part of "regression", depends on "sanity"
    @Test(groups = "regression", dependsOnGroups = "sanity")
    public void orderHistoryTest() {
        System.out.println("Regression Test: Testing order history view.");
        Assert.assertTrue(true, "Order history test failed.");
    }

    // Runs a full system integration test after all sanity and functional tests pass
    // Part of "regression", depends on "sanity" and "functional"
    @Test(groups = "regression", dependsOnGroups = {"sanity", "functional"})
    public void fullSystemTest() {
        System.out.println("Regression Test: Running full system test.");
        Assert.assertTrue(true, "Full system test failed.");
    }
}

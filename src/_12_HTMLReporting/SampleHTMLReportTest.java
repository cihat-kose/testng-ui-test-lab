package _12_HTMLReporting;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleHTMLReportTest {

    @Test
    public void testLoginSuccess() {
        System.out.println("Login test is running...");

        // Test scenario for successful login
        String expectedTitle = "My Account";
        String actualTitle = "My Account";  // Simulated post-login title
        Assert.assertEquals(actualTitle, expectedTitle, "Post-login title does not match!");
    }

    @Test
    public void testLoginFailure() {
        System.out.println("Login failure test is running...");

        // Test scenario for failed login
        String expectedErrorMessage = "Invalid credentials";
        String actualErrorMessage = "Invalid credentials";  // Simulated error message after failed login
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is incorrect for failed login!");
    }

    @Test
    public void testLogout() {
        System.out.println("Logout test is running...");

        // Test scenario for successful logout
        boolean isLoggedOut = true;  // Simulated logout status
        Assert.assertTrue(isLoggedOut, "User could not log out!");
    }
}

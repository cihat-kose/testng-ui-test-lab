package _05_Assertions;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Demonstrates soft assertions in TestNG.
 * Multiple assertions can be performed, and errors will be reported at the end using assertAll().
 */

public class TestNGSoftAssertions {

    @Test
    public void softAssertTest() {
        System.out.println("Soft Assert Test started...");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals("Google", "Facebook", "Titles do not match!");
        softAssert.assertTrue("Google".contains("Google"), "Title does not contain 'Google'!");
        softAssert.assertEquals(5, 10, "Numbers are not equal!");
        softAssert.assertTrue(false, "User is not logged in!");

        System.out.println("This line runs even if some soft assertions fail.");

        softAssert.assertAll(); // Errors are thrown here.
    }

    @Test
    public void softAssertWithoutAssertAll() {
        System.out.println("Soft Assert Without assertAll() started...");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals("Google", "Facebook", "Titles do not match!");
        softAssert.assertEquals(5, 10, "Numbers are not equal!");

        System.out.println("❗ Soft assertion results will NOT be reported because assertAll() is not called.");
    }

    @Test
    public void combinedHardAndSoftAssertTest() {
        System.out.println("Combined Hard and Soft Assert Test started...");

        SoftAssert softAssert = new SoftAssert();

        // Hard assertion
        String expectedUrl = "https://www.example.com";
        String actualUrl = "https://www.example.com";
        org.testng.Assert.assertEquals(actualUrl, expectedUrl, "URLs do not match!");

        // Soft assertions
        softAssert.assertEquals("Sample Title", "Example Title", "Titles do not match!");
        softAssert.assertEquals(85, 100, "Counter value does not match!");

        System.out.println("Soft assert results will be evaluated at the end.");

        softAssert.assertAll();
    }
}

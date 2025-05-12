package _05_Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Demonstrates hard assertions in TestNG.
 * A failed assertion will stop the test execution immediately.
 */

public class TestNGHardAssertions {

    @Test
    public void hardAssertTest() {
        System.out.println("Hard Assert Test started...");

        String expectedTitle = "Google";
        String actualTitle = "Google";

        Assert.assertEquals(actualTitle, expectedTitle, "Titles do not match!");
        Assert.assertTrue(actualTitle.contains("Google"), "Title does not contain 'Google'!");

        System.out.println("✅ Hard Assert Test passed.");
    }

    @Test
    public void multipleHardAssertTest() {
        System.out.println("Multiple Hard Asserts Test started...");

        int expectedNumber = 5;
        int actualNumber = 5;
        Assert.assertEquals(actualNumber, expectedNumber, "Numbers are not equal!");

        String expectedText = "Hello World";
        String actualText = "Hello Selenium";
        Assert.assertEquals(actualText, expectedText, "Texts are not equal!");

        System.out.println("❌ This line will not be reached if an assertion fails above.");
    }
}

package _05_Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Demonstrates advanced TestNG assertions:
 * - assertNull / assertNotNull: checks presence of value
 * - assertNotEquals: checks inequality of content
 */

public class TestNGAdvancedAssertions {

    @Test(priority = 0)
    public void assertNullTest() {
        String value = null;
        Assert.assertNull(value, "Value should be null.");
        System.out.println("✅ assertNullTest passed.");
    }

    @Test(priority = 1)
    public void assertNotNullTest() {
        String value = "Selenium";
        Assert.assertNotNull(value, "Value should not be null.");
        System.out.println("✅ assertNotNullTest passed.");
    }

    @Test(priority = 2)
    public void assertNotEqualsTest() {
        String actual = "Chrome";
        String expected = "Firefox";

        Assert.assertNotEquals(actual, expected, "Values should not be equal.");
        System.out.println("✅ assertNotEqualsTest passed.");
    }
}

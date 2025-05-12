package _05_Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Demonstrates basic TestNG assertions:
 * - assertEquals: compares content
 * - assertSame: compares memory reference
 * - assertTrue / assertFalse: checks boolean conditions
 */

public class TestNGBasicAssertions {

    @Test(priority = 0)
    public void assertEqualsWithStringLiteralsTest() {
        String actual = "Java is a programming language.";
        String expected = "Java is a programming language.";

        Assert.assertSame(actual, expected, "Memory references are not the same.");
        Assert.assertEquals(actual, expected, "Text contents are not equal.");

        System.out.println("✅ assertEqualsWithStringLiteralsTest passed.");
    }

    @Test(priority = 1)
    public void assertSameWithStringLiteralsTest() {
        String greeting1 = "Hello!";
        String greeting2 = "Hello!";

        Assert.assertSame(greeting1, greeting2, "Memory references are not the same.");
        System.out.println("✅ assertSameWithStringLiteralsTest passed.");
    }

    @Test(priority = 2)
    public void assertEqualsWithIdenticalTextTest() {
        String language1 = "Java";
        String language2 = "Java";

        Assert.assertEquals(language1, language2, "Text contents are not equal.");
        System.out.println("✅ assertEqualsWithIdenticalTextTest passed.");
    }

    @Test(priority = 3)
    public void assertSameWithStringPoolTest() {
        String browser1 = "Selenium";
        String browser2 = "Selenium";

        Assert.assertSame(browser1, browser2, "Memory references are not the same.");
        System.out.println("✅ assertSameWithStringPoolTest passed.");
    }

    @Test(priority = 4)
    public void assertTrueConditionTest() {
        int actual = 10;
        int expected = 5;

        Assert.assertTrue(actual > expected, "Expected actual to be greater than expected.");
        System.out.println("✅ assertTrueConditionTest passed.");
    }

    @Test(priority = 5)
    public void assertFalseConditionTest() {
        String text = "Selenium";

        Assert.assertFalse(text.isEmpty(), "Expected text not to be empty.");
        System.out.println("✅ assertFalseConditionTest passed.");
    }
}

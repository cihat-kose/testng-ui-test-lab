package _04_EnableDisableTests;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class EnableDisableTests {

    // This method will NOT be executed because enabled = false.
    @Test(enabled = false)
    public void testCaseDisabled() {
        String str1 = "TestNG";
        String str2 = "Selenium";
        Assert.assertEquals(str1, str2, "Strings are not equal! TestNG and Selenium are different.");
        System.out.println("This method was not executed because it is disabled via enabled = false.");
    }

    // This test will be executed because enabled = true by default.
    @Test
    public void testCaseEnabled() {
        String str1 = "Selenium";
        String str2 = "Selenium";
        Assert.assertEquals(str1, str2, "Strings should be equal! Both are Selenium.");
        System.out.println("Enabled test method executed.");
    }

    // Another test that will be executed.
    @Test(priority = 1)
    public void testCaseOne() {
        String str1 = "Java";
        String str2 = "Java";
        Assert.assertEquals(str1, str2, "Strings should be equal! Both are Java.");
        System.out.println("Test case one executed successfully.");
    }

    // This test is disabled using enabled = false.
    @Test(priority = 2, enabled = false)
    public void testCaseTwo() {
        String str1 = "TestNG";
        String str2 = "JUnit";
        Assert.assertEquals(str1, str2, "Strings are not equal! TestNG and JUnit are different.");
        System.out.println("This method was not executed because it is disabled via enabled = false.");
    }

    // A regular test method that runs normally.
    @Test(priority = 3)
    public void testCaseThree() {
        String str1 = "Selenium WebDriver";
        String str2 = "Selenium WebDriver";
        Assert.assertEquals(str1, str2, "Strings should be equal! Both are Selenium WebDriver.");
        System.out.println("Test case three executed successfully.");
    }

    // This method is skipped using the @Ignore annotation.
    @Ignore
    @Test
    public void testCaseIgnored() {
        String str1 = "TestNG";
        String str2 = "Selenium";
        Assert.assertEquals(str1, str2, "Strings are not equal! TestNG and Selenium are different.");
        System.out.println("This method was not executed because it was disabled using @Ignore.");
    }
}

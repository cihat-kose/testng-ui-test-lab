package _03_Annotations;

import org.testng.annotations.*;

public class TestNGAnnotationsExample {

    /**
       @BeforeSuite
           @BeforeTest
               @BeforeGroups
                   @BeforeClass
                       @BeforeMethod
                           @Test
                           @Test
                       @AfterMethod
                   @AfterClass
               @AfterGroups
           @AfterTest
       @AfterSuite
     */

    // Runs once before the entire test suite
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite executed.");
    }

    // Runs before the tests defined in testng.xml
    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test executed.");
    }

    // Runs once before any method in the current class
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class executed.");
    }

    // Runs before each test method
    // Common place for setup/preconditions
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method executed.");
    }

    // First test method
    @Test
    public void testMethodOne() {
        System.out.println("Test Method One executed.");
    }

    // Second test method
    @Test
    public void testMethodTwo() {
        System.out.println("Test Method Two executed.");
    }

    // Runs after each test method
    // Typically used for cleanup
    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method executed.");
    }

    // Runs once after all test methods in the current class
    @AfterClass
    public void afterClass() {
        System.out.println("After Class executed.");
    }

    // Runs after all tests defined in testng.xml
    @AfterTest
    public void afterTest() {
        System.out.println("After Test executed.");
    }

    // Runs once after the entire test suite
    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite executed.");
    }
}

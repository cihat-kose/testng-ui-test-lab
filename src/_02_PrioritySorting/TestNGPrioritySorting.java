package _02_PrioritySorting;

import org.testng.annotations.Test;

public class TestNGPrioritySorting {

    // This method is defined with priority = 1.
    // It will be executed before other priority-assigned methods.
    @Test(priority = 1)
    public void testMethodOne() {
        System.out.println("TestNG Method One - Priority 1");
    }

    // This method has priority = 2.
    // It will run after the method with priority 1.
    @Test(priority = 2)
    public void testMethodTwo() {
        System.out.println("TestNG Method Two - Priority 2");
    }

    // This method has no assigned priority.
    // TestNG will execute it based on alphabetical order among non-priority methods.
    @Test
    public void testMethodThree() {
        System.out.println("TestNG Method Three - No Priority");
    }

    // This method is defined with priority = 3.
    // It will be executed after the other priority-assigned methods.
    @Test(priority = 3)
    public void testMethodFour() {
        System.out.println("TestNG Method Four - Priority 3");
    }

    // This method also has no assigned priority.
    // It will be sorted alphabetically among non-priority methods.
    @Test
    public void testMethodFive() {
        System.out.println("TestNG Method Five - No Priority");
    }
}

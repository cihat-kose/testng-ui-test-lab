package _05_Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.BaseDriver;

public class DuckDuckGoHardAndSoftAssertTest extends BaseDriver {

    /**
        Task:
        Demonstrate the use of Hard and Soft Assertions on DuckDuckGo by performing a search for "TestNG".

        HardAssert Test:
        1. Go to "https://duckduckgo.com/"
        2. Verify that the current URL contains "duckduckgo.com"
        3. Verify that the page title contains "DuckDuckGo"
        4. Type "TestNG" into the search input and press Enter
        5. Verify that the first visible result title is "TestNG"

        SoftAssert Test:
        - Repeat the same steps as above.
        - Additionally, include one intentional failing assertion (checking if title contains "Google")
        to demonstrate that SoftAssert allows the test to continue even when an assertion fails.
     */



    @Test
    public void hardAssertTesting() {
        driver.get("https://duckduckgo.com");

        // 1. Verify URL contains "duckduckgo.com"
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("duckduckgo.com"), "URL verification failed");

        // 2. Verify page title contains "duckduckgo"
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("duckduckgo"), "Title verification failed");

        // 3. Perform search
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys("TestNG" + Keys.ENTER);

        // 4. Capture and verify first result title
        WebElement firstTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h2.Ee2e63EzQ9F3xq9wsGDY")));

        Assert.assertEquals(firstTitle.getText().trim(), "TestNG", "First result title does not match expected value!");
    }

    @Test
    public void softAssertTesting() {
        driver.get("https://duckduckgo.com");

        SoftAssert softAssert = new SoftAssert();

        // 1. Verify URL
        softAssert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("duckduckgo.com"), "URL verification failed");

        // 2. Verify title
        softAssert.assertTrue(driver.getTitle().toLowerCase().contains("duckduckgo"), "Title verification failed");

        // 3. Intentional failure for demo: check if title contains an incorrect value
        // This check is intentionally incorrect to demonstrate that SoftAssert does not stop the test.
        // The test will continue even though this assertion fails.
        softAssert.assertTrue(driver.getTitle().contains("Google"), "Intentional failure: Title should not contain 'Google'");


        // 4. Perform search
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys("TestNG" + Keys.ENTER);

        // 5. Capture and verify first result title
        WebElement firstTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h2.Ee2e63EzQ9F3xq9wsGDY")));

        softAssert.assertEquals(firstTitle.getText().trim(), "TestNG", "First result title does not match expected value!");

        System.out.println("All SoftAssert checks completed. Results will now be evaluated.");
        softAssert.assertAll();
    }
}

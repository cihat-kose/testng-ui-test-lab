package _08_CrossBrowserTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriverParameter;

public class CrossBrowserTest extends BaseDriverParameter {

    /**
         Task: Cross Browser Testing - DuckDuckGo Search Test

         Steps:
         1. Navigate to DuckDuckGo homepage.
         2. Verify that the page title contains "DuckDuckGo".
         3. Type "Cross Browser Testing" into the search box and submit.
         4. Verify that the search results are displayed.

         This test is designed to run across multiple browsers (e.g., Chrome, Firefox, Edge)
         using a parameterized driver setup inherited from BaseDriverParameter.
     */

    @Test
    public void crossBrowserTest() {
        // Step 1: Navigate to DuckDuckGo
        driver.get("https://www.duckduckgo.com");

        // Step 2: Verify page title contains "DuckDuckGo"
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("DuckDuckGo"), "Page title does not contain 'DuckDuckGo'!");

        // Step 3: Perform search
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys("Cross Browser Testing");
        searchInput.submit();

        // Step 4: Verify search results are displayed
        WebElement resultsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".react-results--main")));
        Assert.assertTrue(resultsContainer.isDisplayed(), "Search results are not visible!");
    }
}

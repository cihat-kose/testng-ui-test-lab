package _07_XMLConfigurations.duckduckgoTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class DuckDuckGoSearchTest extends BaseDriver {

    /**
         Task:
         Perform a search operation on DuckDuckGo using XML parameters

         Steps:
         1. Navigate to the URL provided via XML (e.g., https://www.duckduckgo.com).
         2. Verify that the page title contains "DuckDuckGo".
         3. Type the search term (also from XML) into the search box and perform the search.
         4. Confirm that search results are displayed.
     */

    @Test
    @Parameters({"url", "searchTerm"})
    public void duckDuckGoSearch(String url, String searchTerm) {
        // Step 1: Go to the URL from XML
        driver.get(url);

        // Step 2: Check if title contains "DuckDuckGo"
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("DuckDuckGo"), "Page title does not contain 'DuckDuckGo'!");

        // Step 3: Search for the term from XML
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys(searchTerm);
        searchInput.submit();

        // Step 4: Confirm that results are visible
        WebElement resultsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".react-results--main")));
        Assert.assertTrue(resultsContainer.isDisplayed(), "Search results are not visible!");
    }
}

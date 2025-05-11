package _05_Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.BaseDriver;

public class DemoblazeHardAndSoftAssertTest extends BaseDriver {

    /**
        Task:
        Write tests that demonstrate the difference between Hard and Soft Assertions
        using the website: https://www.demoblaze.com/

        Steps:
        1- Navigate to the site.
        2- Verify that the page title contains "STORE".
        3- Click on the "Phones" category.
        4- Verify that the name of the first product contains "Samsung galaxy s6".
     */

    @Test
    public void hardAssertTest() {
        driver.get("https://www.demoblaze.com/");

        // Hard Assert: Check if the title contains "STORE"
        Assert.assertTrue(driver.getTitle().contains("STORE"), "Page title does not contain 'STORE'!");

        // Click on the "Phones" category
        WebElement phonesCategory = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones")));
        phonesCategory.click();

        // Check if the first product contains "Samsung"
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid .card-title")));
        String productName = firstProduct.getText().toLowerCase();
        Assert.assertTrue(productName.contains("samsung"), "First product does not contain 'Samsung'!");
    }

    @Test
    public void softAssertTest() {
        driver.get("https://www.demoblaze.com/");

        SoftAssert softAssert = new SoftAssert();

        // Soft Assert: Check if the title contains "STORE"
        softAssert.assertTrue(driver.getTitle().contains("STORE"), "Page title does not contain 'STORE'!");

        // Click on the "Phones" category
        WebElement phonesCategory = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones")));
        phonesCategory.click();

        // Check if the first product contains "Samsung"
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid .card-title")));
        String productName = firstProduct.getText().toLowerCase();
        softAssert.assertTrue(productName.contains("samsung"), "First product does not contain 'Samsung'!");

        // Intentionally failing check for demonstration purposes
        softAssert.assertTrue(productName.contains("iPhone"), "First product does not contain 'iPhone'!");

        System.out.println("Test continued with SoftAssert; failures will be reported after assertAll().");
        softAssert.assertAll();
    }
}

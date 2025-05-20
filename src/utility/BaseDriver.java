package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseDriver {

    public static final Logger logger4j2 = LogManager.getLogger();
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void initialOperations() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Set timeouts
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterClass
    public static void finishingOperations() {
        Tools.wait(3);
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        logger4j2.info("Test method has started.");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        logger4j2.info(result.getName() + " test method has finished. --> " +
                (result.getStatus() == ITestResult.SUCCESS ? "Passed" : "Failed"));
    }
}

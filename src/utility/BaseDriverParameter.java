package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseDriverParameter {

    public static final Logger logger4j2 = LogManager.getLogger();
    public static WebDriverWait wait;
    public WebDriver driver;

    @BeforeClass
    @Parameters("browserType")
    public void initialOperations(String browserType) {

        switch (browserType.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterClass
    public void finishingOperations() {
        // Educational purpose: waiting to observe browser behavior before closing
        Tools.wait(5);
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

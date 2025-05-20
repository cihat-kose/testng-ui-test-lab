package _13_Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LoggingExample {

    // Log4j2 Logger instance
    private static final Logger logger = LogManager.getLogger(LoggingExample.class);

    @Test
    public void logTest() {
        // Information message
        logger.info("Test started: logTest()");

        try {
            // Simulate a test operation (e.g., addition of two numbers)
            int result = 5 + 3;
            logger.debug("Performing addition: 5 + 3 = " + result);

            // Info on successful execution
            logger.info("Test succeeded: Result = " + result);
        } catch (Exception e) {
            // Error logging in case of exception
            logger.error("Test failed", e);
        }

        logger.info("Test ended: logTest()");
    }
}

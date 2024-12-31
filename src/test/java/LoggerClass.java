import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerClass {
    private static final Logger logger = LoggerFactory.getLogger(LoggerClass.class);

    public void myMethod() {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
    }
}
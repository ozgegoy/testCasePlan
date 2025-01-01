package Base;

import Config.ConfigReader;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import java.net.URL;
import java.util.List;


public class Hooks {
    private static final String baseUrl = ConfigReader.getProperty("baseUrl");
    private static final int THREAD_COUNT = Integer.parseInt(ConfigReader.getProperty("thread"));
    private static final AtomicInteger threadIndexCounter = new AtomicInteger(0);
    private static final List<WebDriver> drivers = new CopyOnWriteArrayList<>();
    private static final Boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
    private static final Boolean isRemote = Boolean.parseBoolean(ConfigReader.getProperty("remote"));
    private static final Boolean gitlab = Boolean.parseBoolean(ConfigReader.getProperty("gitlab"));
    private final static String browser = ConfigReader.getProperty("browser");

    static {
        for (int i = 0; i < THREAD_COUNT; i++) {
            drivers.add(null);
        }
    }

    private static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        if (headless) {
            options.addArguments("--headless=new");
        }

        if (!isRemote) {
            WebDriverManager.chromedriver().timeout(11).setup();
            return new ChromeDriver(options);
        } else {
            try {
                String url = gitlab ? "http://gitlab-selenium-server:4545/wd/hub" : "http://localhost:4444/wd/hub";
                URL remote = new URL(url);
                WebDriverManager.chromedriver().timeout(11).setup();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(browser);
                return new RemoteWebDriver(remote, capabilities);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Before
    public void beforeSteps() {
        runnerBeforeSteps(browser);
    }

    @After
    public void afterSteps() {
        runnerAfterSteps();
    }

    public static void runnerAfterSteps() {
        int runnerIndex = getCurrentDriverIndex();
        WebDriver driver = getDriver(runnerIndex);
        if (driver != null) {
            driver.quit();
            drivers.set(runnerIndex, null);
            System.out.println("Driver quit for thread: " + runnerIndex);
        }
    }

    public static void runnerBeforeSteps(String browser) {
        int runnerIndex = getCurrentDriverIndex();
        WebDriver driver = getDriver(runnerIndex);
        if (driver == null) {
            drivers.set(runnerIndex, createDriver());
        }
        getDriver(runnerIndex).get(baseUrl);
        System.out.println("Driver initialized for thread: " + runnerIndex);
    }

    public synchronized static WebDriver getDriver() {
        int runnerIndex = getCurrentDriverIndex();
        return getDriver(runnerIndex);
    }

    public synchronized static WebDriver getDriver(int index) {
        return drivers.get(index);
    }

    private static int getCurrentDriverIndex() {
        return threadIndexCounter.getAndIncrement() % THREAD_COUNT;
    }

    public static void scrollToElement(By locator) {
        WebDriver driver = getDriver();
        WebElement element = driver.findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

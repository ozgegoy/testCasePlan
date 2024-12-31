package Runner;

import Base.Hooks;
import Config.ConfigReader;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"StepDefinitions"},
        tags = "@test",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports.json"}
        //dryRun = true

)
public class Runner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setUp() {
        String browser = ConfigReader.getProperty("runner1");
        Hooks.runnerBeforeSteps(browser);
    }

    @AfterSuite
    public void tearDown() {
        Hooks.runnerAfterSteps();
    }


}
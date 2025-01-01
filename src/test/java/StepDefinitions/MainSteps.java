package StepDefinitions;

import Base.Hooks;
import Pages.MainPage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MainSteps {

    WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));

    @Given("main page is displayed correctly")
    public void theMainPageIsDisplayedCorrectly() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(MainPage.mainPage))).isDisplayed());
    }
}

package StepDefinitions;

import Base.Hooks;
import Pages.AutomationPage;
import Pages.OpenPositionsFiltersPage;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class OpenPositionsFiltersSteps {
    WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));

    @When("user clicks open positions")
    public void userClicksOpenPositions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("OPEN POSITIONS")))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("Open Positions")))).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OpenPositionsFiltersPage.getClassLocator("openPositions")))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("FILTERS")))).isDisplayed());

        List<WebElement> checkboxLabels = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(OpenPositionsFiltersPage.getClassLocator("checkbox") + "//li//label")));
        if (!checkboxLabels.isEmpty()) {
            checkboxLabels.remove(checkboxLabels.size() - 1);
        }
        for (WebElement label : checkboxLabels) {
            System.out.println("Checkbox Label Text: " + label.getText());
        }

    }
}

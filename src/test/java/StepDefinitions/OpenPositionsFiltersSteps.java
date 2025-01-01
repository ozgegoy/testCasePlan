package StepDefinitions;

import Base.Hooks;
import Pages.AutomationPage;
import Pages.OpenPositionsFiltersPage;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OpenPositionsFiltersSteps {
    WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
    String filterOpenPositions = "(//*[@id='filterOpenPositions']/child::*/child::*)[1]";

    @When("user clicks open positions")
    public void userClicksOpenPositions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("OPEN POSITIONS")))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("Open Positions")))).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OpenPositionsFiltersPage.getClassLocator("openPositions")))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("FILTERS")))).isDisplayed());

        Hooks.scrollToElement(By.xpath(AutomationPage.getTextLocator("UNLIMITED TALENT")));
        List<WebElement> checkboxLabels = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(OpenPositionsFiltersPage.getClassLocator("checkbox") + "//li//label")));

        if (!checkboxLabels.isEmpty()) {
            checkboxLabels.remove(checkboxLabels.size() - 1);
        }

        List<String> labelTexts = new ArrayList<>();
        for (WebElement label : checkboxLabels) {
            String labelText = label.getText();
            System.out.println("Checkbox Label Text: " + labelText);
            labelTexts.add(labelText);
        }

        for (String labelText : labelTexts) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getId("searchInput")))).clear();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getId("searchInput")))).sendKeys(labelText);

            String checkBox = "//label[contains(text(), '" + labelText + "')]/preceding-sibling::input";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkBox))).click();
            System.out.println("textControl: " + wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(filterOpenPositions))).getText().contains(labelText));
            //Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(filterOpenPositions))).getText().contains(labelText));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkBox))).click();
        }
    }
}

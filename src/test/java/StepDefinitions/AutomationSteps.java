package StepDefinitions;

import Base.Hooks;
import Pages.AutomationPage;
import Pages.MainPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Arrays;

public class AutomationSteps {
    WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
    String careerNavBarLocator = AutomationPage.getTextLocator("CAREER");
    String parentText = AutomationPage.getText("/parent::*");

    List<String> navItems = Arrays.asList(
            "High Altitude",
            "Our campuses",
            "Social Areas",
            "OPEN POSITIONS",
            "INTERNSHIP",
            "FAQ",
            "BAYKAR",
            "LOGIN"
    );

    List<String> displayItems = Arrays.asList(
            "High Altitude",
            "Our Campuses",
            "Life at",
            "Open Positions",
            "BAYKAR",
            "FREQUENTLY ASKED QUESTIONS",
            "SHOP",
            "CAREER PLATFORM"
    );

    @When("user clicks on the navigation bar items")
    public void theUserClicksOnTheNavigationBarItems() {
        for (int i = 0; i < navItems.size(); i++) {
            String navItem = navItems.get(i);

            if (navItem.equals("Our campuses") || navItem.equals("Social Areas") || navItem.equals("High Altitude")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(careerNavBarLocator))).click();
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(navItem)))).click();
            System.out.println("Clicked " + navItem);

            if (navItem.equals("High Altitude")) {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getId("background") + parentText + AutomationPage.getTextLocator("High Altitude")))).isDisplayed());
            } else if (navItem.equals("BAYKAR")) {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(displayItems.get(i))))).isDisplayed(), displayItems.get(i) + " is not displayed.");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(careerNavBarLocator))).click();
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("From the Roots")))).isDisplayed());
            } else {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(displayItems.get(i))))).isDisplayed(), displayItems.get(i) + " is not displayed.");
            }
        }
    }

    @Then("user clicks on the \"Main Page\" navigation bar")
    public void theUserClicksOnTheMainPageNavigationBar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getClassLocator("logo")))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(MainPage.mainPage))).isDisplayed());
    }
}

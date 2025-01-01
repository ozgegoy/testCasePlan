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
    String careerNavBarLocatorEnglish = AutomationPage.getTextLocator("CAREER");
    String careerNavBarLocatorTurkish = AutomationPage.getTextLocator("KARİYER");
    String parentText = AutomationPage.getText("/parent::*");
    String ancestorText = AutomationPage.getText("//ancestor::li[2]");
    List<String> navItemsEnglish = Arrays.asList("High Altitude", "Our campuses", "Social Areas", "OPEN POSITIONS", "INTERNSHIP", "FAQ", "BAYKAR", "LOGIN");
    List<String> displayItemsEnglish = Arrays.asList("High Altitude", "Our Campuses", "Life at", "Open Positions", "BAYKAR", "FREQUENTLY ASKED QUESTIONS", "SHOP", "CAREER PLATFORM");
    List<String> navItemsTurkish = Arrays.asList("Yüksek İrtifa", "Yerleşkelerimiz", "Sosyal Alanlar", "AÇIK POZİSYONLAR", "STAJ", "S.S.S", "BAYKAR", "GİRİŞ");
    List<String> displayItemsTurkish = Arrays.asList("Yüksek İrtifa", "Yerleşkelerimiz", "Yaşam", "Açık Pozisyonlar", "BAYKAR", "SIKÇA SORULAN SORULAR", "MAĞAZA", "KARİYER PLATFORMU");

    @When("user clicks on the navigation bar items")
    public void theUserClicksOnTheNavigationBarItems() {
        for (int i = 0; i < navItemsEnglish.size(); i++) {
            String navItem = navItemsEnglish.get(i);

            if (navItem.equals("Our campuses") || navItem.equals("Social Areas") || navItem.equals("High Altitude")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(careerNavBarLocatorEnglish))).click();
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(navItem)))).click();
            System.out.println("Clicked " + navItem);

            if (navItem.equals("High Altitude")) {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getId("background") + parentText + AutomationPage.getTextLocator("High Altitude")))).isDisplayed());
            } else if (navItem.equals("BAYKAR")) {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(displayItemsEnglish.get(i))))).isDisplayed(), displayItemsEnglish.get(i) + " is not displayed.");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(careerNavBarLocatorEnglish))).click();
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("From the Roots")))).isDisplayed());
            } else {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(displayItemsEnglish.get(i))))).isDisplayed(), displayItemsEnglish.get(i) + " is not displayed.");
            }
        }
    }

    @When("user changes the language to \"Turkish\" and clicks on the navigation bar items")
    public void theUserChangesTheLanguageToTurkishAndClicksOnTheNavigationBarItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("TR") + ancestorText))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("Köklerden")))).isDisplayed());

        for (int i = 0; i < navItemsTurkish.size(); i++) {
            String navItem = navItemsTurkish.get(i);

            if (navItem.equals("Yerleşkelerimiz") || navItem.equals("Sosyal Alanlar") || navItem.equals("Yüksek İrtifa")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(careerNavBarLocatorTurkish))).click();
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(navItem)))).click();
            System.out.println("Clicked " + navItem);
            if (navItem.equals("Yüksek İrtifa")) {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getId("background") + parentText + AutomationPage.getTextLocator("Yüksek İrtifa")))).isDisplayed());
            } else if (navItem.equals("Yerleşkelerimiz")) {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[normalize-space(text())='Yerleşkelerimiz'])[2]"))).isDisplayed());
            } else if (navItem.equals("BAYKAR")) {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(displayItemsTurkish.get(i))))).isDisplayed(), displayItemsTurkish.get(i) + " is not displayed.");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(careerNavBarLocatorTurkish))).click();
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator("Köklerden")))).isDisplayed());
            } else {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getTextLocator(displayItemsTurkish.get(i))))).isDisplayed(), displayItemsTurkish.get(i) + " is not displayed.");
            }
        }
    }

    @Then("user clicks on the \"Main Page\" navigation bar")
    public void theUserClicksOnTheMainPageNavigationBar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AutomationPage.getClassLocator("logo")))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(MainPage.mainPage))).isDisplayed());
    }
}

package Pages;

public class OpenPositionsFiltersPage {
    public static String getClassLocator(String locator) {
        return String.format("//div[@class='%s']", locator);
    }
}

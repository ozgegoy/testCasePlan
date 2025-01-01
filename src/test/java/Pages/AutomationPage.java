package Pages;

public class AutomationPage {
    public static String getTextLocator(String locatorName) {
        return String.format("//*[normalize-space(text())='%s']", locatorName.trim());
    }

    public static String getClassLocator(String className) {
        return String.format("//*[@class='%s']", className);
    }

    public static String getId(String id) {
        return String.format("//*[@id='%s']", id);
    }

    public static String getText(String text) {
        return String.format("%s", text);
    }
}

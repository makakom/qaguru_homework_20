package tests;

import com.codeborne.selenide.Configuration;
import drivers.EmulatorDriver;
import drivers.BrowserstackMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.getSessionId;


public class TestBase {

    private static final String deviceHost = System.getProperty("deviceHost", "emulator");

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = getDriverClass(deviceHost);
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();
        if (deviceHost.equals("browserstack"))
            Attach.video(sessionId);
    }

    private static String getDriverClass(String device) {
        switch (device.toLowerCase()) {
            case "emulator":
                return EmulatorDriver.class.getName();
            case "browserstack":
                return BrowserstackMobileDriver.class.getName();
        }
        return null;
    }
}
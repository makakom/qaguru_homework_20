package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidSelenideTests extends TestBase {

    @Test
    void searchTest() {
        step("Click skip", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("BrowserStack");
        });
        step("Verify content found", () ->
                $$(AppiumBy.className("android.widget.TextView")).shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void gettingStartedTest() {
        step("Check first screen", () -> {
                $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        $(AppiumBy.id("org.wikipedia.alpha:id/view_onboarding_page_indicator"))
                        .shouldHave(attribute("content-desc", "Page 1 of 4"));
        });

        step("Switch to second screen and check it", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/view_onboarding_page_indicator"))
                    .shouldHave(attribute("content-desc", "Page 2 of 4"));
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("New ways to explore"));
        });

        step("Switch to third screen and check it", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Reading lists with sync"));
            $(AppiumBy.id("org.wikipedia.alpha:id/view_onboarding_page_indicator"))
                    .shouldHave(attribute("content-desc", "Page 3 of 4"));
        });

        step("Switch to Fourth screen and check it", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button"))
                    .shouldBe(visible);
            $(AppiumBy.id("org.wikipedia.alpha:id/view_onboarding_page_indicator"))
                    .shouldHave(attribute("content-desc", "Page 4 of 4"));
        });
    }
}
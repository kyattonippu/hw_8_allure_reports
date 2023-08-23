package com.kyattonippu;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideListenerTest {

    private static final String ALLURE = "allure-framework/allure2";

    @Owner("kyattonippu")
    @Feature("Check contributors in Allure repo")
    @Story("Check contributors in Allure repo with SelenideListener")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Check eroshenkoam as contributor in Allure Framework repository with SelenideListener")
    @Link(name = "GitHub", url = "https://github.com")
    @Test

    public void checkEroshenkoamAsContributorOfAllureFramework() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $("button[placeholder='Search or jump to...']").click();
        $("#query-builder-test").sendKeys(ALLURE);
        $("#query-builder-test").submit();
        $(linkText(ALLURE)).click();

        $("a[href='/allure-framework/allure2/graphs/contributors']").click();
        $(linkText("eroshenkoam")).scrollTo();
        $(linkText("eroshenkoam")).shouldBe(visible);
    }
}

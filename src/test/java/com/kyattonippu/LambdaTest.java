package com.kyattonippu;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaTest {

    private static final String ALLURE = "allure-framework/allure2";

    @Owner("kyattonippu")
    @Feature("Check contributors in Allure repo")
    @Story("Check contributors in Allure repo with SelenideListener")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Check eroshenkoam as contributor in Allure Framework repository with SelenideListener")
    @Link(name = "GitHub", url = "https://github.com")
    @Test

    public void checkContributorsLambdaTest() {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        step("Открыть GitHub", () -> {
            open("https://github.com");
        });
        step("Найти репозиторий " + ALLURE, () -> {
            $("button[placeholder='Search or jump to...']").click();
            $("#query-builder-test").sendKeys(ALLURE);
            $("#query-builder-test").submit();
        });
        step("Перейти в найденный репозиторий " + ALLURE, () -> {
            $(linkText(ALLURE)).click();
        });
        step("Перейти в Contributors", () -> {
            $("a[href='/allure-framework/allure2/graphs/contributors']").click();
        });
        step("Проверить, что eroshenkoam находится в списке Contributors в " + ALLURE, () -> {
            $(linkText("eroshenkoam")).scrollTo();
            $(linkText("eroshenkoam")).shouldBe(visible);
            lifecycle.addAttachment("Скриншот Contributors репозитория " + ALLURE, "image/png",
                    "png", takeScreenshot());
        });
    }

    private byte[] takeScreenshot() {
        final WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

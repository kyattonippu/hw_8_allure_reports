package com.kyattonippu.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открыть Github")
    public WebSteps openGitHub() {
        open("https://github.com");

        return this;
    }

    @Step("Найти репозиторий {allure}")
    public WebSteps searchAllureRepository(String allure) {
        $("button[placeholder='Search or jump to...']").click();
        $("#query-builder-test").sendKeys(allure);
        $("#query-builder-test").submit();

        return this;
    }

    @Step("Перейти в найденный репозиторий {allure}")
    public WebSteps openAllureRepository(String allure) {
        $(linkText(allure)).click();

        return this;
    }

    @Step("Перейти в Contributors")
    public WebSteps openAllureContributors() {
        $("a[href='/allure-framework/allure2/graphs/contributors']").click();

        return this;
    }

    @Step("Проверить, что eroshenkoam находится в списке Contributors в {allure}")
    public WebSteps checkContributorsInAllureRepository() {
        $(linkText("eroshenkoam")).scrollTo();
        $(linkText("eroshenkoam")).shouldBe(visible);

        return this;
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        final WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

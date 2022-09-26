package qa.guru.allure;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StepsTest {
    private String URL = "https://github.com/";
    private String REPOSITORY_NAME = "selenide/selenide";
    private int ISSUE_NUMBER = 1962;

    @BeforeEach
    void configureBeforeEach() {
        Selenide.clearBrowserCookies();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void testIssueSearch() {
        open(URL);
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY_NAME);
        $(".header-search-input").submit();
        $(By.linkText("selenide/selenide")).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE_NUMBER)).should(exist);
    }

    @Test
    void testIssueSearchLambdaStep() {
        step("Открываем главную страницу", () ->
                open(URL));

        step("Ищем репозиторий", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY_NAME);
            $(".header-search-input").submit();
        });

        step("Кликаем по ссылке репозитория", () ->
                $(By.linkText(REPOSITORY_NAME)).click());

        step("Открываем таб Issues", () ->
                $("#issues-tab").click());

        step("Проверяем наличие Issue с номером " + ISSUE_NUMBER, () ->
                $(withText("#" + ISSUE_NUMBER)).should(exist));
    }

    @Test
    void testAnnotatedStep() {
        WebSteps steps = new WebSteps();

        steps.openMainPage(URL);
        steps.searchForRepository(REPOSITORY_NAME);
        steps.clickOnRepositoryLink(REPOSITORY_NAME);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}

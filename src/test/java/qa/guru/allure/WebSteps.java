package qa.guru.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу {url}")
    public void openMainPage(String url) {
        open(url);
    }

    @Step("Ищем репозиторий {repositoryName}")
    public void searchForRepository(String repositoryName) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repositoryName);
        $(".header-search-input").submit();
    }

    @Step("Кликаем по ссылке репозитория {repositoryName}")
    public void clickOnRepositoryLink(String repositoryName) {
        $(linkText(repositoryName)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issueNumber}")
    public void shouldSeeIssueWithNumber(int issueNumber) {
        $(withText("#" + issueNumber)).should(Condition.exist);
    }

}
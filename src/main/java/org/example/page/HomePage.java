package org.example.page;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
public class HomePage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement accountButton;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;
    @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__list__2A-mT")
    private ElementsCollection menuIngredients;

    @Step("Click account button")
    public LoginPage clickAccountButton() {
        accountButton.click();
        return page(LoginPage.class);
    }

    @Step("Click account button, go account page")
    public AccountPage clickAccountButtonGoAccountPage() {
        accountButton.click();
        return page(AccountPage.class);
    }

    @Step("Click login button")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Find last bun ingredient")
    public boolean findBunIngredient() {
        SelenideElement bun = menuIngredients.get(0).lastChild();
        bun.scrollIntoView(true);
        bun.click();
        return bun.isDisplayed();
    }

    @Step("Find last sauce ingredient")
    public boolean findSauceIngredient() {
        SelenideElement sauce = menuIngredients.get(1).lastChild();
        sauce.scrollIntoView(true);
        sauce.click();
        return sauce.isDisplayed();
    }

    @Step("Find last filling ingredient")
    public boolean findFillingIngredient() {
        SelenideElement filling = menuIngredients.get(2).lastChild();
        filling.scrollIntoView(true);
        filling.click();
        return filling.isDisplayed();
    }
}

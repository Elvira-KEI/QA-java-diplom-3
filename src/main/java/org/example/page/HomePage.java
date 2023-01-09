package org.example.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

   public static final String URL = "https://stellarburgers.nomoreparties.site/";
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement accountButton;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;
   @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__list__2A-mT")
    private ElementsCollection menuIngredients;
    @FindBy(how = How.XPATH,using = ".//button[text()='Оформить заказ']")
    private SelenideElement checkoutButton;
    //локатор раздела "Начинки"
    @FindBy(how = How.XPATH,using = ".//span[text()='Начинки']")
    private SelenideElement filling;
    //локатор заголовка "Начинки"
    @FindBy(how = How.XPATH,using = ".//h2[text()='Начинки']")
    private SelenideElement headerFilling;
    //локатор раздела "Начинки" после клика на него
    @FindBy(how = How.XPATH,using = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span")
    private SelenideElement headerFillingVisibleAfterClick;
    //локатор раздела "Соусы"
    @FindBy(how = How.XPATH,using = ".//span[text()='Соусы']")
    private SelenideElement sauces;
    //локатор заголовка "Соусы"
    @FindBy(how = How.XPATH,using = ".//h2[text()='Соусы']")
    private SelenideElement headerSauces;
    //локатор раздела "Соусы" после клика на него
    @FindBy(how = How.XPATH,using = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span")
    private SelenideElement headerSaucesVisibleAfterClick;
    //локатор раздела "Булки"
    @FindBy(how = How.XPATH,using = "//span[contains(text(),'Булки')]")
    private SelenideElement buns;
    //локатор заголовка "Булки"
    @FindBy(how = How.XPATH,using = ".//h2[text()='Булки']")
    private SelenideElement headerBuns;
    //локатор раздела "Булки" после клика на него
    @FindBy(how = How.XPATH,using = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span")
    private SelenideElement headerBunsVisibleAfterClick;

    @Step("Click account button")
    public LoginPage clickAccountButton() {
        accountButton.click();
        return page(LoginPage.class);
    }

    //метод клика по кнопке "Личный кабинет"
    public void clickPersonalAccountButton() {
        accountButton.shouldBe(enabled).click();
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
    public boolean findLastBunIngredient() {
        SelenideElement bun = menuIngredients.get(0).lastChild();
        bun.scrollIntoView(true);
        bun.click();
        return bun.isDisplayed();
    }

    @Step("Find last sauce ingredient")
    public boolean findLastSauceIngredient() {
        SelenideElement sauce = menuIngredients.get(1).lastChild();
        sauce.scrollIntoView(true);
        sauce.click();
        return sauce.isDisplayed();
    }

    @Step("Find last filling ingredient")
    public boolean findLastFillingIngredient() {
        SelenideElement filling = menuIngredients.get(2).lastChild();
        filling.scrollIntoView(true);
        filling.click();
        return filling.isDisplayed();
    }
    public boolean isCheckoutButtonDisplayed() {
        checkoutButton.shouldBe(exist);
        return checkoutButton.isDisplayed();
    }
    //метод клика по разделу "Начинки"
    @Step("Клик по разделу \"Начинки\"")
    public void clickFilling() {
        filling.shouldBe(exist);
        filling.click();
    }
    //метод нахождения заголовка "Начинки"
    public boolean isHeaderFillingVisible() {
        return headerFillingVisibleAfterClick.getText().contentEquals("Начинки");
    }


    //метод клика по разделу "Соусы"
    @Step("Клик по разделу \"Соусы\"")
    public void clickSauces() {
        sauces.shouldBe(exist);
        sauces.click();
    }
    //метод нахождения заголовка "Соусы"
    public boolean isHeaderSaucesVisible() {
        return headerSaucesVisibleAfterClick.getText().contentEquals("Соусы");
    }
    //метод клика по разделу "Булки"
    @Step("Клик по разделу \"Булки\"")
    public void clickBuns() {
        buns.shouldBe(exist);
        buns.click();
    }
    //метод нахождения заголовка "Булки"
    public boolean isHeaderBunsVisible() {
        return headerBunsVisibleAfterClick.getText().contentEquals("Булки");
    }

  }
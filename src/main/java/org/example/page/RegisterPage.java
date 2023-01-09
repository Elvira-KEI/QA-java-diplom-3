package org.example.page;
import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;
    //локатор поля "Имя"
    @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'input pr-6 pl-6')]/input)[1]")
    private SelenideElement nameField;
    //локатор поля "Email"
    @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'input pr-6 pl-6')]/input)[2]")
    private SelenideElement emailField;
    //локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordField;
   /*@FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default']")
    public ElementsCollection registerForm;*/
        @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    public SelenideElement passwordError;
    //локатор текста "Некорректный пароль"
    @FindBy(how = How.XPATH,using = ".//p[@class='input__error text_type_main-default']")
    private SelenideElement unCorrectPassword;

    @Step("Fill name field")
    public void setNameField(String name) {
        nameField.setValue(name);
    }

    @Step("Fill email field")
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    @Step("Fill password field")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Fill register form")
    public RegisterPage fillRegisterForm(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        return page(RegisterPage.class);
    }

    @Step("Click register button")
    public boolean clickRegisterButton(Condition condition) {
        registerButton.click();
        return registerButton.shouldBe(condition).isDisplayed();
    }

    @Step("Is displayed password error")
    public boolean isDisplayedPasswordError() {
        clickRegisterButton(Condition.visible);
        return passwordError.isDisplayed();
    }

    @Step("Click login link")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return page(LoginPage.class);
    }
    //метод нахождения текста "Некорректный пароль"
    public boolean isUnCorrectPasswordDisplayed() {
        unCorrectPassword.shouldBe(visible);
        return unCorrectPassword.isDisplayed();
    }
}

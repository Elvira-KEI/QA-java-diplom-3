package org.example.page;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.page;
public class LoginPage {
    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordLink;
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    public SelenideElement emailField;
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    public SelenideElement passwordField;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    @Step("Click register link")
    public RegisterPage clickRegisterLink() {
        registerLink.click();
        return page(RegisterPage.class);
    }

    @Step("Click forgot password link")
    public ForgotPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return page(ForgotPasswordPage.class);
    }

    @Step("Fill email field")
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    @Step("Fill password field")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Fill login form")
    public LoginPage fillLoginForm(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        return page(LoginPage.class);
    }

    @Step("Click login button, go account page")
    public AccountPage clickLoginButton() {
        loginButton.click();
        return page(AccountPage.class);
    }

    @Step("Click login button")
    public boolean clickLoginButton(Condition condition) {
        loginButton.click();
        return loginButton.shouldBe(condition).isDisplayed();
    }
}

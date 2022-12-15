package org.example.page;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.page;
public class RegisterPage {
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;
    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default']")
    public ElementsCollection registerForm;
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    public SelenideElement passwordError;

    @Step("Fill name field")
    public void setNameField(String name) {
        registerForm.get(0).setValue(name);
    }

    @Step("Fill email field")
    public void setEmailField(String email) {
        registerForm.get(1).setValue(email);
    }

    @Step("Fill password field")
    public void setPasswordField(String password) {
        registerForm.get(2).setValue(password);
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
}

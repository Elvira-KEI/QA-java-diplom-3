package yandex;

import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.api.User;
import org.example.page.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class UserRegisterYandexTest extends BaseBurgers {

    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registrationPage;
    String name = RandomStringUtils.randomAlphabetic(10);
    String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    String passwordCorrectWithSixCharacters = RandomStringUtils.randomAlphabetic(6);
    String passwordUnCorrectWithFiveCharacters = RandomStringUtils.randomAlphabetic(5);

    @Before
    public void setUp() {
        //открыть браузер в максимальном разрешении
        Configuration.startMaximized = true;

        //запустить Яндекс браузер
        startYandexBrowser();}

    @After
    public void tearDown() {
        //закрыть браузер
        closeWebDriver();
    }

    @Test
    @Description("Successful user registration")
    public void successfulRegistrationTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Личный Кабинет"
        homePage.clickAccountButton();

        loginPage = page(LoginPage.class);

        //кликнуть на "Зарегистрироваться"
        loginPage.clickRegisterLink();

        registrationPage = page(RegisterPage.class);

        //заполнить форму с именем, электронной почтой и паролем из 6 символов
        registrationPage.fillRegisterForm(name, email, passwordCorrectWithSixCharacters);

        //проверить на дисплее кнопку "Войти"
        assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    @Description("Unsuccessful registration of a user with a five-character password")
    public void unsuccessfulRegistrationWithPasswordFiveCharactersTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Личный Кабинет"
        homePage.clickAccountButton();

        loginPage = page(LoginPage.class);
        //кликнуть на "Зарегистрироваться"
        loginPage.clickRegisterLink();

        registrationPage = page(RegisterPage.class);

        //заполнить форму с именем, электронной почтой и паролем из 6 символов
        registrationPage.fillRegisterForm(name, email, passwordUnCorrectWithFiveCharacters);

        //проверить на дисплее "Некорректный пароль"
        assertTrue(registrationPage.isUnCorrectPasswordDisplayed());
    }
}

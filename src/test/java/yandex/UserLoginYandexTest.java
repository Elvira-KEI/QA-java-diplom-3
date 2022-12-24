package yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.api.GenerateUser;
import org.example.api.User;
import org.example.page.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserLoginYandexTest extends BaseBurgers {

    private HomePage homePage;
    private LoginPage loginPage;

    private RegisterPage registrationPage;
    private AccountPage accountProfile;
    private ForgotPasswordPage forgotPassword;

    String name = RandomStringUtils.randomAlphabetic(10);
    String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    String password = RandomStringUtils.randomAlphabetic(10);

    @Before
    public void setUp() {
        //открыть браузер в максимальном разрешении
        Configuration.startMaximized = true;

        //запустить Яндекс браузер
        startYandexBrowser();



        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        homePage = open(HomePage.URL, HomePage.class);
        homePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(name, email, password)
                .clickRegisterButton(Condition.hidden);
        homePage = null;
    }

    @After
    public void tearDown() {

        HomePage homePage = page(HomePage.class);

        //кликнуть на "Личный Кабинет"
        homePage.clickPersonalAccountButton();

        AccountPage accountProfile = page(AccountPage.class);
        //разлогин
        //кликнуть на "Выход"
        accountProfile.clickLogout();
    }

    @Test
    @Description("Check the login using the \"Log in to account\" button on the main page")
    public void checkLoginButtonMainPageForLoginTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Войти в аккаунт"
        homePage.clickLoginButton();

        loginPage = page(LoginPage.class);

        //заполнить форму с электронной почтой и паролем
        loginPage.fillLoginForm(email, password);


       //проверить на дисплее кнопку "Оформить заказ"
        assertTrue(homePage.isCheckoutButtonDisplayed());
    }

    @Test
    @Description("Check the login via the \"Personal Account\" button on the main page")
    public void checkPersonalAccountButtonMainPageForLoginTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Личный Кабинет"
        homePage.clickPersonalAccountButton();

         loginPage = page(LoginPage.class);
        //заполнить форму с электронной почтой и паролем
        loginPage.fillLoginForm(email, password);

        //проверить на дисплее кнопку "Оформить заказ"
        assertTrue(homePage.isCheckoutButtonDisplayed());
    }

    @Test
    @Description("Check the login via the button in the registration form on the registration page")
    public void checkLoginButtonRegistrationPageForLoginTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Личный Кабинет"
        homePage.clickPersonalAccountButton();

         loginPage = page(LoginPage.class);
        //кликнуть на "Зарегистрироваться"
        loginPage.clickRegister();

         registrationPage = page(RegisterPage.class);
        //кликнуть на "Войти"
        registrationPage.clickLoginLink();

        //заполнить форму с электронной почтой и паролем
        loginPage.fillLoginForm(email, password);

        //проверить на дисплее кнопку "Оформить заказ"
        assertTrue(homePage.isCheckoutButtonDisplayed());
    }

    @Test
    @Description("Check the login via the button in the password recovery form on the password recovery page")
    public void checkLoginButtonForgotPasswordPageForLoginTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Личный Кабинет"
        homePage.clickPersonalAccountButton();

        loginPage = page(LoginPage.class);
        //кликнуть на "Восстановить пароль"
        loginPage.clickRecoverPassword();

         forgotPassword = page(ForgotPasswordPage.class);
        //кликнуть на "Войти"
        forgotPassword.clickLoginLink();

        //заполнить форму с электронной почтой и паролем
        loginPage.fillLoginForm(email, password);

        //проверить на дисплее кнопку "Оформить заказ"
        assertTrue(homePage.isCheckoutButtonDisplayed());
    }

}

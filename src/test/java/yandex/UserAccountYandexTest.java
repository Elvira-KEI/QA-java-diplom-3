package yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.example.api.GenerateUser;
import org.example.api.User;
import org.example.page.AccountPage;
import org.example.page.HomePage;
import org.example.page.LoginPage;
import org.example.page.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

public class UserAccountYandexTest extends BaseBurgers {
    private User user;
    private HomePage homePage;
    private LoginPage loginPage;

    private RegisterPage registrationPage;
    private AccountPage accountProfile;


    @Before
    public void setUp() {
        //открыть браузер в максимальном разрешении
        Configuration.startMaximized = true;

        //запустить Яндекс браузер
        startYandexBrowser();

        user = GenerateUser.getRandomUser();

        homePage = open(HomePage.URL, HomePage.class);
        homePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), user.getPassword())
                .clickRegisterButton(Condition.hidden);
        homePage = null;
    }
    @After
    public void tearDown() {
        //закрыть браузер
        closeWebDriver();
    }
    @Test
    @Description("Check the click-through to the \"Personal Account\"")
    public void checkAccountProfileTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Войти в аккаунт"
        homePage.clickLoginButton();

        //заполнить форму с электронной почтой и паролем
        loginPage = page(LoginPage.class);
        loginPage.fillLoginForm(user.getEmail(), user.getPassword());

        //кликнуть на "Личный Кабинет"
        homePage.clickAccountButton();

        accountProfile = page(AccountPage.class);

        //проверить на дисплее "Выход"
        assertTrue(accountProfile.isLogoutDisplayed());

        //разлогинится
        //кликнуть на "Выход"
        accountProfile.clickLogout();
    }

    @Test
    @Description("Check the click on the \"Constructor\"")
    public void checkConstructorFromAccountProfileTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Войти в аккаунт"
        homePage.clickLoginButton();
        loginPage = page(LoginPage.class);

        //заполнить форму с электронной почтой и паролем
        loginPage.fillLoginForm(user.getEmail(), user.getPassword());

        //кликнуть на "Личный Кабинет"
        homePage.clickAccountButton();

        accountProfile = page(AccountPage.class);

        //кликнуть на "Конструктор"
        accountProfile.clickConstructor();

        //проверить на дисплее кнопку "Оформить заказ"
        assertTrue(homePage.isCheckoutButtonDisplayed());

        //разлогинится
        //кликнуть на "Личный Кабинет"
        homePage.clickAccountButton();

        //кликнуть на "Выход"
        accountProfile.clickLogout();

    }

    @Test
    @Description("Check the exit by clicking the \"Exit\" button in your personal account")
    public void checkLogoutFromAccountProfileTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на "Войти в аккаунт"
        homePage.clickLoginButton();
        loginPage = page(LoginPage.class);

        //заполнить форму с электронной почтой и паролем
        loginPage.fillLoginForm(user.getEmail(), user.getPassword());

        //кликнуть на "Личный Кабинет"
        homePage.clickAccountButton();

        accountProfile = page(AccountPage.class);

        //кликнуть на "Выход"
        accountProfile.clickLogout();

        //проверить на дисплее кнопку "Войти"
        assertTrue(loginPage.isLoginButtonDisplayed());
    }
    @Test
    @DisplayName("Transition user to constructor")
    public void transitionToConstructor() {
        homePage = open(HomePage.URL, HomePage.class);
        String url = homePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton()
                .clickConstructor();

        assertEquals(HomePage.URL, url);
    }

    @Test
    @DisplayName("Transition user to logo burger")
    public void transitionToLogoBurger() {
        homePage = open(HomePage.URL, HomePage.class);
        String url = homePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton()
                .clickLogoBurger();

        assertEquals(HomePage.URL, url);
    }


}

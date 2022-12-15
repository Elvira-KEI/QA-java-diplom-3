import org.example.api.GenerateUser;
import org.example.api.User;
import org.example.page.HomePage;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;
@Epic("Login user")
public class UserLoginTest {
    private User user;
    private HomePage homePage;

    @Before
    public void setUp() {
        user = GenerateUser.getRandomUser();
        homePage = open(HomePage.URL, HomePage.class);
        homePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), user.getPassword())
                .clickRegisterButton(Condition.hidden);
        homePage = null;
    }

    @After
    public void clearState() {
        user = null;
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Login user by login button")
    public void loginUserByLoginButton() {
        homePage = open(HomePage.URL, HomePage.class);
        boolean isDisplayed = homePage.clickLoginButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by account button")
    public void loginUserByAccountButton() {
        homePage = open(HomePage.URL, HomePage.class);
        boolean isDisplayed = homePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by register page")
    public void loginUserByRegisterPage() {
        homePage = open(HomePage.URL, HomePage.class);
        boolean isDisplayed = homePage.clickLoginButton()
                .clickRegisterLink()
                .clickLoginLink()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by forgot password page")
    public void loginUserByForgotPasswordPage() {
        homePage = open(HomePage.URL, HomePage.class);
        boolean isDisplayed = homePage.clickLoginButton()
                .clickForgotPasswordLink()
                .clickLoginLink()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        assertFalse(isDisplayed);
    }
}

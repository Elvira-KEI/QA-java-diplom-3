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

@Epic("Logout user and transitions between pages")
public class UserAccountTest {
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

    @Test
    @DisplayName("Logout user by logout button")
    public void logoutUserByLogoutButton() {
        homePage = open(HomePage.URL, HomePage.class);
        homePage.clickAccountButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);

        boolean isDisplayed = homePage.clickAccountButtonGoAccountPage()
                .clickLogoutButton(Condition.hidden);

        assertFalse(isDisplayed);
    }
}

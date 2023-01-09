package chrome;
import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.example.page.HomePage;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.Epic;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

@Epic("Navigate burger constructor")
public class HomePageBurgersTests {
    private HomePage homePage;

    @Before
    public void setUp() {
        //открыть браузер в максимальном разрешении
        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {
        //закрыть браузер
        closeWebDriver();
    }

    @Test
    @Description("Checking the click on the filling")
    public void checkClickFillingTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на раздел "Начинки"
        homePage.clickFilling();

        //Проверить на дисплее после перехода в раздел "Начинки"
        assertTrue("Нахождение не в разделе 'Начинки'", homePage.isHeaderFillingVisible());
    }

    @Test
    @Description("Checking the click on the buns")
    public void checkClickBunsTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //проверить на дисплее заголовок "Булки"
        assertTrue(homePage.isHeaderBunsVisible());
    }

    @Test
    @Description("Checking the click on the sauces")
    public void checkClickSaucesTest() {

        //перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        //кликнуть на раздел "Соусы"
        homePage.clickSauces();

        //проверить на дисплее после перехода в раздел "Начинки"
        assertTrue("Нахождение не в разделе 'Соусы'", homePage.isHeaderSaucesVisible());
    }
}

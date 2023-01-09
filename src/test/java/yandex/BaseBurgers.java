package yandex;

import org.openqa.selenium.chrome.ChromeOptions;

public class BaseBurgers {
    public void startYandexBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");

    }
}

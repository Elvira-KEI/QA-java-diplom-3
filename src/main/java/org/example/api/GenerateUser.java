package org.example.api;
import io.qameta.allure.Allure;
 import org.example.api.User;

import org.apache.commons.lang3.RandomStringUtils;
public class GenerateUser {
    public static User getRandomUser() {
        String name = RandomStringUtils.randomAlphabetic(8);
        String email = name.toLowerCase() + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(8);

        Allure.addAttachment("Email : ", email);
        Allure.addAttachment("Password : ", password);
        Allure.addAttachment("Name : ", name);

        return new User(email, password, name);
    }
}

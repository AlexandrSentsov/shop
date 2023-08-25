package com.example.shop.unit;

import com.example.shop.ShopHandler;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit-тесты класса ShopHandler")
class ShopHandlerTest {

    Faker faker = new Faker();

    @Test
    @DisplayName("Тестирование проверки на длину имени (позитивный сценарий)")
    void shouldCheckLengthPositive() {
        String name = faker.company().name().substring(0, 7);
        Assertions.assertTrue(ShopHandler.checkLength(name, 7), "Не сработала проверка, " +
                "что имя магазина 7 и более символов");
    }

    @Test
    @DisplayName("Тестирование проверки на длину имени (негативный сценарий)")
    void shouldCheckLengthNegative() {
        String name = faker.company().name().substring(0, 6);
        Assertions.assertFalse(ShopHandler.checkLength(name, 7), "Не сработала проверка, " +
                "что имя магазина 6 или менее символов");
    }

    @Test
    @DisplayName("Тестирование проверки на заглавную букву в имени (позитивный сценарий)")
    void shouldCheckFirstLetterPositive() {
        String name = StringUtils.capitalize(faker.company().name());
        Assertions.assertTrue(ShopHandler.checkFirstLetter(name), "Не сработала проверка с заглавной буквой");
    }

    @Test
    @DisplayName("Тестирование проверки на заглавную букву в имени (негативный сценарий)")
    void shouldCheckFirstLetterNegative() {
        String name = StringUtils.lowerCase(faker.company().name());
        Assertions.assertFalse(ShopHandler.checkFirstLetter(name), "Не сработала проверка без заглавной буквой");
    }
}
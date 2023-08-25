package com.example.shop.unit;

import com.example.shop.models.ShopDto;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit-тесты класса ShopDto")
public class ShopDtoTest {
    ShopDto shop;
    Faker faker = new Faker();
    long id;
    String name;

    @BeforeEach
    @DisplayName("Установка тестовых данных")
    void shouldCreatedShop() {
        id = faker.number().numberBetween(1000, 10000);
        name = faker.company().name();
        shop = new ShopDto(id, name, true);
    }

    @Test
    @DisplayName("Тестирование получения Id магазина")
    void shouldGetShopId() {
        Assertions.assertEquals(id, shop.getShopId());
    }

    @Test
    @DisplayName("Тестирование установки Id магазина")
    void shouldSetShopId() {
        long newId = faker.number().numberBetween(1000, 10000);
        shop.setShopId(newId);
        Assertions.assertEquals(newId, shop.getShopId());
    }

    @Test
    @DisplayName("Тестирование получения имени магазина")
    void shouldGetShopName() {
        Assertions.assertEquals(name, shop.getShopName());
    }

    @Test
    @DisplayName("Тестирование установки имени магазина")
    void shouldSetShopName() {
        String newName = faker.company().name();
        shop.setShopName(newName);
        Assertions.assertEquals(newName, shop.getShopName());
    }

    @Test
    @DisplayName("Тестирование получения значения переменной private")
    void shouldIsShopPublic() {
        Assertions.assertTrue(shop.isShopPublic());
    }

    @Test
    @DisplayName("Тестирование установки значения переменной private")
    void shouldSetShopPublic() {
        shop.setShopPublic(false);
        Assertions.assertFalse(shop.isShopPublic());
    }
}

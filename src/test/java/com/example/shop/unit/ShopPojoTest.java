package com.example.shop.unit;

import com.example.shop.models.ShopPojo;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit-тесты класса ShopPojo")
class ShopPojoTest {
    ShopPojo shop;
    Faker faker = new Faker();
    long id;
    String name;

    @BeforeEach
    @DisplayName("Тестирование метода создания магазина")
    void shouldCreatedShop() {
        id = faker.number().numberBetween(1000, 10000);
        name = faker.company().name();
        shop = new ShopPojo();
    }

    @Test
    @DisplayName("Тестирование метода получения Id магазина")
    void shouldSetAndGetShopId() {
        shop.setShopId(id);
        Assertions.assertEquals(id, shop.getShopId());
    }

    @Test
    @DisplayName("Тестирование метода получения имени магазина")
    void shouldSetAndGetShopName() {
        shop.setShopName(name);
        Assertions.assertEquals(name, shop.getShopName());
    }

    @Test
    @DisplayName("Тестирование метода получения значения public")
    void shouldGetAndSetShopPublic() {
        shop.setShopPublic(true);
        Assertions.assertTrue(shop.getShopPublic());
    }
}
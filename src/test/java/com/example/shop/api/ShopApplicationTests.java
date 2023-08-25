package com.example.shop.api;

import com.example.shop.models.ShopDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("API-тесты")
class ShopApplicationTests {

	@Test
	@DisplayName("Вывод списка магазинов")
	void shouldGetAllShopsTest() {
		Response response = Steps.getAll();
		assertThat(response.body().prettyPrint()).isNotNull();
	}

	@ParameterizedTest()
	@ValueSource(strings = { "TestShop_", "Тестовый магазин_", "T?!#@$%&№~*^`'/|()[ ]{}-+=.,:;<>_" })
	@DisplayName("Создание магазина с различными вариантами имени")
	void shouldAddShopTest(String strings) {
		Steps.postAdd(strings + LocalDateTime.now(), true);
	}

	@Test
	@DisplayName("Проверка на получение ошибки при вводе маленькой буквы в начале имени магазина")
	void shouldAddShopWithoutCapitalLetterTest() {
		Response response = Steps.postAdd("testShop_" + LocalDateTime.now(), true);
		assertThat(response.body().prettyPrint()).contains("Name should begin with a capital letter");
	}

	@Test
	@DisplayName("Проверка на получение ошибки при вводе менее 6 символов в имени магазина")
	void shouldAddShopWithTooShortNameTest() {
		Response response = Steps.postAdd("Test", true);
		assertThat(response.body().prettyPrint()).contains("Name should be more than 6 letters");
	}

	@Test
	@DisplayName("Проверка на получение ошибки при вводе более 256 символов в имени магазина")
	void shouldAddShopWithTooLongNameTest() {
		Response response = Steps.postAdd("A".repeat(257), true);
		Assertions.assertEquals("Internal Server Error", response.body().jsonPath().get("error"));
	}

	@ParameterizedTest
	@ValueSource(booleans = {true, false})
	@DisplayName("Создание магазин (с обоми вариантами public), поиск его в списке магагзинов и удаление")
	void shouldDeleteShop(boolean booleans) {
		String shopName = "TestShop_" + LocalDateTime.now();
		Steps.postAdd(shopName, booleans);
		List<ShopDto> shopsList = Steps.getAll().jsonPath().getList("", ShopDto.class);
		int index = 0;
		for (ShopDto shopDto : shopsList) {
			if (shopDto.getShopName().contains(shopName)) {
				break;
			}
			index++;
		}
		Steps.delete(shopsList.get(index).getShopId());
	}
}

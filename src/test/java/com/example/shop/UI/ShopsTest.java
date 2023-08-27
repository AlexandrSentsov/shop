package com.example.shop.UI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.shop.api.Steps;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@DisplayName("UI-тесты")
public class ShopsTest {
    MainPage page;
    Faker faker = new Faker();

    @BeforeAll
    @DisplayName("Установка настроек")
    static void setBeforeAll() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\asentsov\\IdeaProjects\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        boolean isRemote = true;
        if (isRemote) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setCapability("enableVNC", true);
            WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4000").toURL(), capabilities);
            setWebDriver(driver);
        } else {
            Configuration.browser = "firefox";
        }
    }
    @BeforeEach
    @DisplayName("Открытие страницы")
    void setBeforeEach() {
        open("http://localhost:4000");
        page = new MainPage();
    }

    @AfterEach
    @DisplayName("Закрытие браузера")
    void setAfterEach() {
        Selenide.closeWebDriver();
    }


    @Test
    @DisplayName("Создание магазина и проверка что он действительно создался")
    public void shouldCreateShop() {
        String shopName = "TestShop_" + LocalDateTime.now();
        page
                .enterShopName(shopName)
                .clickCreateCheckBox()
                .clickCreateButton()
                .accessAlert();

        Assertions.assertTrue(MainPage.searchShopByName(shopName));
    }

    @Test
    @DisplayName("Удаление рандомного магазина и проверка что он действительно удалился")
    public void shouldDeleteShop() {
        String shopId = MainPage.getShopIdFromRandomShop();
        page
                .enterShopId(shopId)
                .clickDeleteButton()
                .accessAlert();

        Assertions.assertFalse(MainPage.searchShopById(shopId));
    }

    @Test
    @DisplayName("Создание магазина через бэк, нажитие кнопки рефреша и проверка что магазин появился в списке")
    public void shouldRefreshShopsList() {
        String shopName = "Test_" + LocalDateTime.now();
        Assertions.assertFalse(MainPage.searchShopByName(shopName));
        Steps.postAdd(shopName, true);
        page
                .clickRefreshButton();

        Assertions.assertTrue(MainPage.searchShopByName(shopName));
    }

    @Test
    @DisplayName("Проверка ссылок на соцсети")
    public void shouldCheckLinkToTelegramAndVK () {

        Assertions.assertEquals("https://web.telegram.org/", page.checkTelegramLink());
        Assertions.assertEquals("https://m.vk.com/", page.checkVKLink());
    }

    @Test
    @DisplayName("Проверка скролла при клике кнопки Create shop")
    public void shouldScrollToCreateButton() {
        page
                .clickLinkCreateShopButton();

        Assertions.assertEquals("http://localhost:4000/#create_shop", WebDriverRunner.url());
    }

    @Test
    @DisplayName("Проверка скролла при клике кнопки All shops")
    public void shouldScrollToRefreshButton() {
        page
                .clickLinkAllShopsButton();

        Assertions.assertEquals("http://localhost:4000/#all_shops", WebDriverRunner.url());
    }

    @Test
    @DisplayName("Проверка скролла при клике кнопки Delete shop")
    public void shouldScrollToDeleteButton() {
        page
                .clickLinkDeleteShopButton();

        Assertions.assertEquals("http://localhost:4000/#delete_shop", WebDriverRunner.url());
    }

    @Test
    @DisplayName("Проверка отображения сообщения об ошибке при вводе имени магазина с маленькой буквы")
    public void shouldShowCreateShopErrorLow() {
        page
                .enterShopName(faker.company().name().toLowerCase())
                .clickCreateButton();

        Assertions.assertTrue(MainPageElements.CREATE_SHOP_ERROR_MESSAGE.isDisplayed());
    }

    @Test
    @DisplayName("Проверка отображения сообщения об ошибке при вводе слишком короткого имени магазина")
    public void shouldShowCreateShopErrorShirt() {
        page
                .enterShopName(faker.company().name().substring(0, 6))
                .clickCreateButton();

        Assertions.assertTrue(MainPageElements.CREATE_SHOP_ERROR_MESSAGE.isDisplayed());
    }

    @Test
    @DisplayName("Проверка отображения сообщения об ошибке при вводе слишком длинного имени магазина")
    public void shouldShowCreateShopErrorTooLong() {
        page
                .enterShopName("A".repeat(257))
                .clickCreateButton();

        Assertions.assertTrue(MainPageElements.CREATE_SHOP_ERROR_MESSAGE.
                shouldBe(visible, Duration.ofSeconds(1)).isDisplayed());
    }

    @Test
    @DisplayName("Проверка отображения сообщения об ошибке при невведении Id магазина")
    public void shouldShowDeleteShopError() {
        page
                .clickDeleteButton();

        Assertions.assertTrue(MainPageElements.DELETE_SHOP_ERROR_MESSAGE.
                shouldBe(visible, Duration.ofSeconds(1)).isDisplayed());
    }
}

package com.example.shop.UI;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import java.util.Objects;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    @Step("Клик на кнопку рефреша")
    public void clickRefreshButton() {
        MainPageElements.REFRESH_BUTTON.shouldBe(visible).click();
    }

    @Step("Ввод имени магазина в строку создания")
    public MainPage enterShopName(String shopName) {
        MainPageElements.CREATE_PLACEHOLDER.shouldBe(visible).sendKeys(shopName);
        return this;
    }

    @Step("Установка чекбокса private")
    public MainPage clickCreateCheckBox() {
        MainPageElements.CREATE_CHECKBOX.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие на кнопку создания магазина")
    public MainPage clickCreateButton() {
        MainPageElements.CREATE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Закрытие алерта")
    public void accessAlert() {
        Selenide.switchTo().alert().accept();
    }

    @Step("Ввод id магазина в строку удаления")
    public MainPage enterShopId(String shopId) {
        MainPageElements.DELETE_PLACEHOLDER.shouldBe(visible).sendKeys(shopId);
        return this;
    }

    @Step("Нажатие на кнопку удаления магазина")
    public MainPage clickDeleteButton() {
        MainPageElements.DELETE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие на кнопку телеграма")
    public String checkTelegramLink() {
        return Objects.requireNonNull(MainPageElements.TELEGRAM.getAttribute("href"));
    }

    @Step("Нажатие на кнопку ВК")
    public String checkVKLink() {
        return Objects.requireNonNull(MainPageElements.VK.getAttribute("href"));



    }

    @Step("Нажатие на кнопку перехода к кнопке создания магазина")
    public void clickLinkCreateShopButton() {
        MainPageElements.LINK_TO_CREATE_BUTTON.shouldBe(visible).click();
    }

    @Step("Нажатие на кнопку перехода к кнопке обновления списка магазинов")
    public void clickLinkAllShopsButton() {
        MainPageElements.LINK_TO_REFRESH_BUTTON.shouldBe(visible).click();
    }

    @Step("Нажатие на кнопку перехода к кнопке удаления магазина")
    public void clickLinkDeleteShopButton() {
        MainPageElements.LINK_TO_DELETE_BUTTON.shouldBe(visible).click();
    }

    //метод поиска магазина по имени
    public static boolean searchShopByName (String shopName) {
        MainPageElements.SHOPS_LIST.get(2).shouldBe(visible);
        for (int i = 2; i <= MainPageElements.SHOPS_LIST.size() + 1; i++) {
            if ($("#response > tr:nth-child(" + i + ") > td:nth-child(2)").innerText().contains(shopName)) {
                return true;
            }
        }
        return false;
    }

    //метод поиска магазина по айди
    public static boolean searchShopById (String shopId) {
        MainPageElements.SHOPS_LIST.get(2).shouldBe(visible);
        for (int i = 2; i <= MainPageElements.SHOPS_LIST.size() + 1; i++) {
            if ($("#response > tr:nth-child(" + i + ") > td:nth-child(1)").innerText().contains(shopId)) {
                return true;
            }
        }
        return false;
    }

    //метод поиска рандомного магазина
    public static String getShopIdFromRandomShop() {
        Random random = new Random();
        sleep(1000);
        int id = random.nextInt(2, MainPageElements.SHOPS_LIST.size() + 1);
        return $("#response > tr:nth-child(" + id + ") > td:nth-child(1)").innerText();
    }

}

package com.example.shop.UI;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPageElements {

    public static SelenideElement REFRESH_BUTTON = $("#shops_div > button");

    public static SelenideElement CREATE_PLACEHOLDER = $("#name");

    public static SelenideElement CREATE_CHECKBOX = $("#public");

    public static SelenideElement CREATE_BUTTON = $("#create > div > button");

    public static SelenideElement DELETE_PLACEHOLDER = $("#id");

    public static SelenideElement DELETE_BUTTON = $("#delete > div > button");

    public static SelenideElement TELEGRAM = $("body > footer > div > a:nth-child(1)");

    public static SelenideElement VK = $("body > footer > div > a:nth-child(2)");

    public static SelenideElement LINK_TO_CREATE_BUTTON = $("#links > a:nth-child(1)");

    public static SelenideElement LINK_TO_REFRESH_BUTTON = $("#links > a:nth-child(2)");

    public static SelenideElement LINK_TO_DELETE_BUTTON = $("#links > a:nth-child(3)");

    public static ElementsCollection SHOPS_LIST = $$("#response tr");

    public static SelenideElement CREATE_SHOP_ERROR_MESSAGE = $("#name_validation");

    public static SelenideElement DELETE_SHOP_ERROR_MESSAGE = $("#id_validation");
}

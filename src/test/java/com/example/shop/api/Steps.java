package com.example.shop.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Steps {

    private final static String URL = "http://localhost:4000/shops";

    public static Response getAll() {
        Specifications.installSpecification(Specifications.requestSpec(URL),
                Specifications.responseSpecOK200());

        RequestSpecification request = RestAssured.given();
        return request.get("/all");
    }

    public static Response postAdd(String shopName, boolean shopPublic) {

        if (shopName.length() > 256) {
            Specifications.installSpecification(Specifications.requestSpec(URL),
                    Specifications.responseSpecError500());
        } else if ((((shopName.charAt(0) > 'A') && (shopName.charAt(0) < 'Z')) ||
                ((shopName.charAt(0) > 'А') && (shopName.charAt(0) < 'Я')))  && (shopName.length() > 7)) {
            Specifications.installSpecification(Specifications.requestSpec(URL),
                    Specifications.responseSpecOK200());
        } else {
            Specifications.installSpecification(Specifications.requestSpec(URL),
                    Specifications.responseSpecError400());
        }

        ShopRequestDto shopRequestDto = new ShopRequestDto(shopName, shopPublic);
        return RestAssured.given().body(shopRequestDto).post("/add");
    }

    public static void delete (long shopId) {
        Specifications.installSpecification(Specifications.requestSpec(URL),
                Specifications.responseSpecOK204());
        RestAssured.delete("/delete/" + shopId);
    }

}

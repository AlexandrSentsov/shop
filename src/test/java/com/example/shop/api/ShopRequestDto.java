package com.example.shop.api;


public class ShopRequestDto {


    private String shopName;

    private boolean shopPublic;



    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopPublic(boolean shopPublic) {
        this.shopPublic = shopPublic;
    }

    public boolean isShopPublic() {
        return shopPublic;
    }

    public ShopRequestDto(String shopName, boolean shopPublic) {
        this.shopName = shopName;
        this.shopPublic = shopPublic;
    }
}

package com.example.sapo.Dto;

public class FlashSaleRequest {

    private Integer productId;
    private Integer userId;
    private Integer quantity;

    public FlashSaleRequest(Integer productId, Integer userId, Integer quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
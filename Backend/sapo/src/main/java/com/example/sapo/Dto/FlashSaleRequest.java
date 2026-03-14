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

    public FlashSaleRequest() {
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

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
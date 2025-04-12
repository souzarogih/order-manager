package com.miriades.martkeplace.order_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class OrderRequest {
    private UUID userId;
    private String productName;
    private BigDecimal value;
    private String cardToken;

    public OrderRequest(UUID userId, String productName, BigDecimal value, String cardToken) {
        this.userId = userId;
        this.productName = productName;
        this.value = value;
        this.cardToken = cardToken;
    }

    public OrderRequest() {

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }
}

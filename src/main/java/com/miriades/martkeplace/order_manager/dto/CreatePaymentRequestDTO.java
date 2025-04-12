package com.miriades.martkeplace.order_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class CreatePaymentRequestDTO {
    private String correlationId;
    private String data;

    public CreatePaymentRequestDTO(String correlationId, String data) {
        this.correlationId = correlationId;
        this.data = data;
    }

    public CreatePaymentRequestDTO() {

    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

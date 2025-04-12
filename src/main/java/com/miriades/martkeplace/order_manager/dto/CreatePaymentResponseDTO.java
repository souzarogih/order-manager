package com.miriades.martkeplace.order_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CreatePaymentResponseDTO {
    private String correlationId;
    private String result;

    public CreatePaymentResponseDTO(String correlationId, String result) {
        this.correlationId = correlationId;
        this.result = result;
    }

    public CreatePaymentResponseDTO() {

    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

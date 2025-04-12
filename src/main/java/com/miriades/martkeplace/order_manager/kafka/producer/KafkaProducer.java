package com.miriades.martkeplace.order_manager.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miriades.martkeplace.order_manager.dto.CreatePaymentRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KafkaProducer {

    @Value("${topic.create-payment-request}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(CreatePaymentRequestDTO dto) {
        try {
            String message = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send(topic, dto.getCorrelationId(), message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar DTO", e);
        }
    }
}

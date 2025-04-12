package com.miriades.martkeplace.order_manager.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miriades.martkeplace.order_manager.dto.CreatePaymentResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Log4j2
@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${topic.create-payment-response}", groupId = "payment-response-group")
    public void consume(ConsumerRecord<String, String> record) {

        try {
            CreatePaymentResponseDTO response = objectMapper.readValue(record.value(), CreatePaymentResponseDTO.class);
            System.out.println("Consumindo resposta: " + response.getCorrelationId());
            System.out.println("Consumindo resposta: " + response.getResult());
//            log.info("Resposta recebida: {}  | ID: {}", response.getResult(), response.getCorrelationId());
            // Aqui você pode armazenar a resposta ou continuar o fluxo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

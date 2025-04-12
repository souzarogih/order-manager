package com.miriades.martkeplace.order_manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miriades.martkeplace.order_manager.dto.CreatePaymentRequestDTO;
import com.miriades.martkeplace.order_manager.dto.OrderRequest;
import com.miriades.martkeplace.order_manager.dto.OrderResponse;
import com.miriades.martkeplace.order_manager.kafka.producer.KafkaProducer;
import com.miriades.martkeplace.order_manager.model.Order;
import com.miriades.martkeplace.order_manager.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Log4j2
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    ObjectMapper objectMapper;

    private Order save(Order order){
//        log.info("Salvando dados da order do user: {}", order.getUserId());
        Order orderSaved = orderRepository.save(
                new Order(
                        UUID.randomUUID(),
                        order.getUserId(),
                        order.getProductName(),
                        order.getValue(),
                        order.getCardToken(),
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );
//        log.info("Order salva em banco de dados | id={}", orderSaved.getId());
        return orderSaved;
    }

    public OrderResponse createOrder(OrderRequest orderRequest){
//        log.info("Iniciando o processo para criar uma ordem.");

        Order order = save(
                new Order(
                        null,
                        orderRequest.getUserId(),
                        orderRequest.getProductName(),
                        orderRequest.getValue(),
                        orderRequest.getCardToken(),
                        null,
                        null
                )
        );

//        log.info("Pedido salvo com id={}", order.getId());

        String orderBody = null;
        try {
            orderBody = objectMapper.writeValueAsString(order);
        }catch (Exception e) {
            e.printStackTrace();
        }

        //enviar dados da requisição para a fila
        CreatePaymentRequestDTO request = new CreatePaymentRequestDTO(
                UUID.randomUUID().toString(),
                orderBody);

//        log.info("Enviado dados para fila do kafka");
        kafkaProducer.send(request);

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getProductName(),
                order.getValue(),
                order.getCardToken(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}

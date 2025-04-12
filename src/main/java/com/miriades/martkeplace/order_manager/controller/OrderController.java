package com.miriades.martkeplace.order_manager.controller;

import com.miriades.martkeplace.order_manager.dto.OrderRequest;
import com.miriades.martkeplace.order_manager.dto.OrderResponse;
import com.miriades.martkeplace.order_manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }
}

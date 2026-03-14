package com.example.sapo.Service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.sapo.Dto.FlashSaleRequest;

@Component
public class FlashSaleConsumer {

    private final OrderService orderService;

    public FlashSaleConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "flashsale.order.queue")
    public void consume(FlashSaleRequest request) {
        orderService.createOrder(request);
    }
}
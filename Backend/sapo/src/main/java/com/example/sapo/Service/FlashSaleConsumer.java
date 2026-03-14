package com.example.sapo.Service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
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
        System.out.println("ProductId: " + request.getProductId());
        System.out.println("UserId: " + request.getUserId());
        if (request.getUserId() == null) {
            throw new AmqpRejectAndDontRequeueException("Missing userId in FlashSaleRequest");
        }
        try {
            orderService.createOrder(request);
        } catch (IllegalArgumentException ex) {
            throw new AmqpRejectAndDontRequeueException(ex.getMessage(), ex);
        } catch (RuntimeException ex) {
            throw ex;
        }
    }
}

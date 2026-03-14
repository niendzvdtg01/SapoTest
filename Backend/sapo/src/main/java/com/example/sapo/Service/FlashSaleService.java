package com.example.sapo.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.sapo.Dto.FlashSaleRequest;

@Service
public class FlashSaleService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final int LIMIT_PER_USER = 2;

    public String placeOrder(FlashSaleRequest request) {
        Integer productId = request.getProductId();
        Integer userId = request.getUserId();
        Integer quantity = request.getQuantity();

        String stockKey = "flashsale:stock:" + productId;
        String userKey = "flashsale:user:" + productId + ":" + userId;

        // Kiem tra user limit
        String userBought = redisTemplate.opsForValue().get(userKey);

        if (userBought != null && Integer.parseInt(userBought) + quantity > LIMIT_PER_USER) {
            return "User exceeded purchase limit";
        }

        // Kiem tra va giam stock
        Long stock = redisTemplate.opsForValue().decrement(stockKey, quantity);

        if (stock == null || stock < 0) {
            redisTemplate.opsForValue().increment(stockKey, quantity);
            return "Product sold out";
        }

        // Tang so luong san pham da mua cua user
        redisTemplate.opsForValue().increment(userKey, quantity);

        // Gui message den RabbitMQ de xu ly order sau

        rabbitTemplate.convertAndSend(
                "flashsale.exchange",
                "flashsale.order",
                request);
        return "Order placed successfully";
    }
}
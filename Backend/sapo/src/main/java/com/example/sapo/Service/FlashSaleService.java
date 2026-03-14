package com.example.sapo.Service;

import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.sapo.Dto.FlashSaleRequest;
import com.example.sapo.Entity.Product;
import com.example.sapo.JpaRepository.ProductRepository;

import jakarta.annotation.PostConstruct;

@Service
public class FlashSaleService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ProductRepository productRepository;

    private static final int LIMIT_PER_USER = 2;

    @PostConstruct
    public void loadStock() {

        List<Product> products = productRepository.findAll();

        for (Product p : products) {
            redisTemplate.opsForValue().set(
                    "flashsale:stock:" + p.getProductId(),
                    String.valueOf(p.getStock()));
        }
    }

    public String placeOrder(FlashSaleRequest request, Integer userId) {

        if (userId == null) {
            return "Unauthenticated";
        }

        Integer productId = request.getProductId();
        Integer quantity = request.getQuantity();

        String stockKey = "flashsale:stock:" + productId;

        // key tong san pham user da mua
        String userKey = "flashsale:user:" + userId;

        // kiem tra user limit
        String userBought = redisTemplate.opsForValue().get(userKey);

        int bought = userBought == null ? 0 : Integer.parseInt(userBought);

        if (bought + quantity > LIMIT_PER_USER) {
            return "User exceeded purchase limit";
        }

        // kiem tra và giảm stock
        Long stock = redisTemplate.opsForValue().decrement(stockKey, quantity);

        if (stock == null || stock < 0) {
            redisTemplate.opsForValue().increment(stockKey, quantity);
            return "Product sold out";
        }

        // tang tong so san pham user da mua
        redisTemplate.opsForValue().increment(userKey, quantity);

        // gui message RabbitMQ
        request.setUserId(userId);

        rabbitTemplate.convertAndSend(
                "flashsale.exchange",
                "flashsale.order",
                request);

        return "Order placed successfully";
    }
}
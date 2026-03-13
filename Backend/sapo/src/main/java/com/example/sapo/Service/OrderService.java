package com.example.sapo.Service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

import com.example.sapo.Dto.FlashSaleRequest;
import com.example.sapo.Entity.Order;
import com.example.sapo.Entity.OrderItem;
import com.example.sapo.Entity.Product;
import com.example.sapo.Entity.Users;
import com.example.sapo.JpaRepository.OrderItemRepository;
import com.example.sapo.JpaRepository.OrderRepository;
import com.example.sapo.JpaRepository.ProductRepository;
import com.example.sapo.JpaRepository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UsersRepository userRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
            UsersRepository userRepository,
            OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public void createOrder(FlashSaleRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // check order
        checkOrder(request, user.getUserId());

        // tao order
        Order order = new Order();
        order.setUserId(user);
        order.setStatus("SUCCESS");

        BigDecimal total = product.getSalePrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        order.setTotalAmount(total);
        orderRepository.save(order);

        // tao order item
        OrderItem item = new OrderItem();
        item.setProductId(product);
        item.setQuantity(request.getQuantity());
        item.setPrice(product.getSalePrice());
        item.setOrder(order);

        orderItemRepository.save(item);
        orderRepository.save(order);
    }

    public void checkOrder(FlashSaleRequest request, Integer userId) {

        Integer bought = orderItemRepository
                .countProductBought(userId, request.getProductId());

        if (bought == null) {
            bought = 0;
        }

        if (bought + request.getQuantity() > 2) {
            throw new RuntimeException("User can only buy maximum 2 products");
        }
    }
}
package com.example.sapo.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sapo.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("""
                SELECT SUM(oi.quantity)
                FROM OrderItem oi
                WHERE oi.order.userId.userId = :userId
                AND oi.productId.productId = :productId
            """)
    Integer countProductBought(Integer userId, Integer productId);

}

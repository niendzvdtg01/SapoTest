package com.example.sapo.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sapo.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Query("""
            UPDATE Product p
            SET p.stock = p.stock - :quantity
            WHERE p.productId = :productId AND p.stock >= :quantity
            """)
    int decreaseStock(Integer productId, Integer quantity);
}

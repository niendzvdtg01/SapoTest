package com.example.sapo.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sapo.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    //
}

package com.example.sapo.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sapo.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    //
}

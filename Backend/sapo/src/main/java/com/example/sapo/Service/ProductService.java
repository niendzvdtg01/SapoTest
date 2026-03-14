package com.example.sapo.Service;

import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.sapo.Entity.Product;
import com.example.sapo.JpaRepository.ProductRepository;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class ProductService {

    private final StringRedisTemplate redisTemplate;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public ProductService(StringRedisTemplate redisTemplate,
            ProductRepository productRepository,
            ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    public List<Product> getProducts() throws Exception {

        String cache = redisTemplate.opsForValue().get("product:list");
        // Lay cache tu Redis
        if (cache != null) {
            return objectMapper.readValue(cache,
                    new TypeReference<List<Product>>() {
                    });
        }
        // Truy van database
        List<Product> products = productRepository.findAll();

        redisTemplate.opsForValue().set(
                "product:list",
                objectMapper.writeValueAsString(products));

        return products;
    }
}
package com.example.sapo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sapo.Dto.FlashSaleRequest;
import com.example.sapo.Service.FlashSaleService;

@RestController
@RequestMapping("/api/flash-sale")
public class FlashSaleController {

    private final FlashSaleService flashSaleService;

    public FlashSaleController(FlashSaleService flashSaleService) {
        this.flashSaleService = flashSaleService;
    }

    @PostMapping("/order")
    public String order(@RequestBody FlashSaleRequest request, Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("Unauthenticated");
        }
        Integer userId = (Integer) authentication.getPrincipal();
        return flashSaleService.placeOrder(request, userId);
    }
}

package com.katatest.feignClient;

import com.kataTest.back.dto.auth.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface IProductClient {
    @GetMapping("/product/{productId}")
    ProductDTO getProductById(@PathVariable Long productId);
}


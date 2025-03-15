package com.katatest.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CartRequest {

    private Long productId;  // The ID of the product being ordered
    private int quantity;    // The quantity of the product
    private String operation;
}

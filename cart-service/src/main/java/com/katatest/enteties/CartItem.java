package com.katatest.enteties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class  CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long productId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart", nullable = false)
    private Cart cart;


}

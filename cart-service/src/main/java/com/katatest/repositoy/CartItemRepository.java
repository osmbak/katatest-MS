package com.katatest.repositoy;


import com.katatest.enteties.Cart;
import com.katatest.enteties.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProductId(Cart cart, long productId);
    List<CartItem> findByProductId(Long productId);
}

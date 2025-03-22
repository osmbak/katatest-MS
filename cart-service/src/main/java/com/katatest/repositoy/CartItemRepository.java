package com.katatest.repositoy;


import com.katatest.enteties.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    //List<CartItem> findByCartUser(User user);
    // Find a specific product in a user's cart
    //CartItem findByProductIdAndCartUser( Long productId , User user);
    Optional<CartItem> findByItemCartIdAndProductId(Long cartId, Long productId);
    List<CartItem> findByProductId(Long productId);
}

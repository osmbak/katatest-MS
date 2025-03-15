package com.katatest.repositoy;

import com.kataTest.back.enteties.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem , Long> {
    //List<CartItem> findByCartUser(User user);
    // Find a specific product in a user's cart
    //CartItem findByProductIdAndCartUser( Long productId , User user);
}

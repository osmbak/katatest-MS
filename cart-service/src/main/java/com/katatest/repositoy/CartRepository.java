package com.katatest.repositoy;


import com.katatest.enteties.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(String user);




    // Delete all cart items for a specific user (clear cart)
    void deleteByUserId(Long userId);
}

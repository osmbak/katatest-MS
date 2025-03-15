package com.katatest.repositoy;

import com.kataTest.back.enteties.Cart;
import com.kataTest.back.enteties.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);




    // Delete all cart items for a specific user (clear cart)
    void deleteByUserId(Long userId);
}

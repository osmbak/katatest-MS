package com.katatest.repositoy;


import com.katatest.enteties.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(String user);
    void deleteByUser(String user);

}

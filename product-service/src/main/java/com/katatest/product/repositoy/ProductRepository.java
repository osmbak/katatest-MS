package com.katatest.product.repositoy;


import com.katatest.product.enteties.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository   extends JpaRepository<Product,Long> {
}

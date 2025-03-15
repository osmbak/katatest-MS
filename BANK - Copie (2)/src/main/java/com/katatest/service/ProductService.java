package com.katatest.service;


import com.kataTest.back.dto.auth.ProductDTO;
import com.kataTest.back.enteties.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ProductService {
    Page<Product> getProducts(Pageable pageable);

    Optional<Product> getProduct(long id);

    Product addProduct(ProductDTO newProduct , MultipartFile image) throws IOException;

    Product putProduct(Long id, ProductDTO p, MultipartFile image) throws IOException ;

    void removeProduct(Long id);


}

package com.katatest.service.impl;


import com.kataTest.back.dto.auth.ProductDTO;
import com.kataTest.back.enteties.Product;
import com.kataTest.back.repositoy.ProductRepository;
import com.kataTest.back.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> getProduct(long id) {
        //
        return productRepository.findById(id);
    }


    @Override
    public Product addProduct(ProductDTO p, MultipartFile image) throws IOException {
        //String imageProduct = new String(image.getBytes(), StandardCharsets.UTF_8);

        Product product = new Product();
        product.setCode(p.getCode());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setImage(image.getBytes());
        product.setCategory(p.getCategory());
        product.setPrice(p.getPrice());
        product.setQuantity(p.getQuantity());
        product.setInternalReference(p.getInternalReference());
        product.setShellId(p.getShellId());
        product.setInventoryStatus(p.getInventoryStatus());
        product.setRating(p.getRating());
        productRepository.save(product);
        return product;
    }


    @Override
    public Product putProduct(Long id, ProductDTO newProduct, MultipartFile image)  throws IOException {
        //String imageProduct = new String(image.getBytes(), StandardCharsets.UTF_8);
        return productRepository.findById(id).map(prd -> {
            //prd.setCode(newProduct.getCode());
            prd.setName(newProduct.getName());
            prd.setDescription(newProduct.getDescription());
            try {
                prd.setImage(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            prd.setCategory(newProduct.getCategory());
            prd.setPrice(newProduct.getPrice());
            prd.setQuantity(newProduct.getQuantity());
            prd.setInternalReference(newProduct.getInternalReference());
            prd.setShellId(newProduct.getShellId());
            prd.setInventoryStatus(newProduct.getInventoryStatus());
            prd.setRating(newProduct.getRating());
            return productRepository.save(prd);
        }).orElseThrow(()->
                new RuntimeException("Product n'existe PAS ID {} : " + id));
    }


    @Override
    public void removeProduct(Long id) {
        //Optional<Product> product = productRepository.findById(id);
        productRepository.deleteById(id);
    }


}

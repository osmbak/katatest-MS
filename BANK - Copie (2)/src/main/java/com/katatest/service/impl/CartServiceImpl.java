package com.katatest.service.impl;

import com.kataTest.back.enteties.Cart;
import com.kataTest.back.enteties.CartItem;
import com.kataTest.back.enteties.Product;
import com.kataTest.back.repositoy.CartItemRepository;
import com.kataTest.back.repositoy.CartRepository;
import com.kataTest.back.repositoy.ProductRepository;
import com.kataTest.back.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

        private final CartRepository cartRepository;

        private final CartItemRepository cartItemRepository;

        private final ProductRepository productRepository ;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

        public Cart addProductToCart(Long cartId, Long productId, int quantity) {
                Cart cart = cartRepository.findById(cartId)
                        .orElseThrow(() -> new RuntimeException("Panier non trouvé"));

                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

                Optional<CartItem> existingItem = cart.getItems().stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst();

                if (existingItem.isPresent()) {
                        CartItem item = existingItem.get();
                        item.setQuantity(item.getQuantity() + quantity);
                        cartItemRepository.save(item);
                } else {
                        CartItem newItem = new CartItem();
                        newItem.setProduct(product);
                        newItem.setQuantity(quantity);
                        newItem.setCart(cart);
                        cartItemRepository.save(newItem);
                }

                return cartRepository.save(cart);
        }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
    }

    public Cart updateCartItemQuantity(Long itemId, int newQuantity, String operation) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Article non trouvé"));

        if ("increment".equals(operation)) {
            item.setQuantity(item.getQuantity() + 1);
        } else if ("decrement".equals(operation) && item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
        } else {
            throw new IllegalArgumentException("Opération invalide");
        }

        cartItemRepository.save(item);
        return cartRepository.findById(item.getCart().getId()).orElseThrow();
    }

    public void removeCartItem(Long itemId) {
        if (!cartItemRepository.existsById(itemId)) {
            throw new RuntimeException("Article non trouvé");
        }
        cartItemRepository.deleteById(itemId);
    }
}


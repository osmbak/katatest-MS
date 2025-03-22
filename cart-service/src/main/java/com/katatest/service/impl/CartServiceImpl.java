package com.katatest.service.impl;

import com.katatest.Dto.ProductDTO;
import com.katatest.Dto.UserDTO;
import com.katatest.enteties.Cart;
import com.katatest.enteties.CartItem;
import com.katatest.feignClient.IProductClient;
import com.katatest.feignClient.IUserClient;
import com.katatest.repositoy.CartItemRepository;
import com.katatest.repositoy.CartRepository;
import com.katatest.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

        private final CartRepository cartRepository;

        private final CartItemRepository cartItemRepository;

        private final IProductClient productClient;

        private final IUserClient iUserClient;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, IProductClient productClient, IUserClient iUserClient) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productClient = productClient;
        this.iUserClient = iUserClient;
    }

    @Override
        public Cart addProductToCart(Long productId) {
        String userDto = iUserClient.getUserConnected().toString();
        Cart cart = cartRepository.findByUser(userDto).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(userDto);
            List listCartItem = new ArrayList();
            listCartItem.add(productId);
            newCart.setItems(listCartItem);
            return cartRepository.save(newCart);
        });


        Optional<CartItem> existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProductId(productId);
            newCartItem.setQuantity(1);
            cart.getItems().add(newCartItem);
        }
        return cartRepository.save(cart);
    }





    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
    }



    @Override
    public CartItem updateCartItemQuantity(CartItem cartItem, int newQuantity, String operation) {

        if ("increment".equals(operation)) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else if ("decrement".equals(operation) && cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
            throw new IllegalArgumentException("Opération invalide");
        }

        cartItemRepository.save(cartItem);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long itemId) {
        if (!cartItemRepository.existsById(itemId)) {
            throw new RuntimeException("Article non trouvé");
        }
        cartItemRepository.deleteById(itemId);
    }
}


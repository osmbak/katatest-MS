package com.katatest.service;


import com.katatest.enteties.Cart;
import com.katatest.enteties.CartItem;

public interface CartService {

     Cart addProductToCart(Long productId) ;

     CartItem updateCartItemQuantity(CartItem cartItem, int newQuantity , String operation);

     void removeCartItem(Long itemId);

     Cart getCart(Long cartId);
}

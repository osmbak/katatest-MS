package com.katatest.service;


import com.katatest.enteties.Cart;
import com.katatest.enteties.CartItem;

public interface CartService {

     Cart addProductToCart(long productId) ;

     CartItem updateCartItemQuantity(CartItem cartItem, int newQuantity , String operation);

     void removeCartItem(long itemId);

     Cart getCart(Long cartId);
}

package com.katatest.service;

import com.kataTest.back.enteties.Cart;

public interface CartService {

     Cart addProductToCart(Long cartId, Long productId, int quantity) ;

     Cart updateCartItemQuantity(Long itemId, int newQuantity , String operation);

     void removeCartItem(Long itemId);

     Cart getCart(Long cartId);

}

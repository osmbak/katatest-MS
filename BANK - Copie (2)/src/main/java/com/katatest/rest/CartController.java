package com.katatest.rest;

import com.kataTest.back.dto.auth.CartRequest;
import com.kataTest.back.enteties.Cart;
import com.kataTest.back.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/katatest/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // 🔹 Récupérer un panier
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    // 🔹 Mettre à jour la quantité d’un article
    @PutMapping("/update")
    public ResponseEntity<Cart> updateCartItemQuantityController(

            @RequestBody CartRequest cartRequest) {

        int newQuantity =  cartRequest.getQuantity();
        long productId = cartRequest.getProductId();
        String operation = cartRequest.getOperation();

        Cart updatedCart = cartService.updateCartItemQuantity(productId, newQuantity, operation);
        return ResponseEntity.ok(updatedCart);
    }

    // 🔹 Supprimer un article du panier
    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long itemId) {
        cartService.removeCartItem(itemId);
        return ResponseEntity.noContent().build();
    }


}
package com.katatest.rest;


import com.katatest.auth.CartRequest;
import com.katatest.enteties.Cart;
import com.katatest.enteties.CartItem;
import com.katatest.service.CartService;
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

    @PosMapping("/add/{productId]")
    public ResponseEntity<Cart> updateCartItemQuantityController(@PathVariable long productId) {
        cartService.addProductToCart(productId);
        return ResponseEntity.ok(updatedCart);
    }

    // 🔹 Mettre à jour la quantité d’un article
    @PutMapping("/update")
    public ResponseEntity<Cart> updateCartItemQuantityController(

            @RequestBody CartRequest cartRequest) {

        int newQuantity =  cartRequest.getQuantity();
        CartItem cartItem = cartRequest.getCartItem();
        String operation = cartRequest.getOperation();

        Cart updatedCart = cartService.updateCartItemQuantity(cartItem, newQuantity, operation).getCart();
        return ResponseEntity.ok(updatedCart);
    }

    // 🔹 Supprimer un article du panier
    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long itemId) {
        cartService.removeCartItem(itemId);
        return ResponseEntity.noContent().build();
    }


}
package com.example.JPAApp.controller;

import com.example.JPAApp.aspect.ApplyTimer;
import com.example.JPAApp.model.Cart;
import com.example.JPAApp.model.CartItem;
import com.example.JPAApp.service.CartGettingService;
import com.example.JPAApp.service.CartUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartGettingService cartGettingService;
    private final CartUpdateService cartUpdateService;

    @ApplyTimer
    @GetMapping("/cart/{userId}")
    public Cart getCart(@PathVariable int userId) {
        return cartGettingService.getCart(userId);
    }

    @ApplyTimer
    @PostMapping("/cart/addProduct/{userId}")
    public void addProductToCart(@PathVariable int userId, @RequestBody CartItem cartItem){
        cartUpdateService.addToCart(userId,  cartItem);
    }

    @ApplyTimer
    @PostMapping("/cart/removeProduct/{userId}")
    public Cart deleteOneProduct(@PathVariable int userId, @RequestBody CartItem cartItem){
        int productId = cartItem.getId();
        cartUpdateService.deleteProduct(userId, productId);
        return getCart(userId);
    }

    @DeleteMapping("/cart/{userId}")
    public Cart deleteAllProducts(@PathVariable int userId){
        cartUpdateService.clearCart(userId);
       return getCart(userId);
    }


}


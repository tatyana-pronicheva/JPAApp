package demo.JPAApp.controller;

import demo.JPAApp.aspect.ApplyTimer;
import demo.JPAApp.model.Cart;
import demo.JPAApp.model.CartItem;
import demo.JPAApp.service.CartUpdateService;
import demo.JPAApp.service.CartGettingService;
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


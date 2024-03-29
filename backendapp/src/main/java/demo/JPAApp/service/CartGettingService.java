package demo.JPAApp.service;

import demo.JPAApp.model.Cart;
import demo.JPAApp.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartGettingService {

    @Cacheable(value = "cart", key = "#userId")
    public Cart getCart(int userId){
        Cart cart = new Cart(userId, new ArrayList<CartItem>());
        return cart;
    }

}

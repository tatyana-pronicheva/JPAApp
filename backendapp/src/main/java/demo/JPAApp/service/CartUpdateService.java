package demo.JPAApp.service;

import demo.JPAApp.model.Cart;
import demo.JPAApp.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartUpdateService {
    private final CartGettingService cartGettingService;

    @CacheEvict(value = "cart", key = "#userId")
    public void clearCart(int userId) {}


    @CachePut(value = "cart", key = "#userId")
    public Cart addToCart(int userId, CartItem newCartItem) {
        Cart cart = cartGettingService.getCart(userId);
        List<CartItem> items = cart.getItems();
        if (items.stream().anyMatch((item)-> item.getId() == newCartItem.getId())){
            items.stream()
                    .filter((item)-> item.getId() == newCartItem.getId())
                    .forEach((item) -> item.increaseCount(newCartItem.getCount()));
        } else {
            items.add(newCartItem);
        }
        cart.setItems(items);
        return cart;
    }

    @CachePut(value = "cart", key = "#userId")
    public Cart deleteProduct(int userId, int productId) {
        Cart cart = cartGettingService.getCart(userId);
        List<CartItem> items = cart.getItems();
        items.removeIf((item) -> item.getId() == productId);
        cart.setItems(items);
        return cart;
    }
}

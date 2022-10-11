package demo.JPAApp.service;


import demo.JPAApp.entity.Order;
import demo.JPAApp.entity.OrderItem;
import demo.JPAApp.exceptions.ResourceNotFoundException;
import demo.JPAApp.model.Cart;
import demo.JPAApp.repository.OrderRepository;
import demo.JPAApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private  final CartUpdateService cartUpdateService;
    private  final CartGettingService cartGettingService;
    private final ProductRepository productRepository;

    public void createOrder(int userId, Order order) {
        Cart currentCart = cartGettingService.getCart(userId);
        order.setUserId(userId);
        order.setUsername("username");
        order.setPrice(currentCart.getTotalPrice());

        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getCount());
                    item.setProductPrice(o.getPrice());
                    item.setTotalPrice(o.getTotalPrice());
                    item.setProduct(productRepository.findById(o.getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return item;
                }).collect(Collectors.toList());
            order.setOrderItems(items);
        orderRepository.save(order);
        cartUpdateService.clearCart(userId);

    }

    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }
}

package demo.JPAApp.controller;


import demo.JPAApp.entity.Order;
import demo.JPAApp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}")
    public void createOrder(@PathVariable int userId, @RequestBody Order order) {
        orderService.createOrder(userId, order);
    }
}

package com.example.JPAApp;

import com.example.JPAApp.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class CartController {
    private List<Product> cart = new ArrayList<Product>();
    private final ProductRepository productRepository;

    @GetMapping("/cart")
    public List<Product> getCart() {
    return cart;
    }

    @PostMapping("/cart/{id}")
    public void addProductToCart(@PathVariable int id){
        cart.add(
        productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product id = "+ id +" not found"))
        );
    }

    @DeleteMapping("/cart/{id}")
    public List<Product> deleteProductFromCart(@PathVariable int id){
        for (int i = 0; i < cart.size(); i++){
            if (cart.get(i).getId()==id){
            cart.remove(i);
            break;
            }
        }
        return cart;
    }

}


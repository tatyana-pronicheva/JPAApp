package com.example.JPAApp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){
        return productRepository.findById(id).get();
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }
//  curl -X POST -H "Content-Type: application/json" -d '{"title": "Milk2", "cost": 40}'  http://localhost:8881/app/products
    @PostMapping("/products")
    public void addNewProduct(@RequestBody Product product){
        productRepository.save(product);
    }
    @GetMapping("/products/delete/{id}")
    public List<Product> deleteProduct(@PathVariable int id){
        productRepository.delete(productRepository.findById(id).get());
        return productRepository.findAll();
    }
}
package com.example.JPAApp;

import com.example.JPAApp.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){
        return productRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product id = "+ id +" not found"));
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

    @DeleteMapping("/products/{id}")
    public List<Product> deleteProduct(@PathVariable int id){
        productRepository.delete(
                productRepository
                        .findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Product id = "+ id +" not found"))
        );
        return productRepository.findAll();
    }

    @PutMapping("/products/{id}")
    public List<Product>  updateProduct(@PathVariable int id, @RequestBody Product product){
        productRepository.updateProductInfoById(id, product.getTitle(), product.getCost());
        return  productRepository.findAll();
    }

}
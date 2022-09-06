package JPAApp.controller;

import JPAApp.entity.Product;
import JPAApp.exceptions.ResourceNotFoundException;
import JPAApp.repository.ProductRepository;
import JPAApp.service.FileService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private final FileService fileService;

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

//  curl -X POST -H "Content-Type: application/json" -d '{"title": "Milk2", "price": 40}'  http://localhost:8881/app/products
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
        productRepository.updateProductInfoById(id, product.getTitle(), product.getPrice());
        return  productRepository.findAll();
    }

    @MessageMapping("/createCsvProducts")
    @SendTo("/topic/items")
    public String createCsvProducts() throws IOException {
        fileService.writeProductsToFile();
        return("/app/api/v1/downloadCsvProducts.csv");
    }

    @GetMapping(value = "/downloadCsvProducts", produces = "application/octet-stream")
        public byte[] downloadCsvProducts(){
            try {
                return fileService.getFileData();
            } catch (IOException e) {
                return new byte[] {};
            }
        }
}
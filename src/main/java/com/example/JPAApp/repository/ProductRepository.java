package com.example.JPAApp.repository;

import com.example.JPAApp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer integer);
    List<Product> findAll();
    <S extends Product> S save(S entity);
    void delete(Product entity);
    @Transactional
    @Modifying
    @Query("update Product p set p.title = :title, p.price = :price where p.id = :id")
    void updateProductInfoById(Integer id, String title, Integer price);
}

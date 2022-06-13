package com.example.JPAApp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer integer);
    List<Product> findAll();
    <S extends Product> S save(S entity);
    void delete(Product entity);
}

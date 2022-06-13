package com.example.JPAApp;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "products_list")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private int cost;
}

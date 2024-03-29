package demo.JPAApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private double price;

    public String[] productToArray(){
        return new String[]{String.valueOf(id),
                            title,
                            String.valueOf(price)};
    }
}

package demo.JPAApp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private double price;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String username;

    @OneToMany
    @JoinTable(name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"))
    private Collection<OrderItem> orderItems;

    public double getTotalPrice(){
        return getOrderItems().stream().collect(Collectors.summingDouble((OrderItem::getTotalPrice)));
    }
}

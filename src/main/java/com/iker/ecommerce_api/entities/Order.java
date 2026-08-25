package com.iker.ecommerce_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User client;

    private String ClientLocation;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Order() { }

    public Order(User client, String clientLocation, Cart cart) {
        this.client = client;
        ClientLocation = clientLocation;
        this.cart = cart;
    }
}

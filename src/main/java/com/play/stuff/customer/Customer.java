package com.play.stuff.customer;

import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "\"CUSTOMER\"")
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "\"customer_seq\"")
    @Column(columnDefinition = "decimal(20)")
    private BigInteger id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    public void addOrder(Order order) {
        orders.add(order);
    }
}

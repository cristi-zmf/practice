package com.play.stuff.customer;

import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.*;

@Entity
@Table(name = "CUSTOMER")
@ToString
@Access(AccessType.FIELD)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_seq")
//    @Column(columnDefinition = "decimal(20)")
    @Access(AccessType.FIELD)
    private BigInteger id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private List<Order> orders;

    public  Customer(String name, Set<Order> orders) {
        this.name = name;
        this.orders = new ArrayList<>(orders);
    }

    Customer() {
        id = BigInteger.ONE;
        this.name = null;
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public BigInteger id() {
        return id;
    }

    public List<Order> orders() {
        return Collections.unmodifiableList(orders);
    }

    public boolean removeOrder(Order someOrder) {
        return orders.remove(someOrder);
    }
}

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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOMER_SEQ")
//    @Column(columnDefinition = "numeric(20, 0)")
    private BigInteger id;
    private String name;

    @OneToMany(mappedBy = "cus")
    private Set<Order> orders;

    public void addOrder(Order order) {
        orders.add(order);
    }
}

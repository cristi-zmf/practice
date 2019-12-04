package com.play.stuff.customer;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOMER_SEQ")
    private final BigInteger id;
    private final Customer customerid;
    private final String value;


    public Order(BigInteger id, BigInteger customerid, String value) {
        this.id = id;
        this.customerid = customerid;
        this.value = value;
    }
}

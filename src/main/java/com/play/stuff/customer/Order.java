package com.play.stuff.customer;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "c_order")
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_seq")
    @Column(columnDefinition = "decimal")
    private BigInteger id;
    private String value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(String value) {
//        this.customerid = customerid;
        this.value = value;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

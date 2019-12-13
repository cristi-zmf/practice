package com.play.stuff.customer;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static void main(String[] args) {
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage beforeHeapMemoryUsage = mbean.getHeapMemoryUsage();
        System.out.printf("Total memory used: %s\n", mbean.getHeapMemoryUsage());
        ArrayList<Object> objects = new ArrayList<>();
        System.out.printf("Total memory used after empty array creation: %s\n", mbean.getHeapMemoryUsage());
        objects = null;
        System.out.printf("Total memory used after deleting array: %s\n", mbean.getHeapMemoryUsage());
        List<Object> emptyList = Collections.emptyList();
        System.out.printf("Total memory used after creating empty list with emptyList method: %s\n", mbean.getHeapMemoryUsage());
    }
}

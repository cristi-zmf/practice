package com.play.stuff.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Set;

public interface CustomerRepo extends JpaRepository<Customer, BigInteger> {

    @Query(nativeQuery = true, value = "SELECT * FROM customer_view;")
    Set<Customer> findAllCustomersNative();
}

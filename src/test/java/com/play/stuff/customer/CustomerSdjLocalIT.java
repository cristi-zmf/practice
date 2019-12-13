package com.play.stuff.customer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

@Slf4j
public class CustomerSdjLocalIT extends BaseLocalIT {
    @Autowired
    private CustomerRepo sut;
    @Autowired
    private EntityManager em;
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private TransactionTemplate transactionTemplate;


    @Test()
    void saveAndFlush() {
        assertThat(sut.findAll())
                .withFailMessage("it failed %d", 3)
                .isEmpty();
        Customer customer = new Customer();
        customer.addOrder(new Order("value"));
        saveCustomerInNewTx(customer);
        addOrderInTx(new Order("new order"));
        addOrderInTx(new Order("new order"));
        deleteAnOrder();
        deleteCustomerInNewTx();
    }

    private void addOrderInTx(Order newOrder) {
        log.warn("Adding new order {}", newOrder);
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Customer customer = sut.findAll().get(0);
            customer.addOrder(newOrder);
        });
    }

    private void deleteCustomerInNewTx() {
        transactionTemplate.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Customer customer = sut.findAll().get(0);
            log.warn("deleting customer {}", customer);
            sut.delete(customer);
        });
    }

    private void deleteAnOrder() {
        transactionTemplate.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Customer customer = sut.findAll().get(0);
            log.warn("deleting an order {}", customer);
            Order someOrder = customer.orders().stream().findFirst().orElseThrow();
            customer.removeOrder(someOrder);
        });
    }

    private void saveCustomerInNewTx(Customer customer) {
        transactionTemplate.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
        transactionTemplate.executeWithoutResult((t) -> {
            sut.saveAndFlush(customer);
            var maps = template.queryForList("SELECT * FROM customer");
            assertThat(maps).isNotEmpty();
            System.out.printf("Look at all these customers!!! %s", maps);
        });
    }
}

package com.play.stuff.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerSdjLocalIT extends BaseLocalIT {
    @Autowired private CustomerRepo sut;
    @Autowired private EntityManager em;
    @Autowired private JdbcTemplate template;
    @Autowired private TransactionTemplate transactionTemplate;


    @Test()
    void saveAndFlush() {
        assertThat(sut.findAll())
                .withFailMessage("it failed %d", 3)
                .isEmpty();
        Customer customer = new Customer();
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.executeWithoutResult((t) -> {
            sut.saveAndFlush(customer);
//            sut.flush();
//            em.flush();
//            t.setRollbackOnly();
            var maps = template.queryForList("SELECT * FROM customer");
//            assertThat(maps).isEmpty();
            assertThat(maps).isNotEmpty();
            System.out.printf("Look at all these customers!!! %s", maps);
        });
    }
}

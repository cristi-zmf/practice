package com.play.stuff.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.math.BigInteger;

@Service
public class SimpleSqlOperations {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private EntityManager em;

    public SimpleSqlOperations(DataSource dataSource, JdbcTemplate jdbcTemplate, PlatformTransactionManager transactionManager, EntityManager em) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.transactionManager = transactionManager;
        this.em = em;
    }

//    @Transactional
    public void doInsert() {
        TransactionTemplate txRunner = new TransactionTemplate(transactionManager);
        txRunner.execute(status -> {
            sqlOperation1();
            sqlOperation2();
            return null;
        });
        Customer customer = em.find(Customer.class, BigInteger.ONE);
        System.out.printf("This is our beautiful customer %s", customer);
    }

    private void sqlOperation1() {
        jdbcTemplate.update("insert into customer (id, name) values (nextval('CUSTOMER_SEQ'), ?)",  "ciuciu");
    }

    private void sqlOperation2() {
//        throw new RuntimeException("something bad happened");
//        Connection connection = dataSource.getConnection();
//        String sql = "insert into customer (id, name) values (?, ?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setLong(1, 2L);
//        statement.setString(2, "cucu");
//
//        statement.execute();
    }


}

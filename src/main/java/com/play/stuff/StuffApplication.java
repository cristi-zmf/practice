package com.play.stuff;

import com.p6spy.engine.spy.P6DataSource;
import com.play.stuff.customer.CustomerRepo;
import com.play.stuff.customer.SimpleSqlOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Set;

@SpringBootApplication
public class StuffApplication {
	@Autowired private SimpleSqlOperations simpleSqlOperations;
	@Autowired private CustomerRepo customerRepo;
	@Autowired private PlatformTransactionManager transactionManager;

	public static void main(String[] args) {
		SpringApplication.run(StuffApplication.class, args);
		System.out.println("bla");
		Set<String> bla = Set.of("bla", "cucu");
		System.out.println(bla);
	}


}

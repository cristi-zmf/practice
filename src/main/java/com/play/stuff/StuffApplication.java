package com.play.stuff;

import com.play.stuff.customer.CustomerRepo;
import com.play.stuff.customer.SimpleSqlOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.util.Set;

@SpringBootApplication
public class StuffApplication implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired private SimpleSqlOperations simpleSqlOperations;
	@Autowired private CustomerRepo customerRepo;
	@Autowired private PlatformTransactionManager transactionManager;

	public static void main(String[] args) {
		SpringApplication.run(StuffApplication.class, args);
		System.out.println("bla");
		Set<String> bla = Set.of("bla", "cucu");
		System.out.println(bla);
	}

//	@PostConstruct
//	@Transactional
	public void insertAUser() {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.executeWithoutResult(t -> System.out.println(customerRepo.findAll()));

		try {
			simpleSqlOperations.doInsert();
		} catch (RuntimeException e) {
			e.printStackTrace();
			transactionTemplate.executeWithoutResult(t -> System.out.printf("Some customers were added %s", customerRepo.findAll()));
		}

	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		contextRefreshedEvent.getApplicationContext().getBean(StuffApplication.class).insertAUser();
	}
}

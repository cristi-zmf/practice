package com.play.stuff;

import com.play.stuff.customer.CustomerRepo;
import com.play.stuff.customer.SimpleSqlOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Set;

@SpringBootApplication
public class StuffApplication {
	@Autowired private SimpleSqlOperations simpleSqlOperations;
	@Autowired private CustomerRepo customerRepo;

	public static void main(String[] args) {
		SpringApplication.run(StuffApplication.class, args);
		System.out.println("bla");
		Set<String> bla = Set.of("bla", "cucu");
		System.out.println(bla);
	}

	@PostConstruct
	public void insertAUser() {
		System.out.println(customerRepo.findAll());
		try {
			simpleSqlOperations.doInsert();
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.printf("Some customers were added %s", customerRepo.findAll());
		}

	}

}

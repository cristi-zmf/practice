package com.play.stuff.customer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BaseLocalIT {

}

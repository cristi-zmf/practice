package com.play.stuff.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerSdjLocalIT extends BaseLocalIT {
    @Autowired CustomerRepo sut;

    @Test
    void saveAndFlush() {
        Assertions.assertThat(sut.findAll())
                .withFailMessage("it failed %d", 3)
                .isNotEmpty().withFailMessage(  "faefea");
    }
}

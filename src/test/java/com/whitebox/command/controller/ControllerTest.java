package com.whitebox.command.controller;

import com.whitebox.model.AccountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerTest.class);
    @Autowired
    private AccountCommandController accountCommandController;

    @Test
    public void testController(){
        AccountDto accountDto = null;
        accountDto.builder().holderName("holderName").balance(BigDecimal.TEN);

        String result = accountCommandController.createAccount(accountDto);
        LOGGER.info(result);
    }
}

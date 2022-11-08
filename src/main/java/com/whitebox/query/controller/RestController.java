package com.whitebox.query.controller;


import com.whitebox.model.AccountDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
class RestController {

    @GetMapping("/accounts")
    public List<AccountDto> listAllAccounts() {
        return null;
    }

}

package com.richflow.api.controller.accounts;

import com.richflow.api.request.accounts.AccountsRequest;
import com.richflow.api.service.accounts.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @PostMapping("/")
    public void createAccounts(@RequestBody AccountsRequest accountsRequest) {
        accountsService.createAccounts(accountsRequest);
    }

    @PatchMapping("/{index}")
    public void updateAccounts() {
    }

    @DeleteMapping("/{index}")
    public void deleteAccounts() {
    }
}

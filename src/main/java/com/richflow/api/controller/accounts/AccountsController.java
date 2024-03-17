package com.richflow.api.controller.accounts;

import com.richflow.api.request.accounts.AccountsRequest;
import com.richflow.api.request.user.UserRequest;
import com.richflow.api.response.accounts.AccountsResponse;
import com.richflow.api.service.accounts.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private AccountsService accountsService;
    private AccountsResponse accountsResponse;

    @PostMapping("/")
    public void createAccounts(@RequestBody UserRequest userRequest, @RequestBody AccountsRequest accountsRequest) {
        accountsService.createAccounts(userRequest, accountsRequest);
    }

    @PatchMapping("/{index}")
    public void updateAccounts() {
    }

    @DeleteMapping("/{index}")
    public void deleteAccounts() {
    }
}

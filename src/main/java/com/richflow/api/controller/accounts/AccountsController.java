package com.richflow.api.controller.accounts;

import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.request.accounts.AccountsRequest;
import com.richflow.api.response.accounts.AccountsResponse;
import com.richflow.api.service.accounts.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @GetMapping("/{userId}")
    public List<Accounts> getAccounts(@PathVariable String userId) throws Exception {
        try {
            return accountsService.getAccountsList(userId);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @PostMapping("/")
    public AccountsResponse createAccounts(@RequestBody AccountsRequest accountsRequest) {
        try {
            accountsService.createAccounts(accountsRequest);
            return AccountsService.buildAccountsResponse(200);
        } catch (Exception e) {
            return AccountsService.buildAccountsResponse(600, String.valueOf(e));
        }
    }

    @PatchMapping("/{acIdx}")
    public AccountsResponse updateAccounts(@PathVariable Long acIdx, @RequestBody AccountsRequest accountsRequest) {
        try {
            if(accountsService.updateAccounts(acIdx, accountsRequest)) {
                return AccountsService.buildAccountsResponse(200);
            } else {
                return AccountsService.buildAccountsResponse(501);
            }
        } catch (Exception e) {
            return AccountsService.buildAccountsResponse(600, String.valueOf(e));
        }
    }

    @DeleteMapping("/{acIdx}")
    public AccountsResponse deleteAccounts(@PathVariable Long acIdx, @RequestBody AccountsRequest accountsRequest) {
        try {
            accountsService.deleteAccounts(acIdx, accountsRequest);
            return AccountsService.buildAccountsResponse(200);
        } catch (Exception e) {
            return AccountsService.buildAccountsResponse(600, String.valueOf(e));
        }
    }
}

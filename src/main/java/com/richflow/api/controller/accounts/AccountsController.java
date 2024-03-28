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
            return AccountsService.buildAccountsResponse(501, String.valueOf(e));
        }
    }

    @PatchMapping("/{index}")
    public void updateAccounts() {
    }

    @DeleteMapping("/{index}")
    public void deleteAccounts() {
    }
}

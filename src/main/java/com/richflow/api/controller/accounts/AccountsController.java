package com.richflow.api.controller.accounts;

import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.request.accounts.CreateAccountsDTO;
import com.richflow.api.request.accounts.UpdateAccountsDTO;
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

    @GetMapping("/{userIdx}")
    public List<Accounts> getAccounts(@PathVariable Long userIdx) throws Exception {
        try {
            return accountsService.getAccountsList(userIdx);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @PostMapping("/")
    public AccountsResponse createAccounts(@RequestBody CreateAccountsDTO createAccountsDTO) {
        try {
            accountsService.createAccounts(createAccountsDTO);
            return AccountsService.buildAccountsResponse(200);
        } catch (Exception e) {
            return AccountsService.buildAccountsResponse(600, String.valueOf(e));
        }
    }

    @PatchMapping()
    public AccountsResponse updateAccounts(@RequestBody UpdateAccountsDTO updateAccountsDTO) {
        try {
            accountsService.updateAccounts(updateAccountsDTO);
            return AccountsService.buildAccountsResponse(200);
        } catch (Exception e) {
            return AccountsService.buildAccountsResponse(600, String.valueOf(e));
        }
    }

    @DeleteMapping("/{acIdx}")
    public AccountsResponse deleteAccounts(@PathVariable Long acIdx) {
        try {
            accountsService.deleteAccounts(acIdx);
            return AccountsService.buildAccountsResponse(200);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

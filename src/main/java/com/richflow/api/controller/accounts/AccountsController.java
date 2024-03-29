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

    @PatchMapping("/{index}")
    public AccountsResponse updateAccounts(@PathVariable Long index, @RequestBody AccountsRequest accountsRequest) {
        try {
            if(accountsRequest.getAcLevel() == 1) {
                return AccountsService.buildAccountsResponse(502); // 변경할 수 없는 카테고리입니다.
            }
            if(accountsService.updateAccounts(index, accountsRequest) == 200) {
                return AccountsService.buildAccountsResponse(200);
            } else {
                return AccountsService.buildAccountsResponse(503); // 권한이 없습니다.
            }
        } catch (Exception e) {
            return AccountsService.buildAccountsResponse(600, String.valueOf(e));
        }
    }

    @DeleteMapping("/{index}")
    public void deleteAccounts(@PathVariable Long index) {
    }
}

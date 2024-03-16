package com.richflow.api.service.accounts;

import com.richflow.api.common.CommonUtil;
import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.accounts.AccountsCode;
import com.richflow.api.domain.enumType.MoneyType;
import com.richflow.api.repository.accounts.AccountsRepository;
import com.richflow.api.request.user.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.richflow.api.domain.enumType.MoneyType.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;

    public void saveBasicAccounts(UserRequest userRequest) {
        this.saveBasicAccountsOfType(userRequest, cash, AccountsCode.CASH);
        this.saveBasicAccountsOfType(userRequest, bank, AccountsCode.BANK);
        this.saveBasicAccountsOfType(userRequest, credit, AccountsCode.CREDIT);
    }

    public void saveBasicAccountsOfType(UserRequest userRequest, MoneyType moneyType, List<String> accountsList) {
        int seq = 1;
        for (String accountName : accountsList) {
            Accounts accounts = buildAndSaveBasicAccount(userRequest, moneyType, accountName, seq++);
            accountsRepository.save(accounts);
        }
    }

    public Accounts buildAndSaveBasicAccount(UserRequest userRequest, MoneyType moneyType, String accountName, int seq) {
        Accounts accounts = new Accounts();
        accounts.setUserIdx(userRequest.getUserIdx());
        accounts.setAcLevel(1);
        accounts.setAcMoneyType(moneyType);
        accounts.setAcName(accountName);
        accounts.setAcSeq(seq);
        accounts.setAcCreateAt(CommonUtil.getTimestamp());
        return accounts;
    }

}

package com.richflow.api.service.accountingTypes;

import com.richflow.api.common.CommonUtil;
import com.richflow.api.domain.accountingTypes.AccountingTypes;
import com.richflow.api.domain.accountingTypes.AccountingTypesCode;
import com.richflow.api.repository.accountingTypes.AccountingTypesRepository;
import com.richflow.api.request.user.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountingTypesService {

    private final AccountingTypesRepository accountingTypesRepository;
    public void saveBasicCategory(UserRequest userRequest) {
        this.saveBasicIncomeCategory(userRequest);
        this.saveBasicExpensesCategory(userRequest);
    }

    public void saveBasicIncomeCategory(UserRequest userRequest) {
        List<String> income = AccountingTypesCode.INCOME;
        for(String in : income) {
            AccountingTypes accountingTypes = new AccountingTypes();
            accountingTypes.setUserIdx(userRequest.getUserIdx());
            accountingTypes.setActEither("I");
            accountingTypes.setActCtgName(in);
            accountingTypes.setActCreateAt(CommonUtil.getTimestamp());
            accountingTypesRepository.save(accountingTypes);
        }
    }

    public void saveBasicExpensesCategory(UserRequest userRequest) {
        List<String> expenses = AccountingTypesCode.EXPENSES;
        for(String ex : expenses) {
            AccountingTypes accountingTypes = new AccountingTypes();
            accountingTypes.setUserIdx(userRequest.getUserIdx());
            accountingTypes.setActEither("O");
            accountingTypes.setActCtgName(ex);
            accountingTypes.setActCreateAt(CommonUtil.getTimestamp());
            accountingTypesRepository.save(accountingTypes);
        }
    }
}

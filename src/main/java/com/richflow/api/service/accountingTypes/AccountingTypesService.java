package com.richflow.api.service.accountingTypes;

import com.richflow.api.common.CommonUtil;
import com.richflow.api.domain.accountingTypes.AccountingTypes;
import com.richflow.api.domain.accountingTypes.AccountingTypesCode;
import com.richflow.api.domain.enumType.ActEither;
import com.richflow.api.repository.accountingTypes.AccountingTypesRepository;
import com.richflow.api.request.accountingTypes.CreateAccountingDTO;
import com.richflow.api.request.user.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.richflow.api.domain.enumType.ActEither.*;

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
        int seq = 1;
        for(String in : income) {
            AccountingTypes accountingTypes = new AccountingTypes();
            accountingTypes.setUserIdx(userRequest.getUserIdx());
            accountingTypes.setActSeq(seq);
            accountingTypes.setActEither(I);
            accountingTypes.setActCtgName(in);
            accountingTypes.setActCreateAt(CommonUtil.getTimestamp());
            accountingTypesRepository.save(accountingTypes);
            seq++;
        }
    }

    public void saveBasicExpensesCategory(UserRequest userRequest) {
        List<String> expenses = AccountingTypesCode.EXPENSES;
        int seq = 1;
        for(String ex : expenses) {
            AccountingTypes accountingTypes = new AccountingTypes();
            accountingTypes.setUserIdx(userRequest.getUserIdx());
            accountingTypes.setActSeq(seq);
            accountingTypes.setActEither(O);
            accountingTypes.setActCtgName(ex);
            accountingTypes.setActCreateAt(CommonUtil.getTimestamp());
            accountingTypesRepository.save(accountingTypes);
            seq++;
        }
    }

    public AccountingTypes createCategory(CreateAccountingDTO createAccountingDTO) {
        AccountingTypes accountingTypes = AccountingTypes.builder()
                .actEither(ActEither.valueOf(createAccountingDTO.getActEither()))
                .actCtgName(createAccountingDTO.getActCtgName())
                .build();
        return accountingTypesRepository.save(accountingTypes);
    }
}

package com.richflow.api.service.accounts;

import com.richflow.api.common.CommonUtil;
import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.accounts.AccountsCode;
import com.richflow.api.domain.enumType.MoneyType;
import com.richflow.api.repository.accounts.AccountsRepository;
import com.richflow.api.request.accounts.AccountsRequest;
import com.richflow.api.request.user.UserRequest;
import com.richflow.api.service.user.UserService;
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
    private final UserService userService;

    /*
    * 자산 등록
    * */
    public void createAccounts(UserRequest userRequest, AccountsRequest accountsRequest) {
        Long userIdx = userService.getUserIdxByUserId(userRequest.getUserId());
        userRequest.setUserIdx(userIdx);

        MoneyType moneyType = MoneyType.valueOf(accountsRequest.getAcMoneyType().toUpperCase());

        // 사용자 입력 자산 최상위 레벨 확인 및 생성
        if(!getExistsByAccountsTopLevel(userIdx, moneyType)) {
            makeAccountsTopLevel(userIdx, moneyType);
        }

        // 사용자 자산 목록 생성
        Accounts accounts = new Accounts();
        accounts.setUserIdx(userIdx);
        accounts.setAcLevel(Math.toIntExact(accountsRequest.getAcLevel()));
        accounts.setAcMoneyType(valueOf(accountsRequest.getAcMoneyType()));
        accounts.setAcName(accountsRequest.getAcName());
        accounts.setAcSeq(accountsRequest.getAcSeq());
        accounts.setAcCreateAt(CommonUtil.getTimestamp());
        accountsRepository.save(accounts);
    }

    /* 
    * 최상위 레벨 자산 목록 생성
    * */
    public void makeAccountsTopLevel(Long userIdx, MoneyType moneyType) {
        Accounts accounts = new Accounts();
        accounts.setUserIdx(userIdx);
        accounts.setAcLevel(1);
        accounts.setAcMoneyType(moneyType);
        accounts.setAcName(AccountsCode.get(String.valueOf(moneyType)));
        accounts.setAcCreateAt(CommonUtil.getTimestamp());
        accountsRepository.save(accounts);
    }

    /*
     * 최상위 레벨 자산 확인
     * */
    public Boolean getExistsByAccountsTopLevel(Long userIdx, MoneyType acMoneyType) {
        return accountsRepository.ExistsByUserIdxAndAcLevelAndAcMoneyType(userIdx, acMoneyType, 1);
    }

}

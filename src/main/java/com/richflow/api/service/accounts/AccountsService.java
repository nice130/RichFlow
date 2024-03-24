package com.richflow.api.service.accounts;

import com.richflow.api.common.CommonUtil;
import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.accounts.AccountsCode;
import com.richflow.api.domain.enumType.AcMoneyType;
import com.richflow.api.repository.accounts.AccountsRepository;
import com.richflow.api.request.accounts.AccountsRequest;
import com.richflow.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.richflow.api.domain.enumType.AcMoneyType.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final UserService userService;

    /*
    * 자산 등록
    * */
    public void createAccounts(AccountsRequest accountsRequest) {
        Long userIdx = userService.getUserIdxByUserId(accountsRequest.getUserId());
        accountsRequest.setUserIdx(userIdx);

        // 사용자 입력 자산 최상위 레벨 확인 및 생성
        if (!getExistsByBasicAccounts(userIdx)) {
            List<AcMoneyType> moneyTypes = List.of(AcMoneyType.values());
            for (AcMoneyType m : moneyTypes) {
                this.makeBasicAccounts(userIdx, m);
            }
        }

        AcMoneyType moneyType = AcMoneyType.valueOf(accountsRequest.getAcMoneyType().toUpperCase());
        Long acAmount = accountsRequest.getAcAmount();
        long amount = Optional.ofNullable(acAmount).orElse(0L);

        // 사용자 자산 목록 생성
        Accounts accounts = new Accounts();
        accounts.setUserIdx(userIdx);
        accounts.setAcLevel(Math.toIntExact(accountsRequest.getAcLevel()));
        accounts.setAcMoneyType(moneyType);
        accounts.setAcAmount(amount);
        accounts.setAcName(accountsRequest.getAcName());
        accounts.setAcSeq(accountsRequest.getAcSeq());
        accounts.setAcCreateAt(CommonUtil.getTimestamp());
        accountsRepository.save(accounts);
    }

    /* 
    * 최상위 레벨 자산 목록 생성
    * */
    public void makeBasicAccounts(Long userIdx, AcMoneyType moneyType) {
        log.info(String.valueOf(userIdx));
        log.info(String.valueOf(moneyType));
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
    public boolean getExistsByBasicAccounts(Long userIdx) {
        return accountsRepository.existsByUserIdx(userIdx);
    }

}

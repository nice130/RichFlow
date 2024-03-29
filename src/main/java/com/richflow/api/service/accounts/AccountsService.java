package com.richflow.api.service.accounts;

import com.richflow.api.common.CommonUtil;
import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.accounts.AccountsCode;
import com.richflow.api.domain.accounts.AccountsResponseCode;
import com.richflow.api.domain.enumType.AcMoneyType;
import com.richflow.api.repository.accounts.AccountsRepository;
import com.richflow.api.request.accounts.AccountsRequest;
import com.richflow.api.response.accounts.AccountsResponse;
import com.richflow.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final UserService userService;

    public List<Accounts> getAccountsList(String userId) {
        Long userIdx = userService.getUserIdxByUserId(userId);
        log.info(String.valueOf(userIdx));
        return accountsRepository.getAccountsByUserIdx(userIdx);
    }

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

        // 사용자 자산 목록 생성
        Accounts accounts = setCommonAccounts(userIdx, moneyType, acAmount);
        accounts.setAcLevel(Math.toIntExact(accountsRequest.getAcLevel()));
        accounts.setAcName(accountsRequest.getAcName());
        accounts.setAcSeq(accountsRequest.getAcSeq());
        accountsRepository.save(accounts);
    }

    public static Accounts setCommonAccounts(Long userIdx, AcMoneyType moneyType, Long acAmount) {
        long amount = Optional.ofNullable(acAmount).orElse(0L);
        Accounts accounts = new Accounts();
        accounts.setUserIdx(userIdx);
        accounts.setAcMoneyType(moneyType);
        accounts.setAcAmount(amount);
        accounts.setAcCreateAt(CommonUtil.getTimestamp());
        return accounts;
    }

    /* 
    * 최상위 레벨 자산 목록 생성
    * */
    public void makeBasicAccounts(Long userIdx, AcMoneyType moneyType) {
        Accounts accounts = setCommonAccounts(userIdx, moneyType, 0L);
        accounts.setAcLevel(1);
        accounts.setAcName(AccountsCode.get(String.valueOf(moneyType)));
        accountsRepository.save(accounts);
    }

    /*
     * 최상위 레벨 자산 확인
     * */
    public boolean getExistsByBasicAccounts(Long userIdx) {
        return accountsRepository.existsByUserIdx(userIdx);
    }

    public int updateAccounts(Long index, AccountsRequest accountsRequest) {
        return accountsRepository.findByAcIdx(index)
                .map(origin -> {
                    if(!userValidation(origin.getUserIdx(), accountsRequest.getUserId())) {
                        return 503;
                    }
                    origin.setAcName(accountsRequest.getAcName());
                    origin.setAcMoneyType(AcMoneyType.valueOf(accountsRequest.getAcMoneyType().toUpperCase()));
                    origin.setAcSeq(accountsRequest.getAcSeq());
                    origin.setAcAmount(accountsRequest.getAcAmount());
                    origin.setAcUpdateAt(CommonUtil.getTimestamp());
                    accountsRepository.save(origin);
                    return 200;
                })
                .orElse(501);
    }

    public boolean userValidation(Long userIdx, String userId) {
        Long reqUserIdx = userService.getUserIdxByUserId(userId);
        return userIdx.equals(reqUserIdx);
    }

    public static AccountsResponse buildAccountsResponse(int code) {
        return AccountsResponse.builder()
                .code(code)
                .message(AccountsResponseCode.getMessage(code))
                .build();
    }

    public static AccountsResponse buildAccountsResponse(int code, String error) {
        return AccountsResponse.builder()
                .code(code)
                .message(error)
                .build();
    }

}

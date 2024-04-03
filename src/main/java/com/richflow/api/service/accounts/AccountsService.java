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

    public List<Accounts> getAccountsList(Long userIdx) {
        return accountsRepository.getAccountsByUserIdx(userIdx);
    }

    /*
    * 자산 등록
    * */
    public void createAccounts(AccountsRequest accountsRequest) {
        Long userIdx = accountsRequest.getUserIdx();
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

        Long parentIdx;
        if(accountsRequest.getAcLevel() > 2) {
            parentIdx = accountsRequest.getAcParentIdx();
        } else {
            Optional<Accounts> accounts = accountsRepository.findByAcMoneyTypeAndAcLevel(moneyType, 1);
            parentIdx = accounts.get().getAcIdx();
        }

        // 사용자 자산 목록 생성
        Accounts accounts = setCommonAccounts(userIdx, moneyType, acAmount);
        accounts.setAcLevel(Math.toIntExact(accountsRequest.getAcLevel()));
        accounts.setAcParentIdx(parentIdx);
        accounts.setAcName(accountsRequest.getAcName());
        accounts.setAcSeq(accountsRequest.getAcSeq());
        accountsRepository.save(accounts);

        // 상위 레벨 자산 업데이트
        Optional<Accounts> pAccounts = accountsRepository.findByAcIdx(parentIdx);
        updateParentAcAmounts(pAccounts.get().getAcIdx(), 0L, acAmount);
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

    public boolean updateAccounts(Long acIdx, AccountsRequest accountsRequest) {
        return accountsRepository.findByAcIdx(acIdx)
                .map(origin -> {
                    userValidation(origin.getUserIdx(), accountsRequest.getUserIdx());
                    levelValidation(acIdx, "update");

                    Long oriAmt = origin.getAcAmount();

                    origin.setAcName(accountsRequest.getAcName());
                    origin.setAcMoneyType(AcMoneyType.valueOf(accountsRequest.getAcMoneyType().toUpperCase()));
                    origin.setAcLevel(accountsRequest.getAcLevel());
                    origin.setAcParentIdx(accountsRequest.getAcParentIdx());
                    origin.setAcSeq(accountsRequest.getAcSeq());
                    origin.setAcAmount(accountsRequest.getAcAmount());
                    origin.setAcUpdateAt(CommonUtil.getTimestamp());
                    accountsRepository.save(origin);

                    updateParentAcAmounts(accountsRequest.getAcParentIdx(), oriAmt, accountsRequest.getAcAmount());
                    return true;
                })
                .orElse(false);
    }

    /*
        상위레벨 ~ 최상위레벨까지 기존 자산, 입력 자산 차액만큼 update
    */
    public void updateParentAcAmounts(Long prtAcIdx, Long oriAmt, Long reqAmt) {
        Long updateAmt = reqAmt - oriAmt;
        accountsRepository.findByAcIdx(prtAcIdx)
                .map(origin -> {
                    Long prtOriAmt = origin.getAcAmount();
                    origin.setAcAmount(prtOriAmt + updateAmt);
                    accountsRepository.save(origin);

                    if(origin.getAcLevel() > 1) {
                        updateParentAcAmounts(origin.getAcParentIdx(), prtOriAmt, origin.getAcAmount());
                    }
                    return true;
                });
    }

    public void deleteAccounts(Long acIdx, AccountsRequest accountsRequest) {
        Optional<Accounts> accounts = accountsRepository.findByAcIdx(acIdx);
        userValidation(accounts.get().getUserIdx(), accountsRequest.getUserIdx());
        levelValidation(acIdx, "delete");
        accountsRepository.deleteByAcIdx(acIdx);
    }

    public void userValidation(Long userIdx, Long reqUserIdx) {
        if(!userIdx.equals(reqUserIdx)) {
            throw new RuntimeException("권한이 없습니다.");
        }
    }

    public void levelValidation(Long acIdx, String type) {
        Optional<Accounts> accounts = accountsRepository.findByAcIdx(acIdx);
        int reqLevel = accounts.get().getAcLevel();
        if(reqLevel == 1) {
            throw new RuntimeException("수정/삭제 불가");
        }
        if(type.equals("delete")) {
            if(accountsRepository.existsByAcParentIdx(acIdx)) {
                throw new RuntimeException("하위 자산을 먼저 삭제해주세요.");
            }
        }
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

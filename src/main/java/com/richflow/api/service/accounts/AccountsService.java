package com.richflow.api.service.accounts;

import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.accounts.AccountsCode;
import com.richflow.api.domain.accounts.AccountsResponseCode;
import com.richflow.api.domain.enumType.AcMoneyType;
import com.richflow.api.repository.accounts.AccountsRepository;
import com.richflow.api.request.accounts.CreateAccountsDTO;
import com.richflow.api.request.accounts.UpdateAccountsDTO;
import com.richflow.api.response.accounts.AccountsResponse;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Accounts> getAccountsList(Long userIdx) {
        return accountsRepository.getAccountsByUserIdx(userIdx);
    }

    /*
    * 자산 등록
    * */
    public void createAccounts(CreateAccountsDTO createAccountsDTO) {
        Long userIdx = createAccountsDTO.getUserIdx();

        // 사용자 입력 자산 최상위 레벨 확인 및 생성
        if (!isExistsByBasicAccounts(userIdx)) {
            List<AcMoneyType> moneyTypes = List.of(AcMoneyType.values());
            for (AcMoneyType m : moneyTypes) {
                this.makeBasicAccounts(userIdx, m);
            }
        }

        Long parentIdx;
        if(createAccountsDTO.getAcLevel() > 2) {
            // 최하위 레벨(3)은 front에서 넘겨준 acParentIdx 사용
            parentIdx = createAccountsDTO.getAcParentIdx();
        } else {
            // 상위 레벨(2)은 moneyType, level로 최상위(1) idx 찾기
            Optional<Accounts> accounts = accountsRepository.findByAcMoneyTypeAndAcLevel(createAccountsDTO.getAcMoneyType(), 1);
            parentIdx = accounts.get().getAcIdx();
        }

        // 사용자 자산 목록 생성
        Accounts createAccounts = Accounts.builder()
                .userIdx(userIdx)
                .acLevel(createAccountsDTO.getAcLevel())
                .acParentIdx(parentIdx)
                .acMoneyType(createAccountsDTO.getAcMoneyType())
                .acAmount(createAccountsDTO.getAcAmount())
                .acName(createAccountsDTO.getAcName())
                .acSeq(createAccountsDTO.getAcSeq())
                .build();
        accountsRepository.save(createAccounts);

        // 상위 레벨 자산 업데이트
        Accounts pAccounts = accountsRepository.findById(parentIdx)
                .orElseThrow(() -> new EntityNotFoundException("변경할 상위레벨이 없습니다."));   // 상위레벨 정보 추출
        updateParentAcAmounts(pAccounts.getAcIdx(), 0L, createAccountsDTO.getAcAmount());
    }

    /* 
    * 최상위 레벨 자산 목록 생성
    * */
    public void makeBasicAccounts(Long userIdx, AcMoneyType moneyType) {
        Accounts createAccounts = Accounts.builder()
                .userIdx(userIdx)
                .acLevel(1)
                .acMoneyType(moneyType)
                .acAmount(0L)
                .acName(AccountsCode.get(String.valueOf(moneyType)))
                .build();
        accountsRepository.save(createAccounts);
    }

    /*
     * 최상위 레벨 자산 유무 확인
     * */
    public boolean isExistsByBasicAccounts(Long userIdx) {
        return accountsRepository.existsByUserIdx(userIdx);
    }

    public void updateAccounts(UpdateAccountsDTO updateAccountsDTO) {
        Accounts accounts = accountsRepository.findById(updateAccountsDTO.getAcIdx())
                .orElseThrow(() -> new EntityNotFoundException("변경할 자산이 없습니다."));

        // 최상위레벨 수정/삭제 불가
        levelValidation(updateAccountsDTO.getAcIdx(), "update");

        Long oriAmt = accounts.getAcAmount();   // 상위레벨 자산 업데이트를 위해 기존자산 저장

        accounts.updateAccounts(updateAccountsDTO);
        accountsRepository.save(accounts);

        // 상위레벨 자산 업데이트
        updateParentAcAmounts(updateAccountsDTO.getAcParentIdx(), oriAmt, updateAccountsDTO.getAcAmount());
    }

    /*
        상위레벨 ~ 최상위레벨까지 기존 자산, 입력 자산 차액만큼 update
    */
    public void updateParentAcAmounts(Long prtAcIdx, Long oriAmt, Long reqAmt) {
        try {
            Accounts accounts = accountsRepository.findById(prtAcIdx)
                    .orElseThrow(() -> new EntityNotFoundException("상위 레벨이 존재하지 않습니다."));

            Long updateAmt = reqAmt - oriAmt;           // 요청금액(기존금액+신규금액) - 기존금액 = 신규금액
            Long prtOriAmt = accounts.getAcAmount();    // 상위레벨 기존금액

            // 상위레벨 업데이트 시 기존금액에 신규금액만 더해서 업데이트
            accounts.updateAcAmount(prtAcIdx, prtOriAmt + updateAmt);
            accountsRepository.save(accounts);

            if (accounts.getAcLevel() > 1) {
                // 최상위 레벨이 아니면 최상위 레벨 찾아서 한번 더 더해준다.
                updateParentAcAmounts(accounts.getAcParentIdx(), prtOriAmt, accounts.getAcAmount());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    public void deleteAccounts(Long acIdx) {
        Accounts accounts = accountsRepository.findById(acIdx)
                .orElseThrow(() -> new EntityNotFoundException("삭제할 자산이 없습니다."));
        levelValidation(acIdx, "delete");
        accountsRepository.deleteById(acIdx);
    }

    public void levelValidation(Long acIdx, String type) {
        Accounts accounts = accountsRepository.findById(acIdx)
                .orElseThrow(() -> new EntityNotFoundException("수정/삭제 할 수 없습니다."));
        int reqLevel = accounts.getAcLevel();
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

package com.richflow.api.repository.accounts;

import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.enumType.AcMoneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    List<Accounts> getAccountsByUserIdx(Long userIdx);
    boolean existsByUserIdx(Long userIdx);
    Optional<Accounts> findByAcMoneyTypeAndAcLevel(AcMoneyType moneyType, int i);
    boolean existsByAcParentIdx(Long acIdx);
}

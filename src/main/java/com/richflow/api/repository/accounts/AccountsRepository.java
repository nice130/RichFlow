package com.richflow.api.repository.accounts;

import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.enumType.MoneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, String> {

    Boolean ExistsByUserIdxAndAcLevelAndAcMoneyType(Long userIdx, MoneyType acMoneyType, int acLevel);
}
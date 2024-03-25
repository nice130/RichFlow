package com.richflow.api.repository.history;

import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.enumType.MoneyType;
import com.richflow.api.domain.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

}

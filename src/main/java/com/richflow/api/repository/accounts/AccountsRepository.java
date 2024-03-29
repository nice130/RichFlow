package com.richflow.api.repository.accounts;

import com.richflow.api.domain.accounts.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    List<Accounts> getAccountsByUserIdx(Long userIdx);

    boolean existsByUserIdx(Long userIdx);

    Optional<Accounts> findByAcIdx(Long acIdx);
}

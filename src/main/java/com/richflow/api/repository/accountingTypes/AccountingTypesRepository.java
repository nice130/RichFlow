package com.richflow.api.repository.accountingTypes;

import com.richflow.api.domain.accountingTypes.AccountingTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingTypesRepository extends JpaRepository<AccountingTypes, String>{
}

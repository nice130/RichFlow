package com.richflow.api.controller.accountingTypes;

import com.richflow.api.domain.accountingTypes.AccountingTypes;
import com.richflow.api.domain.history.History;
import com.richflow.api.request.accountingTypes.CreateAccountingDTO;
import com.richflow.api.service.accountingTypes.AccountingTypesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class AccountingTypesController {

    private final AccountingTypesService accountingTypesService;

    @PostMapping
    public ResponseEntity<AccountingTypes> createCategory(@RequestBody CreateAccountingDTO createAccountingDTO) {
        AccountingTypes createCategory = accountingTypesService.createCategory(createAccountingDTO);
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

}

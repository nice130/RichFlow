package com.richflow.api.controller.accountingTypes;

import com.richflow.api.request.accountingTypes.AccountingTypesRequest;
import com.richflow.api.request.user.UserRequest;
import com.richflow.api.service.accountingTypes.AccountingTypesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/")
    public void saveCategory(@RequestBody UserRequest userRequest, @RequestBody AccountingTypesRequest accountingTypesRequest) {
    }

}

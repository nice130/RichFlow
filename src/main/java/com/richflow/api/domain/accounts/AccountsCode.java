package com.richflow.api.domain.accounts;

import java.util.HashMap;

public class AccountsCode {
    public static final String CASH = "현금";
    public static final String BANK = "은행";
    public static final String CREDIT = "신용카드";

    private static final HashMap<String, String> variables = new HashMap<>();

    static {
        variables.put("CASH", AccountsCode.CASH);
        variables.put("BANK", AccountsCode.BANK);
        variables.put("CREDIT", AccountsCode.CREDIT);
    }

    public static String get(String type) {
        return variables.get(type);
    }

}

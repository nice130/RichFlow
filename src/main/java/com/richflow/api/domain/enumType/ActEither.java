package com.richflow.api.domain.enumType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ActEither {
    I("I"),
    O("O");

    @Getter
    private final String value;

    ActEither(String value){
        this.value = value;
    }

//    @JsonCreator
//    public static ActEither from(String value) {
//        for (ActEither status : ActEither.values()) {
//            if (status.getValue().equals(value)) {
//                return status;
//            }
//        }
//        return null;
//    }
//
//    @JsonValue
//    public String getValue() {
//        return value;
//    }
}
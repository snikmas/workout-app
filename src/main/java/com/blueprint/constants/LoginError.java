package com.blueprint.constants;

import lombok.Getter;

@Getter
public enum LoginError {
    INVALID_LENGTH("Invalid length"),
    INVALID_CHARACTER("Invalid symbols"),
    CONSECUTIVE_CHARACTERS("Invalid consecutive characters"),
    INVALID_BEGINNING("Login can't start from the special characters"),
    NO_LETTERS("Login must have letters");
    private final String message;
    LoginError(String message){
        this.message = message;
    }
}

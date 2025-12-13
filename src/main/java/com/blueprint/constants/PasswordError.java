package com.blueprint.constants;

import lombok.Getter;

@Getter
public enum PasswordError {
    TOO_SHORT("Password must be at least 8 characters"),
    NO_DIGIT("Password must contain a digit"),
    NO_UPPERCASE("Password must contain an uppercase letter"),
    NO_LOWERCASE("Password must contain a lowercase letter"),
    NO_ENGLISH("Password should be written in English!");

    private final String message;
    PasswordError(String message) {
        this.message = message;
    }

}

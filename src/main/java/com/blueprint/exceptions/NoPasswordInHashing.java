package com.blueprint.exceptions;

public class NoPasswordInHashing extends RuntimeException {
    public NoPasswordInHashing(String message) {
        super(message);
    }
}

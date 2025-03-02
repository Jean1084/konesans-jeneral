package com.konesansjeneral.teste.backend.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String attribute, String error) {
        super(attribute + ": " + error);
    }
}

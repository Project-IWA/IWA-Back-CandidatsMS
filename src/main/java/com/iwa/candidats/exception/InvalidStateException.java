package com.iwa.candidats.exception;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String state) {
        super("Invalid state: " + state);
    }
}
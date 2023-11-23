package com.iwa.candidats.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class InvalidEstablishmentDataException extends RuntimeException {
    public InvalidEstablishmentDataException(String message, DataIntegrityViolationException e) {
        super(message);
    }
}


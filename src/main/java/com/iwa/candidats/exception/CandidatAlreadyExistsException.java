package com.iwa.candidats.exception;

public class CandidatAlreadyExistsException extends RuntimeException {
    public CandidatAlreadyExistsException(String email) {
        super("Candidat already exists with email: " + email);
    }
}